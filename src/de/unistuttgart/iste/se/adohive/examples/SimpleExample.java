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
package de.unistuttgart.iste.se.adohive.examples;

import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.controller.IAssistantManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * This Example will illustrate the use of AdoHive.
 * We will first create a new IAssistant object and save it to the data back-end with the help of AdoHive.
 * Then we will change some data and let AdoHive update the back-end.
 * Finally we will remove our data object from the data back-end.
 * 
 * @author Team AdoHive
 */
public class SimpleExample {

	public static void main(String[] args) {

		
		//Create a new IAssistant object with the help of MyAssistant
		
		IAssistant assistant = new MyAssistant();
		
		//Fill the assistant with some data
		
		assistant.setFirstName("John");
		assistant.setLastName("Doe");
		assistant.setQualification("q");
		
		//Now add our assistant to the data back-end
		//To do this, get an instance of IAssistantManager the AdoHiveController, 
		//which in turn we can obtain by calling AdoHiveController.getInstance()
		
		//we only do some simple exception handling here
		try
		{
			IAssistantManager assistantManager = AdoHiveController.getInstance().getAssistantManager();
			
			//AdoHive uses setId to automatically add the unique identifier so can keep our assistant object
			assistantManager.add(assistant);
			
			//IMPORTANT: Do not change the ID yourself, setId() is only called once by add to identify the data object with the database
			//and is used by update, remove and others. If you change he ID at any point, there is no guarantee that any AdoHive operation
			//will work as intended
			
			//now change some data
			assistant.setFirstName("Jack");
			
			//save the changes to the data back-end
			assistantManager.update(assistant);
			
			//finally, remove our object
			assistantManager.remove(assistant);
			
			//The assistant object is now no longer in the data back-end, but we still have a reference, so we could add it again
			assistantManager.add(assistant);
			
			//Note that the add operation will probably change the id of the data object
		}
		catch(AdoHiveException ahe)
		{
			ahe.printStackTrace();
		}
		
	}

}

/**
 * @author Team AdoHive
 *
 */
class MyAssistant implements IAssistant {
	protected Integer id;
	protected String firstName;
	protected String lastName;
	protected String qualification;
	protected String email;
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
	
	public MyAssistant clone() {
		MyAssistant a = new MyAssistant();
		a.setFirstName(this.firstName);
		a.setId(this.id);
		a.setLastName(this.lastName);
		a.setQualification(this.qualification);
		return a;
	}
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.IAssistant#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}
	/* (non-Javadoc)
	 * @see de.unistuttgart.iste.se.adohive.model.IAssistant#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
}
