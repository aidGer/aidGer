package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;
import java.util.Arrays;

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
    private int[]  budgetCosts;

    /**
     * The fonds of the category.
     */
    private int[]  fonds;

    /**
     * The year the category is valid.
     */
    private short  year;

    /**
     * Initialize the FinancialCategory class.
     */
    public FinancialCategory() {
        validatePresenceOf(new String[] { "name" });
        //TODO: Validate the budget costs and fonds
        //TODO: Validate the year
    }

    /**
     * Clone the current category.
     */
    @Override
    public FinancialCategory clone() {
        FinancialCategory f = new FinancialCategory();
        f.setId(id);
        f.setBugdetCosts(budgetCosts);
        f.setFonds(fonds);
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
        if (o != null && o instanceof FinancialCategory) {
            FinancialCategory f = (FinancialCategory) o;
            return f.id == id && f.budgetCosts == budgetCosts &&
                    f.fonds == fonds && f.name.equals(name) && f.year == year;
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
        hash = 37 * hash + Arrays.hashCode(fonds);
        hash = 37 * hash + year;
        return hash;
    }

    /**
     * Get the budget costs of the category.
     * 
     * @return The budget costs of the category
     */
    @Override
    public int[] getBugdetCosts() {
        return budgetCosts;
    }

    /**
     * Get the fonds of the category.
     * 
     * @return The fonds of the category
     */
    @Override
    public int[] getFonds() {
        return fonds;
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
    public void setBugdetCosts(int[] costs) {
        budgetCosts = costs;
    }

    /**
     * Set the fonds of the category.
     * 
     * @param fonds
     *            The fonds of the category
     */
    @Override
    public void setFonds(int[] fonds) {
        this.fonds = fonds;
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
