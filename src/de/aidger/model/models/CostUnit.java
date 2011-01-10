package de.aidger.model.models;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.unistuttgart.iste.se.adohive.controller.IAdoHiveManager;

/**
 * This class represents a single cost unit.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("unchecked")
public class CostUnit extends AbstractModel {
    /**
     * The cost unit that is a 8 digit number.
     */
    private String costUnit = "";

    /**
     * The funds that is readable for humans.
     */
    private String funds = "";

    /**
     * The token that is stored in the database for this cost unit.
     */
    private String tokenDB = "";

    /**
     * Creates a cost unit model.
     */
    public CostUnit() {
        if (getValidators().isEmpty()) {
            validatePresenceOf(new String[] { "costUnit", "funds", "tokenDB" },
                    new String[] { _("Cost unit"), _("Funds"), _("Database token") });
        }
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

    /**
     * Check if two objects are equal.
     * 
     * @param o
     *            The other object
     * @return True if both are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof CostUnit) {
            CostUnit c = (CostUnit) o;
            return (costUnit == null ? c.costUnit == null : costUnit
                .equals(c.costUnit))
                    && (funds == null ? c.funds == null : funds.equals(c.funds))
                    && (tokenDB == null ? c.tokenDB == null : tokenDB
                        .equals(c.tokenDB));
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#clone()
     */
    @Override
    public CostUnit clone() {
        CostUnit costUnit = new CostUnit();
        costUnit.setCostUnit(this.costUnit);
        costUnit.setFunds(funds);
        costUnit.setTokenDB(tokenDB);
        costUnit.doClone(this);
        return costUnit;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected IAdoHiveManager getManager() {
        return Runtime.getInstance().getDataXMLManager();
    }

    /**
     * Custom validation function.
     * 
     * @return True if the validation is successful
     */
    public boolean validate() {
        boolean ret = true;

        if (costUnit.length() != 8) {
            addError("costUnit", _("Cost unit"), _("has to have a length of 8"));
            ret = false;
        }

        return ret;
    }
};
