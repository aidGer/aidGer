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

package de.aidger.utils;

import static de.aidger.utils.Translation._;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Employment;
import de.aidger.view.UI;
import de.aidger.view.models.AssistantTableModel;
import de.aidger.view.tabs.Tab;
import de.aidger.view.tabs.ViewerTab;
import siena.SienaException;

/**
 * Anonymizes assistants after a specified period of time.
 *
 * @author aidGer Team
 */
public class Anonymizer {

    /**
     * The first and last name are replaced with this token.
     */
    public final static String token = "*****";

    /**
     * Anonymizes assistants after a specified peroid of time.
     *
     * @return The count of anonymized assistants
     */
    public static int anonymizeAssistants() {
        int count = 0;
        int time = -1
                * Integer.parseInt(Runtime.getInstance().getOption(
                    "anonymize-time", "365"));

        Calendar checkCal = new GregorianCalendar();
        checkCal.add(Calendar.DAY_OF_YEAR, time);
        Date checkDate = checkCal.getTime();

        // Try to find any existing Assistant Viewer Tabs to update them correctly
        List<AssistantTableModel> models = new ArrayList<AssistantTableModel>();

        if (UI.getWindows().length > 0) {
            for (Tab t : UI.getInstance().getTabs()) {
                if (t instanceof ViewerTab) {
                    ViewerTab v = (ViewerTab) t;
                    if (v.getTableModel() instanceof AssistantTableModel) {
                        models.add((AssistantTableModel) v.getTableModel());
                    }
                }
            }
        }

        try {
            List<Assistant> assistants = (new Assistant()).getAll();

            for (Assistant a : assistants) {
                if (a.getFirstName().equals(token)) {
                    continue;
                }

                Date latest = new Date(1);
                List<Employment> employments = (new Employment())
                    .getEmployments(new Assistant(a));

                for (Employment e : employments) {
                    Calendar gc = new GregorianCalendar(e.getYear(), e
                        .getMonth(), 1);
                    if (gc.getTime().after(latest)) {
                        latest = gc.getTime();
                    }
                }

                if (latest.after(new Date(2)) && latest.before(checkDate)) {
                    count++;

                    Assistant ass = new Assistant(a);
                    ass.setFirstName(token);
                    ass.setLastName(token);
                    ass.setEmail("ano@nym.com");
                    ass.save();

                    for (AssistantTableModel m : models) {
                        //TODO: Rewrite
                        //m.update(ass, true);
                    }
                }
            }
        } catch (SienaException ex) {
            Logger.error(_("Anonymizing Assistants failed because of a database error"));
        } catch (NullPointerException ex) {
            return 0;
        }

        return count;
    }

}
