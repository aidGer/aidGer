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
import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.controller.IEmploymentManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IEmployment;
import de.unistuttgart.iste.se.adohive.model.internal.Employment;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author Team AdoHive
 */
public abstract class JdbcEmploymentManager extends JdbcAdoHiveManager<IEmployment> implements IEmploymentManager{
	
	public JdbcEmploymentManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(Employment.class, IEmployment.class, con, dialect, true);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IEmploymentManager#getEmployments(java.sql.Date, java.sql.Date)
	 */
	@Override
	public List<IEmployment> getEmployments(short startYear, byte startMonth, short endYear, byte endMonth) throws AdoHiveException{
		
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement(
					"SELECT * FROM \"Beschaeftigung\" WHERE (\"Jahr\" > ? OR (\"Jahr\" = ? AND \"Monat\" >= ?))" +
					"AND (\"Jahr\" < ? OR (\"Jahr\" = ? AND \"Monat\" <= ?))");
			stmt.setShort(1, startYear) ;
			stmt.setShort(2, startYear);
			stmt.setShort(3, (short)startMonth);
			stmt.setShort(4, endYear);
			stmt.setShort(5, endYear);
			stmt.setShort(6, (short)endMonth);
			return executeQuery(stmt);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IEmploymentManager#getEmployments(de.unistuttgart.iste.se.adohive.model.IContract)
	 */
	@Override
	public List<IEmployment> getEmployments(IContract contract) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>("\"Vertrag_ID\"",contract.getId()));
		return executeQuery(createQuery(pairlist));
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IEmploymentManager#getEmployments(de.unistuttgart.iste.se.adohive.model.IAssistant)
	 */
	@Override
	public List<IEmployment> getEmployments(IAssistant assistant) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>("\"Hilfskraft_ID\"",assistant.getId()));
		return executeQuery(createQuery(pairlist));
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IEmploymentManager#getEmployments(de.unistuttgart.iste.se.adohive.model.ICourse)
	 */
	@Override
	public List<IEmployment> getEmployments(ICourse course) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>("\"Veranstaltung_ID\"",course.getId()));
		return executeQuery(createQuery(pairlist));
	}
	
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IEmploymentManager#getEmployments(java.lang.String)
	 */
	@Override
	public List<IEmployment> getEmployments(String semester) throws AdoHiveException {
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement(
					"SELECT \"Beschaeftigung\".* FROM \"Beschaeftigung\",\"Veranstaltung\" WHERE " +
					"\"Beschaeftigung\".\"Veranstaltung_ID\" = \"Veranstaltung\".\"ID\" " +
					"AND \"Veranstaltung\".\"Semester\" = ?");
			stmt.setString(1, semester) ;
			return executeQuery(stmt);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	@Override
	public List<String> getDistinctCostUnits() throws AdoHiveException {
		List<String> distinctSemesters = new ArrayList<String>();
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement("SELECT DISTINCT \"Kostenstelle\" FROM \"Beschaeftigung\"");
			ResultSet result = stmt.executeQuery();
			while(result.next())
				distinctSemesters.add(result.getString(1));
			return distinctSemesters;
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}

}
