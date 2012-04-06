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

import static de.aidger.utils.Translation._;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Employment;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.aidger.view.models.UIAssistant;
import siena.SienaException;

/**
 * An inspector for the 6 year employment limit for assistants.
 * 
 * @author aidGer Team
 */
public class EmploymentLimitInspector extends Inspector {

    /**
     * The assistant to be checked.
     */
    private final Assistant assistant;

    /**
     * Creates an employment limit inspector.
     * 
     * @param assistant
     *            the assistant to be checked
     */
    public EmploymentLimitInspector(Assistant assistant) {
        this.assistant = assistant;

        updatedDBRequired = true;
    }

    /*
     * Checks the 6 year employment limit for an assistant.
     */
    @Override
    public void check() {
        int year = 6, limit = year * 12;

        try {
            List<Employment> employments = (new Employment())
                .getEmployments(assistant);

            List<Date> dates = new ArrayList<Date>();

            int unchecked = 0, checked = 0;

            for (Employment employment : employments) {
                Calendar cal = Calendar.getInstance();
                cal.clear();

                cal.set(Calendar.MONTH, employment.getMonth() - 1);
                cal.set(Calendar.YEAR, employment.getYear());

                if (!dates.contains(cal.getTime())) {
                    if (Qualification.valueOf(employment.getQualification()) == Qualification.u
                            || Qualification.valueOf(employment
                                .getQualification()) == Qualification.b) {
                        ++unchecked;
                    }

                    if (Qualification.valueOf(employment.getQualification()) == Qualification.g) {
                        ++checked;
                    }

                    dates.add(cal.getTime());
                }
            }

            if (checked > limit || unchecked > limit) {
                String qualification = Qualification.u.toString();

                if (checked > limit) {
                    qualification = Qualification.g.toString();
                }

                result = MessageFormat
                    .format(
                        _("You have hired {0} with qualification {1} more than {2} years."),
                        new Object[] { (new UIAssistant(assistant)).toString(),
                                qualification, year });
            }

        } catch (SienaException e1) {
        }
    }
}
