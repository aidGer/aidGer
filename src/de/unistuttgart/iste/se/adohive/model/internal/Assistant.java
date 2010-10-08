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

import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * @author Team AdoHive
 */
@AdoClass(tableName="Hilfskraft")
public final class Assistant implements IAssistant {
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected String qualification;
	protected String email;
	
	protected IAssistant assistant;
	
	public Assistant() {
		
	}
	
	public Assistant(IAssistant assistant) {
		this.assistant = assistant;
	}
	
	/**
	 * @return the id
	 */
	@AdoMethod(columnName="ID")
	public Integer getId() {
		if(assistant != null)
			return assistant.getId();
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	@AdoMethod(columnName = "ID")
	public void setId(Integer id) {
		if(assistant != null)
			assistant.setId(id);
		this.id = id;
	}
	
	/**
	 * @return the firstName
	 */
	@AdoMethod(columnName = "Vorname")
	public String getFirstName() {
		if(assistant != null)
			return assistant.getFirstName();
		return firstName;
	}
	
	/**
	 * @param firstName the firstName to set
	 */
	@AdoMethod(columnName = "Vorname")
	public void setFirstName(String firstName) {
		if(assistant != null)
			assistant.setFirstName(firstName);
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	@AdoMethod(columnName = "Nachname")
	public String getLastName() {
		if(assistant != null)
			return assistant.getLastName();
		return lastName;
	}
	
	/**
	 * @param lastName the lastName to set
	 */
	@AdoMethod(columnName = "Nachname")
	public void setLastName(String lastName) {
		if(assistant != null)
			assistant.setLastName(lastName);
		this.lastName = lastName;
	}

	/**
	 * @return the qualification
	 */
	@AdoMethod(columnName = "Qualifikation")
	public String getQualification() {
		if(assistant != null)
			return assistant.getQualification();
		return qualification;
	}
	
	/**
	 * @param qualification the qualification to set
	 */
	@AdoMethod(columnName = "Qualifikation")
	public void setQualification(String qualification) {
		if(assistant != null)
			assistant.setQualification(qualification);
		this.qualification = qualification;
	}
	
	@Override
	@AdoMethod(columnName = "Email")
	public String getEmail() {
		if(assistant != null)
			return assistant.getEmail();
		return email;
	}
	
	@Override
	@AdoMethod(columnName = "Email")
	public void setEmail(String email) {
		if(assistant != null)
			assistant.setEmail(email);
		this.email = email;
	}
	
/*	@Override
	public boolean equals(Object o) {
		if (o instanceof Assistant) {
			Assistant a = (Assistant) o;
			return StringHelper.equals(a.getFirstName(), this.firstName) &&
					a.getId() == this.id &&
					StringHelper.equals(a.getLastName(), this.lastName) &&
					StringHelper.equals(a.getQualification(), this.qualification) &&
					StringHelper.equals(a.getEmail(), this.email);
		} else {
			return false;
		}
	}
*/	
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
