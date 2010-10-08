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
package de.unistuttgart.iste.se.adohive.model.internal;

import java.math.BigDecimal;

import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * @author Team AdoHive
 */
@AdoClass(tableName = "Stundenlohn")
public final class HourlyWage implements IHourlyWage{
	protected String qualification;
	protected Byte month;
	protected Short year;
	protected BigDecimal wage;
	
	protected IHourlyWage hourlyWage;
	
	public HourlyWage() {
		
	}
	
	public HourlyWage(IHourlyWage hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void setId(Integer id) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @return the qualification
	 */
	@AdoMethod(columnName = "Qualifikation")
	public String getQualification() {
		if(hourlyWage != null)
			return hourlyWage.getQualification();
		return qualification;
	}
	
	/**
	 * @param qualification the qualification to set
	 */
	@AdoMethod(columnName = "Qualifikation")
	public void setQualification(String qualification) {
		if(hourlyWage != null)
			hourlyWage.setQualification(qualification);
		this.qualification = qualification;
	}
	
	/**
	 * @return the month
	 */
	@AdoMethod(columnName = "Monat")
	public Byte getMonth() {
		if(hourlyWage != null)
			return hourlyWage.getMonth();
		return month;
	}
	
	/**
	 * @param month the month to set
	 */
	@AdoMethod(columnName = "Monat")
	public void setMonth(Byte month) {
		if(hourlyWage != null)
			hourlyWage.setMonth(month);
		this.month = month;
	}
	
	/**
	 * @return the year
	 */
	@AdoMethod(columnName = "Jahr")
	public Short getYear() {
		if(hourlyWage != null)
			return hourlyWage.getYear();
		return year;
	}

	/**
	 * @param year the year to set
	 */
	@AdoMethod(columnName = "Jahr")
	public void setYear(Short year) {
		if(hourlyWage != null)
			hourlyWage.setYear(year);
		this.year = year;
	}
	
	/**
	 * @return the wage
	 */
	@AdoMethod(columnName = "Lohn")
	public BigDecimal getWage() {
		if(hourlyWage != null)
			return hourlyWage.getWage();
		return wage;
	}
	
	/**
	 * @param wage the wage to set
	 */
	@AdoMethod(columnName = "Lohn")
	public void setWage(BigDecimal wage) {
		if(hourlyWage != null)
			hourlyWage.setWage(wage);
		this.wage = wage;
	}
	
/*	@Override
	public boolean equals(Object o) {
		if (o instanceof HourlyWage) {
			HourlyWage h = (HourlyWage) o;
			return h.getMonth() == this.month &&
					StringHelper.equals(h.getQualification(), this.qualification) &&
					h.getWage().equals(this.wage) &&
					h.getYear() == this.year;
		} else {
			return false;
		}
	}
	*/
	public HourlyWage clone() {
		HourlyWage h = new HourlyWage();
		h.setMonth(this.month);
		h.setQualification(this.qualification);
		h.setWage(this.wage);
		h.setYear(this.year);
		return h;
	}
}
