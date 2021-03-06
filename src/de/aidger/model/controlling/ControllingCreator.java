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

/**
 * 
 */
package de.aidger.model.controlling;

import java.util.ArrayList;
import java.util.List;

import siena.SienaException;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.Employment;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.UI;

/**
 * This class is used to get all controlling assistant objects for a given year,
 * month and fund.
 * 
 * @author aidGer Team
 */
public class ControllingCreator {

    /**
     * The year of this controlling report.
     */
    private final int year;

    /**
     * The month of this controlling report.
     */
    private final int month;

    /**
     * The costUnit of this controlling report.
     */
    private final CostUnit costUnit;

    /**
     * Initializes a new controlling creator and sets the year, month and funds.
     * 
     * @param year
     *            The year of this controlling report.
     * @param month
     *            The month of this controlling report.
     * @param costUnit
     *            The costUnit of this controlling report.
     */
    public ControllingCreator(int year, int month, CostUnit costUnit) {
        this.year = year;
        this.month = month;
        this.costUnit = costUnit;
    }

    /**
     * Calculates all the assistants of this controlling report, that match the
     * criteria, as well as their costs.
     * 
     * @param ignore
     *            Whether or not to ignore missing hourly wages
     * @return The assistant objects of this controlling report.
     */
    public ArrayList<ControllingAssistant> getAssistants(boolean ignore) {
        ArrayList<ControllingAssistant> controllingAssistants = new ArrayList<ControllingAssistant>();
        try {
            List<Assistant> assistants = new Assistant().getAll();
            // Get all the employments that fit the time span.
            List<Employment> employments = new Employment().getEmployments(
                (short) year, (byte) 1, (short) year, (byte) month);
            for (Assistant assistant : assistants) {
                /*
                 * There's no need to add the assistant, if there are no fitting
                 * employments for it.
                 */
                ControllingAssistant controllingAssistant = null;
                for (Employment employment : employments) {
                    new BalanceHelper();
                    if (assistant.getId().equals(employment.getAssistantId())
                            && costUnit.fromTokenDB(employment.getFunds())
                                .equals(costUnit)
                            && (BalanceHelper
                                .calculatePreTaxBudgetCost(employment) != -1 || !ignore)) {
                        if (controllingAssistant == null) {
                            controllingAssistant = new ControllingAssistant();
                            controllingAssistant.setFirstName(assistant
                                .getFirstName());
                            controllingAssistant.setLastName(assistant
                                .getLastName());
                        }
                        // Costs are needed pre-tax for this.
                        double costs = BalanceHelper
                            .calculatePreTaxBudgetCost(employment);
                        /*
                         * Flag the current controlling assistant if there was a
                         * missing hourly wage.
                         */
                        if (costs == -1) {
                            costs = 0;
                            controllingAssistant.setFlagged(true);
                        }
                        controllingAssistant.setCosts(controllingAssistant
                            .getCosts()
                                + costs);
                    }
                }
                if (controllingAssistant != null) {
                    controllingAssistants.add(controllingAssistant);
                }
            }
        } catch (SienaException e) {
            UI.displayError(e.toString());
        }
        // Sort the controlling assistants
        ArrayList<ControllingAssistant> sortedAssistants = new ArrayList<ControllingAssistant>();
        for (ControllingAssistant a1 : controllingAssistants) {
            boolean added = false;
            for (int i = 0; i < sortedAssistants.size(); i++) {
                int comparison = a1.compareTo(sortedAssistants.get(i));
                if (comparison < 0) {
                    sortedAssistants.add(i, a1);
                    added = true;
                    break;
                }
            }
            if (!added) {
                // Greater than any other controlling assistants
                sortedAssistants.add(a1);
            }
        }
        return sortedAssistants;
    }
}
