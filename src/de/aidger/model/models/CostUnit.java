package de.aidger.model.models;

import de.aidger.model.AbstractModel;

/**
 * This class represents a single cost unit.
 * 
 * @author aidGer Team
 */
public class CostUnit extends AbstractModel {
    /**
     * The cost unit that is a 8 digit number.
     */
    private String costUnit;

    /**
     * The funds that is readable for humans.
     */
    private String funds;

    /**
     * The token that is stored in the database for this cost unit.
     */
    private String tokenDB;

    /**
     * Creates a cost unit model.
     */
    public CostUnit() {
        this.costUnit = "";
        this.funds = "";
        this.tokenDB = "";
    }

    /**
     * Returns the cost unit as 8 digit number.
     * 
     * @return the cost unit as 8 digit number
     */
    public String getCostUnit() {
        return costUnit;
    }

    /**
     * Sets the cost unit.
     * 
     * @param costUnit
     *            the cost unit as string
     */
    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }

    /**
     * Returns the funds that is readable for humans.
     * 
     * @return the funds that is readable for humans.
     */
    public String getFunds() {
        return funds;
    }

    /**
     * Sets the funds.
     * 
     * @param funds
     *            the funds as string
     */
    public void setFunds(String funds) {
        this.funds = funds;
    }

    /**
     * Returns the token that is stored in the database.
     * 
     * @return the token that is stored in the database
     */
    public String getTokenDB() {
        return tokenDB;
    }

    /**
     * Sets the database token.
     * 
     * @param tokenDB
     *            the database token
     */
    public void setTokenDB(String tokenDB) {
        this.tokenDB = tokenDB;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return funds + " (" + tokenDB + ")";
    }

    @Override
    public Object clone() {
        return null;
    }
};
