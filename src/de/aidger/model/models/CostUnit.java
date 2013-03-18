/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
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

package de.aidger.model.models;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;
import java.util.ArrayList;
import java.util.List;
import siena.Column;
import siena.Table;

/**
 * This class represents a single cost unit.
 * 
 * @author aidGer Team
 */
@Table("Kostenstelle")
public class CostUnit extends AbstractModel<CostUnit> implements Comparable<CostUnit> {
    /**
     * The cost unit that is a 8 digit number.
     */
    @Column("Kostenstelle")
    private String costUnit = "";

    /**
     * The funds that is readable for humans.
     */
    @Column("Fonds")
    private String funds = "";

    /**
     * The token that is stored in the database for this cost unit.
     */
    @Column("TokenDB")
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
        costUnit.setId(id);
        costUnit.setCostUnit(this.costUnit);
        costUnit.setFunds(funds);
        costUnit.setTokenDB(tokenDB);
        return costUnit;
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
    
    /**
     * Get CostUnit from TokenDB
     * 
     * @param token Token to search for
     * @return The found CostUnit
     */
    public CostUnit fromTokenDB(String token) {
        return all().filter("tokenDB", token).get();
    }
    
    /**
     * Get a list of all distinct cost units
     * 
     * @return List of cost units
     */
    public List<String> getAllCostUnits() {
        List<String> costunits = new ArrayList<String>();
        List<CostUnit> lst = getAll();
        for (CostUnit c : lst) {
            if (!costunits.contains(c.getCostUnit())) {
                costunits.add(c.getCostUnit());
            }
        }
        return costunits;
    }

    @Override
    public int compareTo(CostUnit other) {
        return this.getFunds().compareTo(other.getFunds());
    }
};
