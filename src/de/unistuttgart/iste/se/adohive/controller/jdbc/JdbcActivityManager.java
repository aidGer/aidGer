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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.controller.IActivityManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.ICourse;
import de.unistuttgart.iste.se.adohive.model.internal.Activity;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author Team AdoHive
 */
public abstract class JdbcActivityManager extends JdbcAdoHiveManager<IActivity> implements IActivityManager {

	public JdbcActivityManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(Activity.class, IActivity.class, con, dialect, true);
	}
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IActivityManager#getActivities(de.unistuttgart.iste.se.adohive.model.ICourse)
	 */
	@Override
	public List<IActivity> getActivities(ICourse course) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>(dialect.quote("Veranstaltung_ID"),course.getId()));
		return executeQuery(createQuery(pairlist));
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IActivityManager#getActivities(de.unistuttgart.iste.se.adohive.model.IAssistant)
	 */
	@Override
	public List<IActivity> getActivities(IAssistant assistant) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>(dialect.quote("Hilfskraft_ID"),assistant.getId()));
		return executeQuery(createQuery(pairlist));
	}
	
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IActivityManager#getActivities(java.sql.Date, java.sql.Date)
	 */
	@Override
	public List<IActivity> getActivities(Date start, Date end) throws AdoHiveException {
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement("SELECT * FROM "+dialect.quote("Vorgang")+" WHERE "+dialect.quote("Datum")+" BETWEEN ? AND ?");
			stmt.setDate(1, start) ;
			stmt.setDate(2, end);
			return executeQuery(stmt);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
}
