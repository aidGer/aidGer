/**
 * 
 */
package de.aidger.model.budgets;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.aidger.model.budgets.BudgetFilter.Comparison;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Test the budget creator class.
 * 
 * @author aidGer Team
 */
public class BudgetCreatorTest {

    protected Course course = null;
    private Employment employment;
    private Assistant assistant;
    private Contract contract;
    private FinancialCategory fc;
    private static BudgetCreator budgetCreator;

    @BeforeClass
    public static void beforeClassSetUp() {
        de.aidger.model.Runtime.getInstance().initialize();
    }

    /**
     * Sets up every test.
     * 
     * @throws AdoHiveException
     */
    @Before
    public void setUp() throws AdoHiveException {
        fc = new FinancialCategory();
        fc.setBudgetCosts(new Integer[] { 100 });
        fc.setFunds(new Integer[] { 10001000 });
        fc.setName("name");
        fc.setYear((short) 2010);
        fc.save();

        assistant = new Assistant();
        assistant.setEmail("test@example.com");
        assistant.setFirstName("Test");
        assistant.setLastName("Tester");
        assistant.setQualification("g");
        assistant.save();

        contract = new Contract();
        contract.setAssistantId(assistant.getId());
        contract.setCompletionDate(new Date(10));
        contract.setConfirmationDate(new Date(100));
        contract.setDelegation(false);
        contract.setEndDate(new Date(1000));
        contract.setStartDate(new Date(20));
        contract.setType("Type");
        contract.save();

        course = new Course();
        course.setId(1);
        course.setAdvisor("Tester");
        course.setDescription("Description");
        course.setFinancialCategoryId(fc.getId());
        course.setGroup("2");
        course.setLecturer("Test Tester");
        course.setNumberOfGroups(3);
        course.setPart('a');
        course.setRemark("Remark");
        course.setScope("Sniper Scope");
        course.setSemester("SS09");
        course.setTargetAudience("Testers");
        course.setUnqualifiedWorkingHours(100.0);
        course.save();

        employment = new Employment();
        employment.setId(1);
        employment.setAssistantId(assistant.getId());
        employment.setContractId(contract.getId());
        employment.setCourseId(course.getId());
        employment.setCostUnit("0711");
        employment.setFunds(1);
        employment.setHourCount(40.0);
        employment.setMonth((byte) 10);
        employment.setQualification("g");
        employment.setRemark("Remark");
        employment.setYear((short) 2012);
        employment.save();

        budgetCreator = new BudgetCreator();
    }

    /**
     * Tests the method addCourseBudget() of the class BudgetCreator.
     */
    @Test
    public void testAddCourseBudget() {
        System.out.println("addCourseBudget()");

        /*
         * Test with no filters.
         */
        budgetCreator.addCourseBudget(course, null);

        CourseBudget resultBudget = budgetCreator.getCourseBudgets().get(0);
        CourseBudget wantedResultBudget = new CourseBudget(course);

        /*
         * With no filter, the course should be converted into a budget course.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one lecturer as the filter.
         */
        BudgetFilter budgetFilter = new BudgetFilter();
        budgetFilter.addLecturer(course.getLecturer());

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the lecturer of the course as the filtered lecturer, the course
         * should be converted into a budget course.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one semester as the filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.addSemester(course.getSemester());

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the semester of the course as the filtered semester, the course
         * should be converted into a budget course.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one available budget and the equal comparison as the
         * filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.setAvailableBudget(course.getUnqualifiedWorkingHours()
                * course.getNumberOfGroups() - employment.getHourCount());
        budgetFilter.setAvailableComparison(Comparison.EQUAL);

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the available budget of the course as the filtered available
         * budget, the course should be converted into a budget course with an
         * equality comparison.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one total budget and the less than comparison as the
         * filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.setTotalBudget(course.getUnqualifiedWorkingHours()
                * course.getNumberOfGroups() + 1);
        budgetFilter.setTotalComparison(Comparison.LESS);

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the total budget of the course below the filtered total budget
         * and a less than comparison, the course should be converted to a
         * course budget.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one booked budget and the greater comparison as a filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.setBookedBudget(employment.getHourCount() - 1);
        budgetFilter.setTotalComparison(Comparison.GREATER);

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the booked budget of the course greater than the filtered booked
         * budget, the course should be converted to a course budget.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one total budget and the less or equal than comparison as
         * the filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.setTotalBudget(course.getUnqualifiedWorkingHours()
                * course.getNumberOfGroups());
        budgetFilter.setTotalComparison(Comparison.LESSEQUAL);

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the total budget of the course equal to the filtered total
         * budget and a less or equal than comparison, the course should be
         * converted to a course budget.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one booked budget and the greater or equal comparison as a
         * filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.setBookedBudget(employment.getHourCount());
        budgetFilter.setTotalComparison(Comparison.GREATEREQUAL);

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With the booked budget of the course equal to the filtered booked
         * budget, the course should be converted to a course budget.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));

        /*
         * Test with one booked budget and the no comparison as a filter.
         */
        budgetFilter = new BudgetFilter();
        budgetFilter.setBookedBudget(employment.getHourCount());
        budgetFilter.setTotalComparison(Comparison.NONE);

        budgetCreator = new BudgetCreator();
        budgetCreator.addCourseBudget(course, budgetFilter);

        resultBudget = budgetCreator.getCourseBudgets().get(0);
        wantedResultBudget = new CourseBudget(course);

        /*
         * With no comparison selected, the course should be converted to a
         * course budget.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));
    }

    /**
     * Tests the method getObjectArray() of the class BudgetCreator.
     */
    @Test
    public void testGetObjectArray() {
        System.out.println("getObjectArray()");

        budgetCreator.addCourseBudget(course, null);

        CourseBudget resultBudget = budgetCreator.getCourseBudgets().get(0);
        CourseBudget wantedResultBudget = new CourseBudget(course);

        /*
         * With no filter, the course should be converted into a budget course.
         */
        assertArrayEquals(budgetCreator.getObjectArray(resultBudget),
            budgetCreator.getObjectArray(wantedResultBudget));
    }

    /**
     * Tests the method getCourseBudgets() of the class BudgetCreator.
     */
    @Test
    public void testGetCourseBudgets() {
        System.out.println("getCourseBudgets()");

        budgetCreator.addCourseBudget(course, null);

        assertTrue(budgetCreator.getCourseBudgets().size() == 1);
    }

    /**
     * Cleans up after the tests.
     * 
     * @throws AdoHiveException
     */
    @After
    public void cleanUp() throws AdoHiveException {
        fc.remove();
        assistant.remove();
        contract.remove();
        course.remove();
        employment.remove();
    }

}
