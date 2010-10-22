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

import java.sql.DriverManager;
import java.sql.SQLException;

import de.unistuttgart.iste.se.adohive.controller.jdbc.JdbcAdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveDatabaseException;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author rashfael
 *
 */
public class DerbyAdoHiveController extends JdbcAdoHiveController {

	public DerbyAdoHiveController(String connectionString) throws AdoHiveException {
		super();
		try {
			//Init driver
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(driver).newInstance(); 
			//grab the connection conf
			if(connectionString != null)
				connection = DriverManager.getConnection(connectionString);//"jdbc:derby:derbyDB;create=true");
			else
				connection = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
			//create one dialect Object for all managers
			//NOTE: static does not really work, I would have done this with generics and static methods,
			//but since generics in Java are crap (erasure), it does not work, so we are stuck with throwing around dialect objects
			DerbySqlDialect dialect = new DerbySqlDialect();
			//init all managers
			assistantManager = new DerbyAssistantManager(connection, dialect);
			financialCategoryManager = new DerbyFinancialCategoryManager(connection, dialect);
			hourlyWageManager = new DerbyHourlyWageManager(connection, dialect);
			
			contractManager = new DerbyContractManager(connection, dialect);
			courseManager = new DerbyCourseManager(connection, dialect);
			activityManager = new DerbyActivityManager(connection, dialect);
			
			employmentManager = new DerbyEmploymentManager(connection, dialect);
			
		} catch (SQLException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (InstantiationException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (IllegalAccessException e) {
			throw new AdoHiveDatabaseException(e);
		} catch (ClassNotFoundException e) {
			throw new AdoHiveDatabaseException(e);
		}
		
		
	}
	
	

}