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
package de.unistuttgart.iste.se.adohive.controller.derby;

import java.sql.Connection;
import java.sql.SQLException;

import de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcCourseManager;
import de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author rashfael
 *
 */
class DerbyCourseManager extends JdbcCourseManager {

	private final static String CREATE_TABLE = 
		"CREATE TABLE \"Veranstaltung\" (\n" +
		"\"ID\" bigint NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
		"\"Finanzkategorie_ID\" bigint NOT NULL," +
		"\"Bezeichnung\" varchar(255) DEFAULT NULL," +
		"\"Semester\" varchar(6) DEFAULT NULL," +
		"\"Dozent\" varchar(255) DEFAULT NULL," +
		"\"Betreuer\" varchar(255) DEFAULT NULL," +
		"\"Gruppenanzahl\" int DEFAULT NULL," +
		"\"Zielpublikum\" varchar(100) DEFAULT NULL," +
		"\"HKS\" float DEFAULT NULL," +
		"\"Umfang\" varchar(100) DEFAULT NULL," +
		"\"Teil\" varchar(1) DEFAULT NULL," +
		"\"Gruppe\" varchar(100) DEFAULT NULL," +
		"\"Bemerkung\" varchar(100) DEFAULT NULL," +
		"PRIMARY KEY (ID)" +
		")";
	
	/**
	 * @param con
	 * @throws AdoHiveException
	 */
	public DerbyCourseManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(con, dialect);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcAdoHiveManager#createTable()
	 */
	@Override
	protected void createTable() throws SQLException{
		//this.con.prepareStatement("DROP TABLE \"Veranstaltung\"").execute();
		this.con.prepareStatement(CREATE_TABLE).execute();	
	}

}