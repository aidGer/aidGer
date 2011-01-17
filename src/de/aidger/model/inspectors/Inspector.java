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

package de.aidger.model.inspectors;

import java.util.List;

/**
 * Basic inspector class for checks before saving models.
 * 
 * @author aidGer Team
 */
public abstract class Inspector {
    /**
     * The result of the check.
     */
    protected String result = "";

    /**
     * Flag whether the check requires an updated database.
     */
    protected boolean updatedDBRequired = false;

    /**
     * Main work for all inspectors is done here by checking the models.
     */
    public abstract void check();

    /**
     * Returns whether the check has failed.
     * 
     * @return whether the check has failed
     */
    public boolean isFail() {
        return !result.isEmpty();
    }

    /**
     * Returns the result of the check.
     * 
     * @return the result of the check
     */
    public String getResult() {
        return result;
    }

    /**
     * Returns whether one of the inspectors requires an updated database.
     * 
     * @param inspectors
     *            list of inspectors
     * @return whether one of the inspectors requires an updated database.
     */
    public static boolean isUpdatedDBRequired(List<Inspector> inspectors) {
        for (Inspector inspector : inspectors) {
            if (inspector.updatedDBRequired) {
                return true;
            }
        }

        return false;
    }
}
