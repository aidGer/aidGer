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

import de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcEmploymentManager;
import de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author rashfael
 *
 */
class DerbyEmploymentManager extends JdbcEmploymentManager {
	private final static String CREATE_TABLE = 
		"CREATE TABLE \"Beschaeftigung\" (\n" +
		"\"ID\" bigint NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
		"\"Veranstaltung_ID\" bigint NOT NULL," +
		"\"Hilfskraft_ID\" bigint NOT NULL," +
		"\"Vertrag_ID\" int NOT NULL," +
		"\"Monat\" SMALLINT DEFAULT NULL,"+
		"\"Jahr\" SMALLINT DEFAULT NULL,"+
		"\"AnzahlStunden\" float DEFAULT NULL, " +
		"\"Fonds\" varchar(10) DEFAULT NULL, " +
		"\"Bemerkung\" varchar(100) DEFAULT NULL," +
		"\"Kostenstelle\" int DEFAULT NULL," +
		"\"Qualifikation\" varchar(1) DEFAULT NULL," +
		"PRIMARY KEY (ID)," +
		"CONSTRAINT BESCHAEFTIGUNG_VERANSTALTUNG_FK FOREIGN KEY (\"Veranstaltung_ID\") REFERENCES \"Veranstaltung\"(\"ID\")," +
		"CONSTRAINT BESCHAEFTIGUNG_HILFSKRAFT_FK FOREIGN KEY (\"Hilfskraft_ID\") REFERENCES \"Hilfskraft\"(\"ID\")," +
		"CONSTRAINT BESCHAEFTIGUNG_VERTRAG_FK FOREIGN KEY (\"Vertrag_ID\") REFERENCES \"Vertrag\"(\"ID\")" +
		")";

	/**
	 * @param con
	 * @throws AdoHiveException
	 */
	public DerbyEmploymentManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(con, dialect);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcAdoHiveManager#createTable()
	 */
	@Override
	protected void createTable() throws SQLException{
		//this.con.prepareStatement("DROP TABLE \"Beschaeftigung\"").execute();
		this.con.prepareStatement(CREATE_TABLE).execute();
	}

}
