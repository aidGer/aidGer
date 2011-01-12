package de.aidger.view.models;

import de.aidger.model.Runtime;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.CostUnit;
import de.aidger.view.tabs.ViewerTab.DataType;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * The UI assistant is used for prettier rendering of the model.
 * 
 * @author aidGer Team
 */
public class UIAssistant extends Assistant implements UIModel,
        Comparable<UIAssistant> {

    /**
     * The total hours the assistant is employed for a course.
     */
    private double totalHours = 0.0;

    /**
     * The funds the assistant is payed from.
     */
    private String funds = "";

    /**
     * Initializes the Assistant class.
     */
    public UIAssistant() {
    }

    /**
     * Initializes the Assistant class with the given assistant model.
     * 
     * @param a
     *            the assistant model
     */
    public UIAssistant(IAssistant a) {
        super(a);
    }

    /**
     * Sets the total hours for this assistant.
     * 
     * @param totalHours
     *            the total hours
     */
    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    /**
     * Sets the funds for this assistant.
     * 
     * @param tokenDB
     *            the database token of the funds
     */
    public void setFunds(String tokenDB) {
        CostUnit costUnit = Runtime.getInstance().getDataXMLManager()
            .fromTokenDB(tokenDB);
        funds = costUnit == null ? tokenDB : costUnit.getFunds();
    }

    /**
     * Returns the given string with the given length filled with spaces.
     * 
     * @param s
     *            the string
     * @param len
     *            the length of the new string
     * @return the given string with the given length filled with spaces
     */
    private String fillSpaces(String s, int len) {
        final StringBuilder builder = new StringBuilder(len);
        builder.append(s);

        for (int t = s.length(); t < len; ++t) {
            builder.append(' ');
        }

        return builder.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.model.AbstractModel#toString()
     */
    @Override
    public String toString() {
        String name = getFirstName() + " " + getLastName();

        if (getFirstName() == null || getLastName() == null) {
            return "";
        } else if (totalHours == 0.0) {
            return name;
        } else if (!funds.isEmpty()) {
            return fillSpaces(name, 28) + fillSpaces(totalHours + "h", 8)
                    + funds;
        } else {
            return name + " (" + totalHours + "h)";
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.UIModel#getDataType()
     */
    @Override
    public DataType getDataType() {
        return DataType.Assistant;
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
        if (o instanceof IAssistant) {
            IAssistant a = (IAssistant) o;
            return (getFirstName() == null ? a.getFirstName() == null : (a
                .getFirstName() == null ? false : a.getFirstName().equals(
                getFirstName())))
                    && (getLastName() == null ? a.getLastName() == null : (a
                        .getLastName() == null ? false : a.getLastName()
                        .equals(getLastName())))
                    && (getEmail() == null ? a.getEmail() == null : (a
                        .getEmail() == null ? false : a.getEmail().equals(
                        getEmail())))
                    && (getQualification() == null ? a.getQualification() == null
                            : (a.getQualification() == null ? false : a
                                .getQualification().equals(getQualification())));
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(UIAssistant o) {
        return toString().compareTo(o.toString());
    }
}
