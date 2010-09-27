package de.aidger.model.inspectors;

import static de.aidger.controller.actions.EditorSaveAction.round;
import static de.aidger.utils.Translation._;

import java.text.MessageFormat;
import java.util.List;

import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.utils.reports.BalanceHelper;
import de.aidger.view.models.UIFinancialCategory;
import de.aidger.view.utils.UIFund;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * An inspector for the budget limit in € for funds.
 * 
 * @author aidGer Team
 */
public class FundsBudgetLimitInspector extends Inspector {

    /**
     * The course to be checked.
     */
    private final Course course;

    /**
     * The funds to be checked.
     */
    private final int funds;

    /**
     * Creates a funds budget limit inspector.
     * 
     * @param course
     *            the course to be checked
     * @param funds
     *            the funds to be checked
     */
    public FundsBudgetLimitInspector(Course course, int funds) {
        this.course = course;
        this.funds = funds;

        updatedDBRequired = true;
    }

    /*
     * Checks the budget limit in € for the funds.
     */
    @Override
    public void check() {
        try {
            double bookedBudgetCosts = 0.0, maxBudgetCosts = 0.0;

            FinancialCategory fc = new FinancialCategory(
                (new FinancialCategory()).getById(course
                    .getFinancialCategoryId()));

            for (int i = 0; i < fc.getFunds().length; ++i) {
                if (fc.getFunds()[i] == funds) {
                    maxBudgetCosts = fc.getBudgetCosts()[i];

                    break;
                }
            }

            List<Course> courses = (new Course()).getCourses(fc);

            for (Course curCourse : courses) {
                List<Employment> employments = (new Employment())
                    .getEmployments(curCourse);

                for (Employment curEmployment : employments) {
                    if (curEmployment.getFunds() == funds) {
                        bookedBudgetCosts += BalanceHelper
                            .calculateBudgetCost(curEmployment);
                    }
                }
            }

            if (bookedBudgetCosts > maxBudgetCosts) {
                result = MessageFormat
                    .format(
                        _("The budget costs limit for funds {0} in financial category {1} is exceeded ({2}€ / {3}€)."),
                        new Object[] { UIFund.valueOf(funds),
                                new UIFinancialCategory(fc).toString(),
                                round(bookedBudgetCosts, 2),
                                round(maxBudgetCosts, 2) });
            }
        } catch (AdoHiveException e) {
        }
    }

}
