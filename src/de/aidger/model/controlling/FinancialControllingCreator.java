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

/**
 * 
 */
package de.aidger.model.controlling;

import java.util.ArrayList;
import java.util.List;

import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * This class creates a controlling report for financial categories
 * 
 * @author aidGer Team
 */
public class FinancialControllingCreator {

    /**
     * Initializes a new FinancialControllingCreator
     */
    public FinancialControllingCreator() {
    }

    /**
     * Get all financial categories of a given year
     * 
     * @param year
     *            The year of the wanted financial categories
     * @return A list of financial categories from the given year
     */
    public List<FinancialCategory> getCategories(int year) {
        List<FinancialCategory> financialCategories = new ArrayList<FinancialCategory>();
        List<IFinancialCategory> tempFinancialCategories = new ArrayList<IFinancialCategory>();
        try {
            tempFinancialCategories = new FinancialCategory().getAll();
            // Only use the financial categories with the given year and convert them to aidGer model form
            for (IFinancialCategory category : tempFinancialCategories) {
                if (category.getYear().intValue() == year) {
                    financialCategories.add(new FinancialCategory(category));
                }
            }
        } catch (AdoHiveException e) {
            e.printStackTrace();
        }
        return financialCategories;
    }
}
