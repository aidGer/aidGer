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

package de.unistuttgart.iste.se.adohive.model;

/**
 * Provides access to one Assistant data object
 * @author Team AdoHive
 *
 */
public interface IAssistant  extends IAdoHiveModel<IAssistant> {
	
	/* keeping this here for awesome code generation
	 public int id;
	 public String firstName;
	 public String lastName;
	 public String qualification
	 */
	
	
	/**
	 * @return the firstName
	 */
	public String getFirstName();
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName);
	
	/**
	 * @return the lastName
	 */
	public String getLastName();
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName);

	/**
	 * 
	 * @return the email
	 */
	public String getEmail();
	
	/**
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email);
	
	/**
	 * @return the qualification
	 */
	public String getQualification();
	
	/**
	 * @param qualification the qualification to set
	 */
	public void setQualification(String qualification);
}
