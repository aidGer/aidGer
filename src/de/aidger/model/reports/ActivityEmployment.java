/**
 * 
 */
package de.aidger.model.reports;

import java.math.BigDecimal;

/**
 * This model represents the employments of one course for one time-frame for
 * one assistant in an activity report.
 * 
 * @author aidGer Team
 */
public class ActivityEmployment {
    /**
     * The year in which these employments began.
     */
    private short startYear;

    /**
     * The year in which these employments ended.
     */
    private short endYear;

    /**
     * The month in which these employments began.
     */
    private byte startMonth;

    /**
     * The month in which these employments ended.
     */
    private byte endMonth;

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
        startYear = endYear = 0;
        startMonth = endMonth = 0;
        course = "";
        hours = 0;
    }

    /**
     * Sets the starting year of these employments.
     * 
     * @param startYear
     *            the startYear to set
     */
    public void setStartYear(short startYear) {
        this.startYear = startYear;
    }

    /**
     * Returns the starting year of these employments.
     * 
     * @return the startYear
     */
    public short getStartYear() {
        return startYear;
    }

    /**
     * Sets the ending year of these employments.
     * 
     * @param endYear
     *            the endYear to set
     */
    public short setEndYear(short endYear) {
        this.endYear = endYear;
        return endYear;
    }

    /**
     * Returns the ending year of these employments.
     * 
     * @return the endYear
     */
    public short getEndYear() {
        return endYear;
    }

    /**
     * Sets the starting month of these employments.
     * 
     * @param startMonth
     *            the startMonth to set
     */
    public void setStartMonth(byte startMonth) {
        this.startMonth = startMonth;
    }

    /**
     * Returns the starting month of these employments.
     * 
     * @return the startMonth
     */
    public byte getStartMonth() {
        return startMonth;
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
     * Sets the ending month of these employments.
     * 
     * @param endMonth
     *            the endMonth to set
     */
    public byte setEndMonth(byte endMonth) {
        this.endMonth = endMonth;
        return endMonth;
    }

    /**
     * Returns the ending month of these employments.
     * 
     * @return the endMonth
     */
    public byte getEndMonth() {
        return endMonth;
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
