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

import java.lang.reflect.Method;
import java.sql.Types;

/**
 * @author Team AdoHive
 */
public class JdbcColumn {
	protected String columnName;
	protected Method getMethod = null;
	protected Method setMethod = null;
	protected boolean isPrimary = false;
	protected boolean isAutoIncrement = false;
	protected int sqlType = Types.OTHER;
	
	public JdbcColumn(String columnName) {
		this.columnName = columnName;
	}
	
	public JdbcColumn(String columnName, Method getMethod, Method setMethod, boolean isPrimary, boolean isAutoIncrement, int sqlType) {
		this.columnName = columnName;
		this.getMethod = getMethod;
		this.setMethod = setMethod;
		this.isPrimary = isPrimary;
		this.isAutoIncrement = isAutoIncrement;
		this.sqlType = sqlType;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public Method getGetMethod() {
		return getMethod;
	}
	
	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}
	
	public Method getSetMethod() {
		return setMethod;
	}
	
	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}
	
	public boolean isPrimary() {
		return isPrimary;
	}
	
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	public boolean isAutoIncrement() {
		return isAutoIncrement;
	}
	
	public void setAutoIncrement(boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	public int getSqlType() {
		return sqlType;
	}

	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
}
