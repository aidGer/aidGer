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
package de.unistuttgart.iste.se.adohive.model.test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * @author Team AdoHive
 */
public final class HourlyWage implements IHourlyWage{
	protected String qualification = IndependentTestDataProvider.randomFixedLengthDigitString(1);
	protected Byte month = (byte) IndependentTestDataProvider.randomChar();
	protected Short year = (short)(byte) IndependentTestDataProvider.randomChar();
	protected BigDecimal wage = new BigDecimal(BigInteger.valueOf(IndependentTestDataProvider.getRandom().nextInt(99999)),2,new MathContext(5));
	
	public HourlyWage() {
		
	}

	@Override
	public Integer getId() {
		return 871;
	}
	
	@Override
	public void setId(Integer id) {
		// this should never be called, so 
		// do some stuff that leads to
		// an exception.
		
		@SuppressWarnings("unused")
		int a = 1 / 0;
	}
	
	/**
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}
	
	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	/**
	 * @return the month
	 */
	public Byte getMonth() {
			return month;
	}
	
	/**
	 * @param month the month to set
	 */
	public void setMonth(Byte month) {
		this.month = month;
	}
	
	/**
	 * @return the year
	 */
	public Short getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(Short year) {
		this.year = year;
	}
	
	/**
	 * @return the wage
	 */
	public BigDecimal getWage() {
		return wage;
	}
	
	/**
	 * @param wage the wage to set
	 */
	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof IHourlyWage) {
			IHourlyWage h = (IHourlyWage) o;
			return h.getMonth() == this.month &&
					StringHelper.equals(h.getQualification(), this.qualification) &&
					h.getWage().compareTo(this.wage) == 0 &&
					h.getYear() == this.year;
		} else {
			return false;
		}
	}
	
	public HourlyWage clone() {
		HourlyWage h = new HourlyWage();
		h.setMonth(this.month);
		h.setQualification(this.qualification);
		h.setWage(this.wage);
		h.setYear(this.year);
		return h;
	}
}
