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

import java.sql.Date;
import java.util.List;
import java.util.ArrayList;

import de.aidger.model.AbstractModel;
import de.aidger.model.validators.DateRangeValidator;
import de.unistuttgart.iste.se.adohive.controller.IContractManager;
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
     * References the corresponding assistant.
     */
    private Integer assistantId;

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
    private Boolean delegation;

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
        if (getValidators().isEmpty()) {
            validatePresenceOf(new String[] { "completionDate", "endDate",
                    "startDate", "type" }, new String[] { _("Completion date"),
                    _("End date"), _("Start date"), _("Type") });
            validateDateRange("startDate", "endDate", _("End date"),
                _("Start date"));
            validateExistanceOf(new String[] { "assistantId" },
                new String[] { _("Assistant") }, new Assistant());
        }
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
        setAssistantId(c.getAssistantId());
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
        c.setAssistantId(assistantId);
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
            return (id == null ? c.id == null : id.equals(c.id))
                    && (assistantId == null ? c.assistantId == null
                            : assistantId.equals(c.assistantId))
                    && (delegation == null ? c.delegation == null : delegation
                        .equals(c.delegation))
                    && (completionDate == null ? c.completionDate == null :
                        (c.completionDate == null ? false :
                        completionDate.toString().equals(c.completionDate.toString())))
                    && (confirmationDate == null ? c.confirmationDate == null :
                        (c.confirmationDate == null ? false :
                        confirmationDate.toString().equals(c.confirmationDate.toString())))
                    && (endDate == null ? c.endDate == null : (c.endDate == null ?
                        false : endDate.toString().equals(c.endDate.toString())))
                    && (startDate == null ? c.startDate == null : (c.startDate == null ?
                        false : startDate.toString().equals(c.startDate.toString())))
                    && (type == null ? c.type == null : type.equals(c.type));
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
        int hash = 5;
        hash = 83 * hash + (assistantId != null ? assistantId.hashCode() : 0);
        hash = 83 * hash
                + (completionDate != null ? completionDate.hashCode() : 0);
        hash = 83 * hash
                + (confirmationDate != null ? confirmationDate.hashCode() : 0);
        hash = 83 * hash + (delegation != null ? delegation.hashCode() : 0);
        hash = 83 * hash + (endDate != null ? endDate.hashCode() : 0);
        hash = 83 * hash + (startDate != null ? startDate.hashCode() : 0);
        hash = 83 * hash + (type != null ? type.hashCode() : 0);
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
        if (confirmationDate != null
                && !DateRangeValidator.validate(completionDate,
                    confirmationDate)) {
            addError(_("The date range Completiondate and Confirmationdate is incorrect"));
            ret = false;
        }
        return ret;
    }

    /**
     * Custom validation function for remove().
     * 
     * @return True if everything is correct
     * @throws AdoHiveException
     */
    public boolean validateOnRemove() throws AdoHiveException {
        boolean ret = true;

        List emps = (new Employment()).getEmployments(this);

        if (emps.size() > 0) {
            addError(_("Contract is still linked to an Employment"));
            ret = false;
        }
        return ret;
    }

    /**
     * Get a list of contracts valid in the date range
     * 
     * @param start
     *            Start of the date range
     * @param end
     *            End of the date range
     * @return List of contracts
     * @throws AdoHiveException
     */
    public List<Contract> getContracts(Date start, Date end)
            throws AdoHiveException {
        IContractManager mgr = (IContractManager) getManager();
		return castList(mgr.getContracts(start, end));
    }

    /**
     * Get a list of contracts associated to the specified assistant.
     *
     * @param assi
     *          The assistant to search for
     * @return List of contracts
     * @throws AdoHiveException
     */
    public List<Contract> getContracts(Assistant assi) 
			throws AdoHiveException {
		IContractManager mgr = (IContractManager) getManager();
		return castList(mgr.getContracts(assi));
    }

    /**
     * Get the id of the corresponding assistant.
     * 
     * @return The id
     */
    public Integer getAssistantId() {
        return assistantId;
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
    public Boolean isDelegation() {
        return delegation;
    }

    /**
     * Set the id of the corresponding assistant.
     * 
     * @param id
     *            The id
     */
    public void setAssistantId(Integer id) {
        assistantId = id;
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
    public void setDelegation(Boolean del) {
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

    /**
     * Cast the list from interface to real class.
     * 
     * @param list
     *            The list to cast
     * @return The new list
     */
    protected List<Contract> castList(List<IContract> list) {
		List<Contract> ret = new ArrayList<Contract>();
		for (IContract c : list) {
			ret.add(new Contract(c));
		}
		return ret;
	}

}
