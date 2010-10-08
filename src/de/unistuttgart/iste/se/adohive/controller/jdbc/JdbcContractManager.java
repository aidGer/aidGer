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

import de.unistuttgart.iste.se.adohive.controller.IContractManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.IContract;
import de.unistuttgart.iste.se.adohive.model.internal.Contract;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author Team AdoHive
 */
public abstract class JdbcContractManager extends JdbcAdoHiveManager<IContract> implements IContractManager {
	
	public JdbcContractManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(Contract.class, IContract.class, con, dialect, true);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IContractManager#getContracts(java.sql.Date, java.sql.Date)
	 */
	@Override
	public List<IContract> getContracts(Date start, Date end) throws AdoHiveException {
		try {
			PreparedStatement stmt;
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM ");
			dialect.quote("Vertrag",query);
			query.append(" WHERE ");
			dialect.quote("DatumAnfang",query);
			query.append(" BETWEEN ? AND ? OR ");
			dialect.quote("DatumEnde",query);
			query.append(" BETWEEN ? AND ? OR ");
			dialect.quote("DatumAbschluss",query);
			query.append("BETWEEN ? AND ? OR ");
			dialect.quote("DatumBestaetigung",query);
			query.append("BETWEEN ? AND ?");
			stmt = con.prepareStatement(query.toString());
			stmt.setDate(1, start) ;
			stmt.setDate(2, end);
			stmt.setDate(3, start) ;
			stmt.setDate(4, end);
			stmt.setDate(5, start) ;
			stmt.setDate(6, end);
			stmt.setDate(7, start) ;
			stmt.setDate(8, end);
			return executeQuery(stmt);
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		}
	}
	
	@Override
	public List<IContract> getContracts(IAssistant assistant) throws AdoHiveException {
		List<Pair<String,Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String,Object>(dialect.quote("Hilfskraft_ID"), assistant.getId()));
		return executeQuery(createQuery(pairlist));
	}
}
