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

import siena.Table;
import siena.Column;

import de.aidger.model.AbstractModel;

/**
 * Represents a single entry in the hourly wage column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
@Table("Stundenlohn")
public class HourlyWage extends AbstractModel<HourlyWage> {

    /**
     * The qualification needed for the wage
     */
    @Column("Qualifikation")
    private String qualification;

    /**
     * The month the wage is valid in.
     */
    @Column("Monat")
    private Byte month;

    /**
     * The year the wage is valid in.
     */
    @Column("Jahr")
    private Short year;

    /**
     * The wage per hour.
     */
    @Column("Lohn")
    private Double wage;

    /**
     * Initializes the HourlyWage class.
     */
    public HourlyWage() {
        if (getValidators().isEmpty()) {
            validatePresenceOf(new String[] { "qualification", "wage" },
                    new String[] { _("Qualification"), _("Wage") });
            validateInclusionOf(new String[] { "qualification" },
                    new String[] { _("Qualification") }, new String[] { "g", "u", "b" });
        }
    }

    /**
     * Initializes the HourlyWage class with the given hourly wage model.
     * 
     * @param h
     *            the hourly wage model
     */
    public HourlyWage(HourlyWage h) {
        this();
        setId(h.getId());
        setMonth(h.getMonth());
        setQualification(h.getQualification());
        setWage(h.getWage());
        setYear(h.getYear());
    }

    /**
     * Clone the current wage
     */
    @Override
    public HourlyWage clone() {
        HourlyWage h = new HourlyWage();
        h.setId(id);
        h.setMonth(month);
        h.setQualification(qualification);
        h.setWage(wage);
        h.setYear(year);
        return h;
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
        if (o instanceof HourlyWage) {
            HourlyWage h = (HourlyWage) o;
            return (month == null ? h.month == null : month.equals(h.month))
                    && (year == null ? h.year == null : year.equals(h.year))
                    && (qualification == null ? h.qualification == null
                            : h.qualification.equals(qualification))
                    && (wage == null ? h.wage == null : wage - h.wage <= 0.01);
        } else {
            return false;
        }
    }

    /**
     * Custom validation function.
     * 
     * @return True if the validation is successfull
     */
    public boolean validate() {
        boolean ret = true;
        if (year <= 1000 || year >= 10000) {
            addError("year", _("Year"), _("is an incorrect year"));
            ret = false;
        }
        if (month <= 0 || month >= 13) {
            addError("month", _("Month"), _("is an incorrect month"));
            ret = false;
        }
        return ret;
    }

    /**
     * Get an HourlyWage by its three unique keys
     *
     * @param q
     *      The qualification
     * @param m
     *      The month
     * @param y
     *      The year
     * @return The HourlyWage matching those three keys
     */
    public HourlyWage getByKeys(String q, byte m, short y) {
        return all().filter("qualification", q).filter("month", m).filter("year", y).get();
    }

    /**
     * Get the month the wage is valid in.
     * 
     * @return The month the wage is valid in
     */
    public Byte getMonth() {
        return month;
    }

    /**
     * Get the qualification needed for the wage.
     * 
     * @return The qualification needed for the wage
     */
    public String getQualification() {
        return qualification;
    }

    /**
     * Get the wage per hour.
     * 
     * @return The wage per hour
     */
    public Double getWage() {
        return wage;
    }

    /**
     * Get the year the wage is valid in.
     * 
     * @return The year the wage is valid in
     */
    public Short getYear() {
        return year;
    }

    /**
     * Set the month the wage is valid in.
     * 
     * @param month
     *            The month the wage is valid in
     */
    public void setMonth(Byte month) {
        this.month = month;
    }

    /**
     * Set the qualification needed for the wage.
     * 
     * @param qual
     *            The qualification needed for the wage
     */
    public void setQualification(String qual) {
        qualification = qual;
    }

    /**
     * Set the wage per hour.
     * 
     * @param wage
     *            The wage per hour
     */
    public void setWage(Double wage) {
        this.wage = wage;
    }

    /**
     * Set the year the wage is valid in.
     * 
     * @param year
     *            The year the wage is valid in.
     */
    public void setYear(Short year) {
        this.year = year;
    }

}
