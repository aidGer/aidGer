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

package de.aidger.model.inspectors;

import static de.aidger.controller.actions.EditorSaveAction.round;
import static de.aidger.utils.Translation._;

import java.text.MessageFormat;
import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.models.UICostUnit;
import de.aidger.view.models.UIFinancialCategory;
import siena.SienaException;

/**
 * An inspector for the budget limit in € for cost units.
 * 
 * @author aidGer Team
 */
public class CostUnitBudgetLimitInspector extends Inspector {

    /**
     * The course to be checked.
     */
    private final Course course;

    /**
     * The cost unit to be checked.
     */
    private final Integer costUnit;

    /**
     * Creates a cost unit budget limit inspector.
     * 
     * @param course
     *            the course to be checked
     * @param costUnit
     *            the cost unit to be checked
     */
    public CostUnitBudgetLimitInspector(Course course, Integer costUnit) {
        this.course = course;
        this.costUnit = costUnit;

        updatedDBRequired = true;
    }

    /*
     * Checks the budget limit in € for the cost unit.
     */
    @Override
    public void check() {
        try {
            double bookedBudgetCosts = 0.0, maxBudgetCosts = 0.0;

            FinancialCategory fc = new FinancialCategory(
                (new FinancialCategory()).getById(course
                    .getFinancialCategoryId()));

            for (int i = 0; i < fc.getCostUnits().length; ++i) {
                if (costUnit.equals(fc.getCostUnits()[i])) {
                    maxBudgetCosts = fc.getBudgetCosts()[i];

                    break;
                }
            }

            List<Course> courses = (new Course()).getCourses(fc);

            for (Course curCourse : courses) {
                List<Employment> employments = (new Employment())
                    .getEmployments(curCourse);

                for (Employment curEmployment : employments) {
                    if (costUnit.equals(curEmployment.getCostUnit())) {
                        bookedBudgetCosts += BalanceHelper
                            .calculateBudgetCost(curEmployment);
                    }
                }
            }

            if (bookedBudgetCosts > maxBudgetCosts) {
                result = MessageFormat
                    .format(
                        _("The budget costs limit for funds {0} in financial category {1} is exceeded ({2}€ / {3}€)."),
                        new Object[] { UICostUnit.valueOf(costUnit),
                                new UIFinancialCategory(fc).toString(),
                                round(bookedBudgetCosts, 2),
                                round(maxBudgetCosts, 2) });
            }
        } catch (SienaException e) {
        }
    }

}
