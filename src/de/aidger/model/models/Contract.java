package de.aidger.model.models;

import java.util.Date;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IContract;

/**
 * Represents a single entry in the contract column of the database.
 * Contains functions to retrieve and change the data in the database.
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
                "endDate", "startDate", "type" });
        validateDateRange("startDate", "endDate");
        validateDateRange("completionDate", "confirmationDate");        
        //TODO: Validate the type
    }

    /**
     * Clone the current contract
     */
    @Override
    public Contract clone()	{
        Contract c = new Contract();
        c.setId(id);
        c.setCompletionDate(completionDate);
        c.setConfirmationDate(confirmationDate);
        c.setDelegation(delegation);
        c.setEndDate(endDate);
        c.setStartDate(startDate);
        c.setType(type);
        return c;
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
     * @param date The date the contract was completed
     */
    @Override
    public void setCompletionDate(Date date) {
        completionDate = date;
    }

    /**
     * Set the date the contract was confirmed.
     *
     * @param date The date the contract was confirmed
     */
    @Override
    public void setConfirmationDate(Date date) {
        confirmationDate = date;
    }

    /**
     * Has the contract been delegated?
     *
     * @param del True if the contract has been delegated
     */
    @Override
    public void setDelegation(boolean del) {
        delegation = del;
    }

    /**
     * Set the date the contract ends.
     *
     * @param date The date the contract ends
     */
    @Override
    public void setEndDate(Date date) {
        endDate = date;
    }

    /**
     * Set the date the contract starts.
     *
     * @param date The date the contract starts
     */
    @Override
    public void setStartDate(Date date) {
        startDate = date;
    }

    /**
     * Set the type of the contract.
     *
     * @param tp The type of the contract
     */
    @Override
    public void setType(String tp) {
        type = tp;
    }

}
