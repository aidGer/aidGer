/*
 * Copyright (C) 2010 The AdoHive Team
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */

/**
 * 
 */
package de.unistuttgart.iste.se.adohive.controller.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.unistuttgart.iste.se.adohive.controller.IFinancialCategoryManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveArgumentException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import de.unistuttgart.iste.se.adohive.model.internal.FinancialCategory;
import de.unistuttgart.iste.se.adohive.model.internal.InternalFinancialCategory;

/**
 * @author Team AdoHive
 * Since the columns in the database differ from the fields in the model class 
 * we have to use an intermediate model class with the usual Manager (InternalFinancialCategory).
 * In some cases (size, contains etc.) hard coded sql statements are used.
 * In this class we apply some voodoo to get FinancialCategories into the database and back
 */
public abstract class JdbcFinancialCategoryManager implements IFinancialCategoryManager{
	
	protected Connection con;
	protected JdbcInternalFinancialManager internalManager;
	

	public JdbcFinancialCategoryManager(Connection con) {
		this.con = con;
	}
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager#getByKeys(java.lang.Object[])
	 */
	@Override
	public IFinancialCategory getByKeys(Object... o) throws AdoHiveException {
		if(o.length != 1 || !(o[0] instanceof Integer))
			throw new AdoHiveArgumentException("Keys not supported");
		return getById((Integer)o[0]);
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager#getAll()
	 */
	@Override
	public List<IFinancialCategory> getAll() throws AdoHiveException {
		HashMap<Integer,FinancialCategory> categories = new HashMap<Integer,FinancialCategory>();
		List<InternalFinancialCategory> allInternalCategories = internalManager.getAll();
		
		//we simply use the year and name values from the first InternalFinancialCategory 
		//TODO: add some testing if all rows have the same values (consistency)
		for(InternalFinancialCategory internalCategory : allInternalCategories) {
			if(categories.containsKey(internalCategory.getId()))
				categories.get(internalCategory.getId()).getInternalCategories().add(internalCategory);
			else 
				categories.put(internalCategory.getId(), new FinancialCategory(internalCategory));
				
		}
		
		//because java has no generic covariance and is generally total crap, 
		//we have to copy all the crap from categories to an ArrayList
		
		List<IFinancialCategory> output = new ArrayList<IFinancialCategory>();
		for(FinancialCategory category : categories.values())
			output.add(category);
		return output;
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager#getById(int)
	 */
	@Override
	public IFinancialCategory getById(int id) throws AdoHiveException {
		List<InternalFinancialCategory> internalCategories = internalManager.getInternal(id);
		if(internalCategories.size() == 0)
			return null;
		FinancialCategory fc = new FinancialCategory(internalCategories);
		return fc;
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager#update(de.unistuttgart.iste.se.adohive.model.IAdoHiveModel)
	 */
	@Override
	public boolean update(IFinancialCategory o) throws AdoHiveException {
		//Most complex method, we have to decide which internal categories to update, which to add and which to remove
		//Fetch old/currently saved FinancialCategory and compare the new internal categories with the old ones
		//remove matching categories from the fetched list, then remove all the left fetched categories from the database
		//Check all new internal categories with contains(), then update the ones which are contained and add those which are not
		FinancialCategory category = new FinancialCategory(o);
		List<InternalFinancialCategory> categoriesToRemove = internalManager.getInternal(o.getId());
		
		for(InternalFinancialCategory internalCategory : category.getInternalCategories()) {
			
			//this loop is only for checking if we need to remove a category or not 
			//dont get confused, we remove a category from the categoriesToRemove list so it does NOT get removed
			for(InternalFinancialCategory oldInternalCategory : categoriesToRemove)
				if(internalCategory.getCostUnit().equals(oldInternalCategory.getCostUnit())) {
					categoriesToRemove.remove(oldInternalCategory);
					break; //no need to check the rest, will throw a ConcurrentModificationException anyways
				}
			
			if(internalManager.contains(internalCategory))
				internalManager.update(internalCategory);
			else
				internalManager.add(internalCategory);
		}
		for(InternalFinancialCategory oldInternalCategory : categoriesToRemove)
			internalManager.remove(oldInternalCategory);
		return true;
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#add(de.unistuttgart.iste.se.adohive.model.IAdoHiveModel)
	 */
	@Override
	public boolean add(IFinancialCategory e) throws AdoHiveException {
		//Do some voodoo to get the auto increment value from the first InternalCategory and use that to add the rest
		FinancialCategory fc;
		if(e instanceof FinancialCategory)
			fc = (FinancialCategory)e;
		else
			fc = new FinancialCategory(e);
		if(fc.getInternalCategories().size() == 0)
			return true;
		internalManager.add(fc.getInternalCategories().get(0));
		fc.setId(fc.getInternalCategories().get(0).getId());
		for(int i=1; i<fc.getInternalCategories().size();i++)
			internalManager.add(fc.getInternalCategories().get(i));
		return true;
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#clear()
	 */
	@Override
	public void clear() throws AdoHiveException {
		internalManager.clear();
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#contains(de.unistuttgart.iste.se.adohive.model.IAdoHiveModel)
	 */
	@Override
	public boolean contains(IFinancialCategory o) throws AdoHiveException {
		if(o.getId() == null)
			return false;
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM \"Finanzkategorie\" WHERE ID=" + o.getId());
			return rs.next();
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#get(int)
	 */
	@Override
	public IFinancialCategory get(int index) throws AdoHiveException {
		//Cant be solved without some heavy SQL (LIMIT wont work here), so I just use getAll and get from there
		//TODO:use A combined GROUP BY/LIMIT, then fetch all InternalFinancialCategories with the returned id and wrap them
		return getAll().get(index);
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#isEmpty()
	 */
	@Override
	public boolean isEmpty() throws AdoHiveException {
		return internalManager.isEmpty();
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#remove(de.unistuttgart.iste.se.adohive.model.IAdoHiveModel)
	 */
	@Override
	public boolean remove(IFinancialCategory obj) throws AdoHiveException {
		if(obj.getId() == null)
			return false;
		//we have to remove all rows with e.getID()
		try {
			Statement stmt = con.createStatement();
			int result = stmt.executeUpdate("DELETE FROM \"Finanzkategorie\" WHERE ID=" + obj.getId());
			return result > 0;
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#size()
	 */
	@Override
	public int size() throws AdoHiveException {
		//use sql group here to count only individual IDs
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM (SELECT ID FROM \"Finanzkategorie\" GROUP BY ID) AS t1");
			if(rs.next())
				return rs.getInt(1);
			else
				throw new AdoHiveDatabaseException();
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}


	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveList#subList(int, int)
	 */
	@Override
	public List<IFinancialCategory> subList(int fromIndex, int toIndex)
			throws AdoHiveException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Integer> getDistinctCostUnits() throws AdoHiveException {
		List<Integer> distinctSemesters = new ArrayList<Integer>();
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement("SELECT DISTINCT \"Kostenstelle\" FROM \"Finanzkategorie\"");
			ResultSet result = stmt.executeQuery();
			while(result.next())
				distinctSemesters.add(result.getInt(1));
			return distinctSemesters;
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
}
