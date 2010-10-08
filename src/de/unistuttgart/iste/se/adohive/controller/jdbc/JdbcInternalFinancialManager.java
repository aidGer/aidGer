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

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.internal.InternalFinancialCategory;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author rashfael
 *
 */
public abstract class JdbcInternalFinancialManager extends JdbcAdoHiveManager<InternalFinancialCategory>{

	public JdbcInternalFinancialManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(InternalFinancialCategory.class, InternalFinancialCategory.class, con, dialect, false);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager#getByKeys(java.lang.Object[])
	 */
	@Override
	public InternalFinancialCategory getByKeys(Object... o) throws AdoHiveException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean add(InternalFinancialCategory o) throws AdoHiveException {
		
		try {
			//wrap o in internal Class if o is not already internal
			InternalFinancialCategory wrap;
			if(!modelClass.equals(o.getClass()))
				wrap = modelClass.getConstructor(interfaceClass).newInstance(o);
			else
				wrap = o;
			executeInsert(wrap);
			return true;
		} catch (IllegalArgumentException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (SecurityException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InvocationTargetException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (NoSuchMethodException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	public List<InternalFinancialCategory> getInternal(int id) throws AdoHiveException{
		List<Pair<String, Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String, Object>("ID", id));
		return executeQuery(createQuery(pairlist));
	}
	
	
}
