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

import de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcContractManager;
import de.unistuttgart.iste.se.adohive.controller.jdbc.SqlDialect;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author rashfael
 *
 */
class AnsiContractManager extends JdbcContractManager {
	
	private final static String CREATE_TABLE = 
		"CREATE TABLE \"Vertrag\" (\n" +
		"\"ID\" int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n" +
		"\"Hilfskraft_ID\" bigint NOT NULL," +
		"\"Art\" varchar(255) DEFAULT NULL,\n" +
		"\"DatumAnfang\" date DEFAULT NULL,\n" +
		"\"DatumEnde\" date DEFAULT NULL,\n" +
		"\"DatumAbschluss\" date DEFAULT NULL,\n" +
		"\"DatumBestaetigung\" date DEFAULT NULL,\n" +
		"\"Delegation\" smallint DEFAULT NULL,\n"+
		"PRIMARY KEY (ID)," +
		"CONSTRAINT VERTRAG_HILFSKRAFT_FK FOREIGN KEY (\"Hilfskraft_ID\") REFERENCES \"Hilfskraft\"(\"ID\")" +
		")";

	/**
	 * @param con
	 * @throws AdoHiveException
	 */
	public AnsiContractManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(con, dialect);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcAdoHiveManager#createTable()
	 */
	@Override
	protected void createTable() throws SQLException {
		//con.prepareStatement("DROP TABLE \"Hiwi\"").execute();
		con.prepareStatement(CREATE_TABLE).execute();
	}


}
