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

import de.unistuttgart.iste.se.adohive.controller.ICourseManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import de.unistuttgart.iste.se.adohive.model.internal.Course;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author Team AdoHive
 */
public abstract class JdbcCourseManager extends JdbcAdoHiveManager<ICourse> implements ICourseManager {

	public JdbcCourseManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(Course.class, ICourse.class, con, dialect, true);
	}
	
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.ICourseManager#getCourses(de.unistuttgart.iste.se.adohive.model.IFinancialCategory)
	 */
	@Override
	public List<ICourse> getCourses(IFinancialCategory financialCategory) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>("\"Finanzkategorie_ID\"",financialCategory.getId()));
		return executeQuery(createQuery(pairlist));
	}
	
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.ICourseManager#getCoursesByGroup(java.lang.String)
	 */
	@Override
	public List<ICourse> getCoursesByGroup(String group) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>("\"Gruppe\"",group));
		return executeQuery(createQuery(pairlist));
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.ICourseManager#getCoursesBySemester(java.lang.String)
	 */
	@Override
	public List<ICourse> getCoursesBySemester(String semester) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>("\"Semester\"",semester));
		return executeQuery(createQuery(pairlist));
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.ICourseManager#getDistinctGroups()
	 */
	@Override
	public List<String> getDistinctGroups() throws AdoHiveException {
		List<String> distinctGroups = new ArrayList<String>();
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement("SELECT DISTINCT \"Gruppe\" FROM \"Veranstaltung\"");
			ResultSet result = stmt.executeQuery();
			while(result.next())
				distinctGroups.add(result.getString(1));
			return distinctGroups;
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.ICourseManager#getDistinctSemesters()
	 */
	@Override
	public List<String> getDistinctSemesters() throws AdoHiveException {
		List<String> distinctSemesters = new ArrayList<String>();
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement("SELECT DISTINCT \"Semester\" FROM \"Veranstaltung\"");
			ResultSet result = stmt.executeQuery();
			while(result.next())
				distinctSemesters.add(result.getString(1));
			return distinctSemesters;
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
}
