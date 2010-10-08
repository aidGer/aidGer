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
package de.unistuttgart.iste.se.adohive.controller;

import java.util.List;

import de.unistuttgart.iste.se.adohive.history.HistoryEvent;
import de.unistuttgart.iste.se.adohive.model.IAdoHiveModel;

/**
 * This interface provides access to a simple database change history. 
 * It can be used to log type, date and username of a change, but no change data itself.
 * Since the backend operates on a single table and a single integer to identify rows, 
 * only tables with single integer primary keys can be tracked (every model that implements getId()).
 * 
 * NO HISTORY IS STORED AUTOMATICALLY.
 * You have to call addEvent() to store data.
 * @author Team AdoHive
 */
public interface IHistoryManager {
	
	/**
	 * 
	 * @param event
	 */
	public void addEvent(HistoryEvent event);
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public List<HistoryEvent> getElements(IAdoHiveModel object);
}
