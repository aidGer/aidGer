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
public class ControllingAssistant {

    /**
     * The name of this assistant
     */
    private String name;

    /**
     * The total income of this assistant.
     */
    private double costs;

    /**
     * Initializes a new controlling assistant.
     */
    public ControllingAssistant() {
        setName("");
        setCosts(0);
    }

    /**
     * Sets the name of this assistant.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this assistant.
     * 
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets this assistant as an object array.
     * 
     * @return The assistant
     */
    public Object[] getObjectArray() {
        return new Object[] { name, getCosts() };
    }
}
