package de.aidger.model.models;

import static de.aidger.utils.Translation._;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

import java.util.Arrays;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.controller.IFinancialCategoryManager;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import java.util.List;

/**
 * Represents a single entry in the financial category column of the database.
 * Contains functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
public class FinancialCategory extends AbstractModel<IFinancialCategory>
        implements IFinancialCategory {

    /**
     * The name of the category.
     */
    private String name;

    /**
     * The budget costs of the category.
     */
    private Integer[] budgetCosts;

    /**
     * The fonds of the category.
     */
    private Integer[] funds;

    /**
     * The year the category is valid.
     */
    private Short year;

    /**
     * Initialize the FinancialCategory class.
     */
    public FinancialCategory() {
        updatePKs = true;

        validatePresenceOf(new String[] { "name" }, new String[] { _("Name") });
    }

    /**
     * Initialize the FinancialCategory class with the given financial category
     * model.
     * 
     * @param f
     *            the financial category model
     */
    public FinancialCategory(IFinancialCategory f) {
        this();
        setId(f.getId());
        setBudgetCosts(f.getBudgetCosts());
        setFunds(f.getFunds());
        setName(f.getName());
        setYear(f.getYear());
        setNew(false);
    }

    /**
     * Clone the current category.
     */
    @Override
    public FinancialCategory clone() {
        FinancialCategory f = new FinancialCategory();
        f.setBudgetCosts(budgetCosts);
        f.setFunds(funds);
        f.setName(name);
        f.setYear(year);
        f.doClone(this);
        return f;
    }

    /**
     * Check if two objects are equal.
     * 
     * @param o
     *            The other object
     * @return True if both are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof FinancialCategory) {
            FinancialCategory f = (FinancialCategory) o;
            return (id == null ? f.id == null : id.equals(f.id))
                    && Arrays.equals(f.budgetCosts, budgetCosts)
                    && Arrays.equals(f.funds, funds)
                    && (year == null ? f.year == null : year.equals(f.year))
                    && (name == null ? f.name == null : f.name.equals(name));
        } else {
            return false;
        }
    }

    /**
     * Generate a unique hashcode for this instance.
     * 
     * @return The hashcode
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (name != null ? name.hashCode() : 0);
        hash = 37 * hash + Arrays.hashCode(budgetCosts);
        hash = 37 * hash + Arrays.hashCode(funds);
        hash = 37 * hash + (year != null ? year.hashCode() : 0);
        return hash;
    }

    /**
     * Custom validation function.
     *
     * @return True if the validation is successfull
     */
    public boolean validate() {
        boolean ret = true;
        if (year <= 1000 || year >= 10000) {
            addError("year", _("Year"), _("is an incorrect year"));
            ret = false;
        }

        for (int b : budgetCosts) {
            if (b <= 0) {
                addError("budgetCosts", _("Budget Costs"), _("can't be zero."));
                ret = false;
            }
        }

        for (int f : funds) {
            if (String.valueOf(f).length() != 8) {
                addError("funds", _("Funds"), _("has to have a length of 8"));
                ret = false;
            }
        }

        return ret;
    }

    /**
     * Custom validation function for remove().
     *
     * @return True if everything is correct
     * @throws AdoHiveException
     */
    public boolean validateOnRemove() throws AdoHiveException {
        boolean ret = true;

        List courses = (new Course()).getCourses(this);

        if (courses.size() > 0) {
            addError(_("Financial Category is still linked to a Course."));
            ret = false;
        }

        return ret;
    }

    /**
     * Get a list of distinct funds.
     *
     * @return List of distinct funds
     * @throws AdoHiveException
     */
    public List<Integer> getDistinctFunds() throws AdoHiveException {
        IFinancialCategoryManager mgr = (IFinancialCategoryManager) getManager();
        return mgr.getDistinctFunds();
    }

    /**
     * Get the budget costs of the category.
     * 
     * @return The budget costs of the category
     */
    @Override
    public Integer[] getBudgetCosts() {
        return budgetCosts;
    }

    /**
     * Get the funds of the category.
     * 
     * @return The funds of the category
     */
    @Override
    public Integer[] getFunds() {
        return funds;
    }

    /**
     * Get the name of the category.
     * 
     * @return The name of the category
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the year the category is valid.
     * 
     * @return The year the category is valid
     */
    @Override
    public Short getYear() {
        return year;
    }

    /**
     * Set the budget costs of the category.
     * 
     * @param costs
     *            The budget costs of the category
     */
    @Override
    public void setBudgetCosts(Integer[] costs) {
        budgetCosts = costs;
    }

    /**
     * Set the funds of the category.
     * 
     * @param funds
     *            The funds of the category
     */
    @Override
    public void setFunds(Integer[] funds) {
        this.funds = funds;
    }

    /**
     * Set the name of the category.
     * 
     * @param name
     *            The name of the category
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the year the category is valid.
     * 
     * @param year
     *            The year the category is valid.
     */
    @Override
    public void setYear(Short year) {
        this.year = year;
    }

}
