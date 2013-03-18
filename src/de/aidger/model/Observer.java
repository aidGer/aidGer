/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.model;

/**
 * Provides two callback prototypes to use with Observable.
 * 
 * @param <T> Type of the model
 */
public interface Observer<T> {
	
	/**
	 * Called on AbstractModel.save()
	 * 
	 * @param model The saved model
	 */
	public void onSave(AbstractModel<T> model);
	
	/**
	 * Called on AbstractModel.remove()
	 * 
	 * @param model The removed model
	 */
	public void onRemove(AbstractModel<T> model);
	
}
