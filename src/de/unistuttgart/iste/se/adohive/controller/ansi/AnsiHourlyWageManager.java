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
package de.unistuttgart.iste.se.adohive.controller.ansi;

import java.sql.Connection;
import java.sql.SQLException;

import de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcHourlyWageManager;
import de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author rashfael
 *
 */
class AnsiHourlyWageManager extends JdbcHourlyWageManager {
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE \"Stundenlohn\" (" +
		"\"Qualifikation\" varchar(2) NOT NULL, " +
		"\"Jahr\" int NOT NULL, " +
		"\"Monat\" int NOT NULL, " +
		"\"Lohn\" decimal(5,2) NOT NULL, " +
		"PRIMARY KEY (\"Qualifikation\", \"Jahr\", \"Monat\")" +
		")";

	/**
	 * @param con
	 * @throws AdoHiveException
	 */
	public AnsiHourlyWageManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(con, dialect);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcAdoHiveManager#createTable()
	 */
	@Override
	protected void createTable() throws SQLException{
		//this.con.prepareStatement("DROP TABLE \"Stundenlohn\"").execute();
		this.con.prepareStatement(CREATE_TABLE).execute();		
	}

}
