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
package de.unistuttgart.iste.se.adohive.util.tuple;

/**
 * @author rashfael
 *
 */
public class Tuple<First,Rest>
{

	protected First first;
	protected Rest rest;

	protected Tuple(First first, Rest rest) 
	{
		this.first = first;
		this.rest = rest;
	}
	/*
	public <T extends Comparable<T>> Tuple<T, Tuple<First, Rest>> prepend(T m) {
		return new Tuple<T, Tuple<First, Rest>>(m, this);
	}
*/
    // Compare two tuples. All elements must be equal.
    @SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Tuple))
            return false;
        Tuple<First, Rest> that = (Tuple<First, Rest>) obj;
        return
        	(this.first == null ? that.first == null : this.first.equals(that.first)) &&
        	this.rest.equals(that.rest);
    }

    // Calculate a hash code based on the hash of each element.
    public int hashCode() {
    	return (first == null ? 0 : first.hashCode()) + rest.hashCode() * 37;
    }
}
