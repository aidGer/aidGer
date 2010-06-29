package de.aidger.model.models;

import java.math.BigDecimal;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.IHourlyWage;

/**
 * Represents a single entry in the hourly wage column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
public class HourlyWage extends AbstractModel<IHourlyWage> implements
        IHourlyWage {

    /**
     * The qualification needed for the wage
     */
    private String qualification;

    /**
     * The month the wage is valid in.
     */
    private Byte month;

    /**
     * The year the wage is valid in.
     */
    private Short year;

    /**
     * The wage per hour.
     */
    private BigDecimal wage;

    /**
     * Initializes the HourlyWage class.
     */
    public HourlyWage() {
        updatePKs = true;

        validatePresenceOf(new String[] { "qualification" }, new String[] {
                _("Qualification") });
        validateInclusionOf(new String[] { "qualification" }, new String[] {
                _("Qualification") }, new String[] {"g", "u", "b"});
    }

    /**
     * Initializes the HourlyWage class with the given hourly wage model.
     * 
     * @param h
     *            the hourly wage model
     */
    public HourlyWage(IHourlyWage h) {
        this();
        setId(h.getId());
        setMonth(h.getMonth());
        setQualification(h.getQualification());
        setWage(h.getWage());
        setYear(h.getYear());
        setNew(false);
    }

    /**
     * Clone the current wage
     */
    @Override
    public HourlyWage clone() {
        HourlyWage h = new HourlyWage();
        h.setMonth(month);
        h.setQualification(qualification);
        h.setWage(wage);
        h.setYear(year);
        h.doClone(this);
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
            /*
             * ID is not used anymore because the database table itself doesn't
             * contain one.
             */
            return (month == null ? h.month == null : month.equals(h.month))
                    && (year == null ? h.year == null : year.equals(h.year))
                    && (qualification == null ? h.qualification == null
                            : h.qualification.equals(qualification))
                    && (wage == null ? h.wage == null : wage.subtract(h.wage)
                            .doubleValue() <= 0.01);
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
        hash = 37 * hash
                + (qualification != null ? qualification.hashCode() : 0);
        hash = 37 * hash + (month != null ? month.hashCode() : 0);
        hash = 37 * hash + (year != null ? year.hashCode() : 0);
        hash = 37 * hash + (wage != null ? wage.hashCode() : 0);
        return hash;
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
     * Get the month the wage is valid in.
     * 
     * @return The month the wage is valid in
     */
    @Override
    public Byte getMonth() {
        return month;
    }

    /**
     * Get the qualification needed for the wage.
     * 
     * @return The qualification needed for the wage
     */
    @Override
    public String getQualification() {
        return qualification;
    }

    /**
     * Get the wage per hour.
     * 
     * @return The wage per hour
     */
    @Override
    public BigDecimal getWage() {
        return wage;
    }

    /**
     * Get the year the wage is valid in.
     * 
     * @return The year the wage is valid in
     */
    @Override
    public Short getYear() {
        return year;
    }

    /**
     * Set the month the wage is valid in.
     * 
     * @param month
     *            The month the wage is valid in
     */
    @Override
    public void setMonth(Byte month) {
        this.month = month;
    }

    /**
     * Set the qualification needed for the wage.
     * 
     * @param qual
     *            The qualification needed for the wage
     */
    @Override
    public void setQualification(String qual) {
        qualification = qual;
    }

    /**
     * Set the wage per hour.
     * 
     * @param wage
     *            The wage per hour
     */
    @Override
    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    /**
     * Set the year the wage is valid in.
     * 
     * @param year
     *            The year the wage is valid in.
     */
    @Override
    public void setYear(Short year) {
        this.year = year;
    }

}
