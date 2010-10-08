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

package de.unistuttgart.iste.se.adohive.model.test;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.*;

/**
 * 
 * This interface provides test data for each of
 * the model interfaces
 * 
 * @author Felix
 *
 */
public interface ITestDataProvider {
	
	public IActivity newIActivity() throws AdoHiveException ;
	
	public IAssistant newIAssistant() throws AdoHiveException ;
	
	public IContract newIContract() throws AdoHiveException ;
	
	public ICourse newICourse() throws AdoHiveException ;
	
	public IEmployment newIEmployment() throws AdoHiveException ;
	
	public IFinancialCategory newIFinancialCategory() throws AdoHiveException ;
	
	public IHourlyWage newIHourlyWage() throws AdoHiveException ;
}
