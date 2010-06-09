package de.aidger.model.models;

import static de.aidger.utils.Translation._;

import java.util.Arrays;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

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
    private int[] budgetCosts;

    /**
     * The fonds of the category.
     */
    private int[] funds;

    /**
     * The year the category is valid.
     */
    private short year;

    /**
     * Initialize the FinancialCategory class.
     */
    public FinancialCategory() {
        validatePresenceOf(new String[] { "name" });
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
    }

    /**
     * Clone the current category.
     */
    @Override
    public FinancialCategory clone() {
        FinancialCategory f = new FinancialCategory();
        f.setId(id);
        f.setBudgetCosts(budgetCosts);
        f.setFunds(funds);
        f.setName(name);
        f.setYear(year);
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
            return f.id == id && Arrays.equals(f.budgetCosts, budgetCosts)
                    && Arrays.equals(f.funds, funds) && f.year == year
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
        hash = 37 * hash + year;
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
            addError("year", _("is an incorrect year"));
            ret = false;
        }

        for (int b : budgetCosts) {
            if (b <= 0) {
                addError("budgetCosts", _("can't be zero."));
                ret = false;
            }
        }

        for (int f : funds) {
            if (String.valueOf(f).length() != 8) {
                addError("funds", _("has to have a length of 8"));
                ret = false;
            }
        }

        return ret;
    }

    /**
     * Get the budget costs of the category.
     * 
     * @return The budget costs of the category
     */
    @Override
    public int[] getBudgetCosts() {
        return budgetCosts;
    }

    /**
     * Get the funds of the category.
     * 
     * @return The funds of the category
     */
    @Override
    public int[] getFunds() {
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
    public short getYear() {
        return year;
    }

    /**
     * Set the budget costs of the category.
     * 
     * @param costs
     *            The budget costs of the category
     */
    @Override
    public void setBudgetCosts(int[] costs) {
        budgetCosts = costs;
    }

    /**
     * Set the funds of the category.
     * 
     * @param funds
     *            The funds of the category
     */
    @Override
    public void setFunds(int[] funds) {
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
    public void setYear(short year) {
        this.year = year;
    }

}
