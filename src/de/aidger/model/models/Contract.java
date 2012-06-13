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
import java.util.ArrayList;
import java.util.List;

import siena.Table;
import siena.Column;

import de.aidger.model.AbstractModel;
import de.aidger.model.validators.DateRangeValidator;

/**
 * Represents a single entry in the contract column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
@Table("Vertrag")
public class Contract extends AbstractModel<Contract> {

    /**
     * References the corresponding assistant.
     */
    @Column("Hilfskraft_ID")
    private Long assistantId;

    /**
     * The date the contract was completed.
     */
    @Column("DatumAbschluss")
    private Date completionDate;

    /**
     * The date the contract was confirmed.
     */
    @Column("DatumBestaetigung")
    private Date confirmationDate;

    /**
     * Determines if the contract has been delegated.
     */
    @Column("Delegation")
    private Boolean delegation;

    /**
     * The date the contract ends.
     */
    @Column("DatumEnde")
    private Date endDate;

    /**
     * The date the contract starts.
     */
    @Column("DatumAnfang")
    private Date startDate;

    /**
     * The type of contract.
     */
    @Column("Art")
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
            validateExistenceOf(new String[]{"assistantId"},
                    new String[]{_("Assistant")}, new Assistant());
        }
    }

    /**
     * Initializes the Contract class with the given contract model.
     * 
     * @param c
     *            the contract model
     */
    public Contract(Contract c) {
        this();
        setId(c.getId());
        setAssistantId(c.getAssistantId());
        setCompletionDate(c.getCompletionDate());
        setConfirmationDate(c.getConfirmationDate());
        setDelegation(c.isDelegation());
        setEndDate(c.getEndDate());
        setStartDate(c.getStartDate());
        setType(c.getType());
    }

    /**
     * Clone the current contract
     */
    @Override
    public Contract clone() {
        Contract c = new Contract();
        c.setId(id);
        c.setAssistantId(assistantId);
        c.setCompletionDate(completionDate);
        c.setConfirmationDate(confirmationDate);
        c.setDelegation(delegation);
        c.setEndDate(endDate);
        c.setStartDate(startDate);
        c.setType(type);
        return c;
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
     */
    public boolean validateOnRemove() {
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
     */
    public List<Contract> getContracts(Date start, Date end) {
    	List<Contract> contracts = all().filter("startDate >=", start).filter("startDate <=", end).fetch();
    	for(Contract contract : all().filter("endDate >=", start).filter("endDate <=", end).fetch())
    		if(!contracts.contains(contract)) 
    			contracts.add(contract);
    	for(Contract contract : all().filter("completionDate >=", start).filter("completionDate <=", end).fetch())
    		if(!contracts.contains(contract)) 
    			contracts.add(contract);
    	for(Contract contract : all().filter("confirmationDate >=", start).filter("confirmationDate <=", end).fetch())
    		if(!contracts.contains(contract)) 
    			contracts.add(contract);
    	return contracts;
    }

    /**
     * Get a list of contracts associated to the specified assistant.
     *
     * @param assi
     *          The assistant to search for
     * @return List of contracts
     */
    public List<Contract> getContracts(Assistant assistant) {
        return all().filter("assistantId", assistant.getId()).fetch();
    }

    /**
     * Get the id of the corresponding assistant.
     * 
     * @return The id
     */
    public Long getAssistantId() {
        return assistantId;
    }

    /**
     * Get the date the contract was completed.
     * 
     * @return The date the contract was completed
     */
    public Date getCompletionDate() {
        return completionDate;
    }

    /**
     * Get the date the contract was confirmed.
     * 
     * @return The date the contract was confirmed
     */
    public Date getConfirmationDate() {
        return confirmationDate;
    }

    /**
     * Get the date the contract ends.
     * 
     * @return The date the contract ends.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Get the date the contract starts.
     * 
     * @return The date the contract starts
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Get the type of the contract.
     * 
     * @return The type of the contract
     */
    public String getType() {
        return type;
    }

    /**
     * Has the contract been delegated.
     * 
     * @return True if the contract has been delegated
     */
    public Boolean isDelegation() {
        return delegation;
    }

    /**
     * Set the id of the corresponding assistant.
     * 
     * @param id
     *            The id
     */
    public void setAssistantId(Long id) {
        assistantId = id;
    }

    /**
     * Set the date the contract was completed.
     * 
     * @param date
     *            The date the contract was completed
     */
    public void setCompletionDate(Date date) {
        completionDate = date;
    }

    /**
     * Set the date the contract was confirmed.
     * 
     * @param date
     *            The date the contract was confirmed
     */
    public void setConfirmationDate(Date date) {
        confirmationDate = date;
    }

    /**
     * Has the contract been delegated?
     * 
     * @param del
     *            True if the contract has been delegated
     */
    public void setDelegation(Boolean del) {
        delegation = del;
    }

    /**
     * Set the date the contract ends.
     * 
     * @param date
     *            The date the contract ends
     */
    public void setEndDate(Date date) {
        endDate = date;
    }

    /**
     * Set the date the contract starts.
     * 
     * @param date
     *            The date the contract starts
     */
    public void setStartDate(Date date) {
        startDate = date;
    }

    /**
     * Set the type of the contract.
     * 
     * @param tp
     *            The type of the contract
     */
    public void setType(String tp) {
        type = tp;
    }

}
