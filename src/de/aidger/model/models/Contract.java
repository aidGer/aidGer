package de.aidger.model.models;

import static de.aidger.utils.Translation._;

import java.sql.Date;
import java.util.List;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IContract;

/**
 * Represents a single entry in the contract column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
public class Contract extends AbstractModel<IContract> implements IContract {

    /**
     * The date the contract was completed.
     */
    private Date completionDate;

    /**
     * The date the contract was confirmed.
     */
    private Date confirmationDate;

    /**
     * Determines if the contract has been delegated.
     */
    private boolean delegation;

    /**
     * The date the contract ends.
     */
    private Date endDate;

    /**
     * The date the contract starts.
     */
    private Date startDate;

    /**
     * The type of contract.
     */
    private String type;

    /**
     * Initializes the Contract class.
     */
    public Contract() {
        validatePresenceOf(new String[] { "completionDate", "confirmationDate",
                "endDate", "startDate", "type" }, new String[] {
                _("Completion Date"), _("Confirmation Date"), _("End Date"),
                _("Start Date"), _("Type") });
        validateDateRange("startDate", "endDate", _("End Date"), _("Start Date"));
        validateDateRange("completionDate", "confirmationDate",
                _("Completion Date"), _("Confirmation Date"));
    }

    /**
     * Initializes the Contract class with the given contract model.
     * 
     * @param c
     *            the contract model
     */
    public Contract(IContract c) {
        this();
        setId(c.getId());
        setCompletionDate(c.getCompletionDate());
        setConfirmationDate(c.getConfirmationDate());
        setDelegation(c.isDelegation());
        setEndDate(c.getEndDate());
        setStartDate(c.getStartDate());
        setType(c.getType());
        setNew(false);
    }

    /**
     * Clone the current contract
     */
    @Override
    public Contract clone() {
        Contract c = new Contract();
        c.setCompletionDate(completionDate);
        c.setConfirmationDate(confirmationDate);
        c.setDelegation(delegation);
        c.setEndDate(endDate);
        c.setStartDate(startDate);
        c.setType(type);
        c.doClone(this);
        return c;
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
        if (o instanceof Contract) {
            Contract c = (Contract) o;
            return c.id == id
                    && c.delegation == delegation
                    && (completionDate == null ? c.completionDate == null
                            : c.completionDate.toString().equals(
                                completionDate.toString()))
                    && (confirmationDate == null ? c.confirmationDate == null
                            : c.confirmationDate.toString().equals(
                                confirmationDate.toString()))
                    && (endDate == null ? c.endDate == null : c.endDate
                        .toString().equals(endDate.toString()))
                    && (startDate == null ? c.startDate == null : c.startDate
                        .toString().equals(startDate.toString()))
                    && (type == null ? c.type == null : c.type.equals(type));
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
        int hash = 7;
        hash = 41 * hash
                + (completionDate != null ? completionDate.hashCode() : 0);
        hash = 41 * hash
                + (confirmationDate != null ? confirmationDate.hashCode() : 0);
        hash = 41 * hash + (delegation ? 1 : 0);
        hash = 41 * hash + (endDate != null ? endDate.hashCode() : 0);
        hash = 41 * hash + (startDate != null ? startDate.hashCode() : 0);
        hash = 41 * hash + (type != null ? type.hashCode() : 0);
        return hash;
    }

    /**
     * Custom validation function.
     * 
     * @return True if everything is correct
     */
    public boolean validate() {
        boolean ret = true;
        if (type.length() > 20) {
            addError("type", _("Type"), _("is too long"));
            ret = false;
        }
        return ret;
    }

    /**
     * Custom validation function for remove().
     * 
     * @return True if everything is correct
     */
    public boolean validateOnRemove() throws AdoHiveException {
        boolean ret = true;

        List emps = (new Employment()).getEmployments(this);

        if (emps.size() > 0) {
            addError("Contract is still linked to an Employment");
            ret = false;
        }
        return ret;
    }

    /**
     * Get the date the contract was completed.
     * 
     * @return The date the contract was completed
     */
    @Override
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * Get the date the contract was confirmed.
     * 
     * @return The date the contract was confirmed
     */
    @Override
    public Date getConfirmationDate() {
        return confirmationDate;
    }

    /**
     * Get the date the contract ends.
     * 
     * @return The date the contract ends.
     */
    @Override
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Get the date the contract starts.
     * 
     * @return The date the contract starts
     */
    @Override
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Get the type of the contract.
     * 
     * @return The type of the contract
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * Has the contract been delegated.
     * 
     * @return True if the contract has been delegated
     */
    @Override
    public boolean isDelegation() {
        return delegation;
    }

    /**
     * Set the date the contract was completed.
     * 
     * @param date
     *            The date the contract was completed
     */
    @Override
    public void setCompletionDate(Date date) {
        completionDate = date;
    }

    /**
     * Set the date the contract was confirmed.
     * 
     * @param date
     *            The date the contract was confirmed
     */
    @Override
    public void setConfirmationDate(Date date) {
        confirmationDate = date;
    }

    /**
     * Has the contract been delegated?
     * 
     * @param del
     *            True if the contract has been delegated
     */
    @Override
    public void setDelegation(boolean del) {
        delegation = del;
    }

    /**
     * Set the date the contract ends.
     * 
     * @param date
     *            The date the contract ends
     */
    @Override
    public void setEndDate(Date date) {
        endDate = date;
    }

    /**
     * Set the date the contract starts.
     * 
     * @param date
     *            The date the contract starts
     */
    @Override
    public void setStartDate(Date date) {
        startDate = date;
    }

    /**
     * Set the type of the contract.
     * 
     * @param tp
     *            The type of the contract
     */
    @Override
    public void setType(String tp) {
        type = tp;
    }

}
