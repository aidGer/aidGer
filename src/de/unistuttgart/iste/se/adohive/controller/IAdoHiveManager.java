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

package de.unistuttgart.iste.se.adohive.controller;

import java.util.List;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;

/**
 * Exends the IAdoHiveList interface to support operations common to relational databases, 
 * like getting an object by identifier or update a single data object in the back-end.
 * @author Team AdoHive
 *
 * @param <T>
 */
public interface IAdoHiveManager<T extends IAdoHiveModel<T>> extends IAdoHiveList<T>{

	/**
	 * If T provides a single integer as identifier, this method returns the object with this id.
	 * If T provides an identifier with multiple keys, this method throws an Exception.
	 * @param id
	 * @return the object with id, or null if no object with this id exists
	 * @throws AdoHiveException if T does not provide a single integer as identifier
	 */
	public T getById(int id) throws AdoHiveException;
	
	/**
	 * If T provides an identifier with multiple keys, this method returns the object with these keys.
	 * If T provides a single integer as identifier, this method simply calls getById(o[0]).
	 * @param o one or more keys which identify the object (the keys have to be in the right order)
	 * @return the object with the key(s) o, or null if no object with this keys exists
	 * @throws AdoHiveException if the number of keys do not match or if the keys are not of the right type
	 */
	public T getByKeys(Object... o) throws AdoHiveException;
	
	/**
	 * 
	 * @return all objects
	 */
	public List<T> getAll() throws AdoHiveException;
	
	/**
	 * 
	 * @param o the object to update
	 * @return true, if operation was successful
	 * @throws AdoHiveException 
	 */
	public boolean update(T o) throws AdoHiveException;
	
}
