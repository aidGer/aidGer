/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
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

package de.aidger.utils.history;

import static de.aidger.utils.Translation._;
import de.aidger.utils.Logger;
import de.aidger.model.AbstractModel;
import de.aidger.view.models.UIModel;
import java.io.Serializable;
import java.sql.Date;

/**
 * A single database related event.
 *
 * @author aidGer Team
 */
public class HistoryEvent implements Serializable {

    /**
     * The id of the model.
     */
    public Long id;

    /**
     * The type of the model.
     */
    public String type;

    /**
     * The status of the event.
     */
    public Status status;

    /**
     * The timestamp of the event.
     */
    public Date date;

    /**
     * Get the model referenced by the event.
     *
     * @return The model
     */
    public UIModel getModel() {
        if (status.equals(Status.Removed) || type.equals("HourlyWage")) {
            return null;
        }

        try {
            Class obj = Class.forName("de.aidger.model.models." + type);
            AbstractModel a = (AbstractModel) obj.newInstance();
            Object o = a.getById(id);

            Class classUI = Class.forName("de.aidger.view.models.UI" + type);
            return (UIModel) classUI.getConstructor(obj).newInstance(o);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The status of the HistoryEvent.
     */
    public enum Status {
        Added(0),
	    Changed(1),
	    Removed(2);

	    private final int id;

        /**
         * Initializes the Status enum.
         *
         * @param id
         *          The id of the status
         */
        Status(int id) {
                this.id = id;
        }

        /**
         * Get the id of the status.
         *
         * @return The id of the status
         */
        public int getId() {
                return id;
        }

        /**
         * Get the correct Status from an int
         *
         * @param id
         *          The id of the searched Status
         * @return The correct Status or null
         */
        public Status fromId(int id) {
                for (Status e : Status.values()) {
                    if (e.id == id) {
                        return e;
                    }
                }
                return null;
        }
    }
}
