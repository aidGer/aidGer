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
package de.unistuttgart.iste.se.adohive.history;

/**
 * @author Team AdoHive
 */
public enum HistoryStatus {
	Added(0),
	Changed(1),
	Removed(2);
	
	private final int id;
	
	HistoryStatus(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public HistoryStatus fromId(int id) {
		for (HistoryStatus e : HistoryStatus.values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
	}
}
