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
import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.iste.se.adohive.controller.IHourlyWageManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveArgumentException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;
import de.unistuttgart.iste.se.adohive.model.internal.HourlyWage;
import de.unistuttgart.iste.se.adohive.util.tuple.Pair;

/**
 * @author Team AdoHive
 */
public abstract class JdbcHourlyWageManager extends JdbcAdoHiveManager<IHourlyWage> implements IHourlyWageManager {

	public JdbcHourlyWageManager(Connection con, SqlDialect dialect) throws AdoHiveException {
		super(HourlyWage.class, IHourlyWage.class, con, dialect, true);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IHourlyWageManager#getByKeys(java.lang.Object[])
	 */
	@Override
	public IHourlyWage getByKeys(Object... o) throws AdoHiveException {
		if(o.length != 3 || !(o[0] instanceof String) || !(o[1] instanceof Byte) || !(o[2] instanceof Short))
			throw new AdoHiveArgumentException("Keys not supported");
		return getByKeys((String)o[0], (Byte)o[1], (Short)o[2]);
	}

	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.controller.IHourlyWageManager#getByKeys(java.lang.String, byte, short)
	 */
	@Override
	public IHourlyWage getByKeys(String qualification, byte month, short year) throws AdoHiveException{
		List<Pair<String, Object>> pairlist = new ArrayList<Pair<String,Object>>();
		pairlist.add(new Pair<String, Object>("\"Qualifikation\"",qualification));
		pairlist.add(new Pair<String, Object>("\"Monat\"",month));
		pairlist.add(new Pair<String, Object>("\"Jahr\"",year));
		
		try {
			return executeQuery(createQuery(pairlist)).get(0);
		} catch (IndexOutOfBoundsException ioobe) {
			return null;
		}
	}
}
