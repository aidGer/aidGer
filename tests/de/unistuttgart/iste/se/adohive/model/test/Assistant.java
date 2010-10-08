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

import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * @author Team AdoHive
 */
public final class Assistant implements IAssistant {
	protected Integer id;
	protected String firstName = IndependentTestDataProvider.randomString();
	protected String lastName = IndependentTestDataProvider.randomString();
	protected String qualification = IndependentTestDataProvider.randomStringFromArr(new String[] {"d","u","g"});
	protected String email = IndependentTestDataProvider.randomString();
		
	public Assistant() {
		
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof IAssistant) {
			IAssistant a = (IAssistant) o;
			return StringHelper.equals(a.getFirstName(), this.firstName) &&
					a.getId().equals(this.id) &&
					StringHelper.equals(a.getLastName(), this.lastName) &&
					StringHelper.equals(a.getQualification(), this.qualification) &&
					StringHelper.equals(a.getEmail(), this.email);
		} else {
			return false;
		}
	}
	
	public Assistant clone() {
		Assistant a = new Assistant();
		a.setFirstName(this.firstName);
		a.setId(this.id);
		a.setLastName(this.lastName);
		a.setQualification(this.qualification);
		a.setEmail(email);
		return a;
	}
}
