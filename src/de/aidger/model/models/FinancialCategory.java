package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IFinancialCategory;

/**
 * Represents a single entry in the financial category column of the database.
 * Contains functions to retrieve and change the data in the database.
 *
 * @author aidGer Team
 */
public class FinancialCategory extends AbstractModel implements
		IFinancialCategory {

	/**
	 * The unique id of the category.
	 */
	private int id;

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
	private int[] fonds;

	/**
	 * The year the category is valid.
	 */
	private short year;

	/**
	 * Clone the current category.
	 */
	@Override
	public FinancialCategory clone()
	{
		return new FinancialCategory();
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
	 * @param costs The budget costs of the category
	 */
	@Override
	public void setBugdetCosts(int[] costs) {
		budgetCosts = costs;
	}

	/**
	 * Set the fonds of the category.
	 *
	 * @param fonds The fonds of the category
	 */
	@Override
	public void setFonds(int[] fonds) {
		this.fonds = fonds;
	}

	/**
	 * Set the name of the category.
	 *
	 * @param name The name of the category
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the year the category is valid.
	 *
	 * @param year The year the category is valid.
	 */
	@Override
	public void setYear(short year) {
		this.year = year;
	}

	/**
	 * Returns the unique id of the category.
	 *
	 * @return The unique id of the category
	 */
	@Override
	public int getId() {
		return id;
	}

	/**
	 * Set the unique id of the category.
	 *
	 * <b>!!! THIS IS FOR INTERNAL ADOHIVE USAGE ONLY !!!</b>
	 *
	 * @param id The unique id of the category
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

}
