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
public class Triple <T1, T2, T3> extends Tuple<T1, Pair<T2,T3>>
{
	public Triple(T1 m1, T2 m2,T3 m3) {
		super(m1, new Pair<T2,T3>(m2,m3));
	}

	public T1 fst()
	{
		return first;
	}
	
	public T2 snd()
	{
		return rest.first;
	}
	
	public T3 trd()
	{
		return rest.rest;
	}
	
}
