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

/**
 * 
 */
package de.aidger.model.controlling;

import java.math.BigDecimal;

/**
 * This class represents an assistant entry of a controlling report.
 * 
 * @author aidGer Team
 */
public class ControllingAssistant implements Comparable<ControllingAssistant> {

    /**
     * The name of this assistant
     */
    private String firstName;

    /**
     * The name of this assistant
     */
    private String lastName;

    /**
     * The total income of this assistant.
     */
    private double costs;

    /**
     * Whether there could've been an error in the calculation of the costs.
     */
    private boolean flagged;

    /**
     * Initializes a new controlling assistant.
     */
    public ControllingAssistant() {
        setFirstName("");
        setLastName("");
        setCosts(0);
        setFlagged(false);
    }

    /**
     * Sets the first name of this assistant.
     * 
     * @param name
     *            the name to set
     */
    public void setFirstName(String name) {
        this.firstName = name;
    }

    /**
     * Returns the first name of this assistant.
     * 
     * @return the name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the last name of this assistant.
     * 
     * @param name
     *            the name to set
     */
    public void setLastName(String name) {
        this.lastName = name;
    }

    /**
     * Returns the last name of this assistant.
     * 
     * @return the name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the costs of this assistant.
     * 
     * @param costs
     *            the costs to set
     */
    public void setCosts(double costs) {
        this.costs = costs;
    }

    /**
     * Returns the costs of this assistant.
     * 
     * @return the costs
     */
    public double getCosts() {
        return new BigDecimal(costs).setScale(2, BigDecimal.ROUND_HALF_EVEN)
            .doubleValue();
    }

    /**
     * Sets whether or not there might've been an error in the calculations.
     * 
     * @param flagged
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    /**
     * Whether or not there might've been an error in the calculations.
     * 
     * @return True if there might've been an error.
     */
    public boolean isFlagged() {
        return flagged;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(ControllingAssistant arg0) {
        // Sort by last name first and then first name
        int comparison = this.getLastName().toLowerCase().compareTo(
            arg0.getLastName().toLowerCase());
        if (comparison == 0) {
            comparison = this.getFirstName().toLowerCase().compareTo(
                arg0.getFirstName().toLowerCase());
        }
        return comparison;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
