/**
 * 
 */
package de.aidger.model.reports;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This model represents the employments of one course for one time-frame for
 * one assistant in an activity report.
 * 
 * @author aidGer Team
 */
public class ActivityEmployment {
    /**
     * The years in which these employments take place.
     */
    private final ArrayList<Short> years;

    /**
     * The month in which these employments ended.
     */
    private final ArrayList<Byte> months;

    /**
     * The name of the course of these employments.
     */
    private String course;

    /**
     * The amount of hours the assistant worked in these employments.
     */
    private double hours;

    /**
     * Initializes a new ActivityEmployment.
     */
    public ActivityEmployment() {
        years = new ArrayList<Short>();
        months = new ArrayList<Byte>();
        course = "";
        hours = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return course;
    }

    /**
     * Adds a year to the vector of years if it doesn't exist yet.
     * 
     * @param value
     *            The year to add
     */
    public void addYear(short value) {
        if (!years.contains(value)) {
            years.add(value);
        }
    }

    /**
     * Returns the years of these employments.
     * 
     * @return The years
     */
    public ArrayList<Short> getYears() {
        return years;
    }

    /**
     * Adds a month to the vector of months of these employments if it doesn't
     * exist yet.
     * 
     * @param value
     *            The month
     */
    public void addMonth(byte value) {
        if (!months.contains(value)) {
            months.add(value);
        }
    }

    /**
     * Returns the months of these employments.
     * 
     * @return The months
     */
    public ArrayList<Byte> getMonths() {
        return months;
    }

    /**
     * Sets the course name of these employments.
     * 
     * @param course
     *            the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Returns the course name of these employments.
     * 
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * Adds hours to the hours of these employments.
     * 
     * @param hours
     *            the hours to add
     */
    public void addHours(double hours) {
        this.hours = this.hours + hours;
    }

    /**
     * Returns the hours of these employments.
     * 
     * @return the hours
     */
    public double getHours() {
        return new BigDecimal(hours).setScale(2, BigDecimal.ROUND_HALF_EVEN)
            .doubleValue();
    }
}
