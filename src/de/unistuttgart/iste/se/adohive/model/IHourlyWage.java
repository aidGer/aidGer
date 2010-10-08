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
package de.unistuttgart.iste.se.adohive.model;

import java.math.BigDecimal;

/**
 * Provides access to one HourlyWage data object
 * Since HourlyWage has a special identifier consisting of qualification, month and year
 * getId() and setId() mustn't be implemented
 * @author Team AdoHive
 *
 */
public interface IHourlyWage extends IAdoHiveModel<IHourlyWage> {
	
	/* keeping this here for awesome code generation
	private String qualification;
	private byte month;
	private short year;
	private double wage;
	*/
	
	/**
	 * @return the qualification
	 */
	public String getQualification();
	
	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification);
	
	/**
	 * @return the month
	 */
	public Byte getMonth();
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(Byte month);
	
	/**
	 * @return the year
	 */
	public Short getYear();
	
	/**
	 * @param year the year to set
	 */
	public void setYear(Short year);
	
	/**
	 * @return the wage
	 */
	public BigDecimal getWage();
	
	/**
	 * @param wage the wage to set
	 */
	public void setWage(BigDecimal wage);
}
