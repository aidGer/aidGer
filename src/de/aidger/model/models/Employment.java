package de.aidger.model.models;

import java.util.Date;
import java.util.List;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.controller.IEmploymentManager;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * Represents a single entry in the employment column of the database. Contains
 * functions to retrieve and change the data in the database.
 *
 * @author aidGer Team
 */
public class Employment extends AbstractModel<IEmployment> implements
        IEmployment {

    /**
     * References the corresponding assistant.
     */
    private int    assistantId;

    /**
     * References the corresponding contract.
     */
    private int    contractId;

    /**
     * References the corresponding course.
     */
    private int    courseId;

    /**
     * The fonds of the employment.
     */
    private int    fonds;

    /**
     * The month in which the employment took place.
     */
    private byte   month;

    /**
     * The year in which the employment took place.
     */
    private short  year;

    /**
     * The hours worked during the employment.
     */
    private double hourCount;

    /**
     * The cost unit given by the university.
     */
    private String costUnit;

    /**
     * Remarks regarding the employment.
     */
    private String remark;

    /**
     * Initializes the Employment class.
     */
    public Employment() {
        validatePresenceOf(new String[] { "costUnit" });
        //TODO: Validate the Ids
        //TODO: Validate the fonds
        //TODO: Validate the hourCount
    }

    /**
     * Clone the current employment.
     */
    @Override
    public Employment clone() {
        Employment e = new Employment();
        e.setId(id);
        e.setAssistantId(assistantId);
        e.setContractId(contractId);
        e.setCostUnit(costUnit);
        e.setFonds(fonds);
        e.setHourCount(hourCount);
        e.setMonth(month);
        e.setRemark(remark);
        e.setYear(year);
        return e;
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
        if (o instanceof Employment) {
            Employment e = (Employment) o;
            return e.id == id && e.assistantId == assistantId &&
                    e.contractId == contractId && e.fonds == fonds &&
                    e.courseId == courseId && e.month == month && 
                    e.year == year &&
                    (costUnit == null ? e.costUnit == null : 
                            e.costUnit.equals(costUnit)) &&
                    ((Double) e.hourCount).equals(hourCount) &&
                    (remark == null ? e.remark == null : 
                            e.remark.equals(remark));
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
        hash = 79 * hash + assistantId;
        hash = 79 * hash + contractId;
        hash = 79 * hash + courseId;
        hash = 79 * hash + fonds;
        hash = 79 * hash + month;
        hash = 79 * hash + year;
        hash = 79 * hash + (int) (Double.doubleToLongBits(hourCount) ^
                (Double.doubleToLongBits(hourCount) >>> 32));
        hash = 79 * hash + (costUnit != null ? costUnit.hashCode() : 0);
        hash = 79 * hash + (remark != null ? remark.hashCode() : 0);
        return hash;
    }

    /**
     * Get all employments during the given time.
     *
     * @param start
     * 			The start date
     * @param end
     * 			The end date
     * @return The employments during the given time
     */
    @SuppressWarnings("unchecked")
    public List<Employment> getEmployments(Date start, Date end) {
    	IEmploymentManager mgr = (IEmploymentManager)getManager();
    	return (List<Employment>)(List<?>)mgr.getEmployments(start, end);
    }

    /**
     * Get all employments for the semester.
     *
     * @param semester
     * 			The semester
     * @return The employments for the semester
     */
    @SuppressWarnings("unchecked")
    public List<Employment> getEmployments(String semester) {
    	IEmploymentManager mgr = (IEmploymentManager)getManager();
    	return (List<Employment>)(List<?>)mgr.getEmployments(semester);
    }

    /**
     * Get the id referencing the assistant.
     *
     * @return The id of the assistant
     */
    @Override
    public int getAssistantId() {
        return assistantId;
    }

    /**
     * Get the id referencing the contract.
     *
     * @return The id of the contract
     */
    @Override
    public int getContractId() {
        return contractId;
    }

    /**
     * Get the cost unit of the contract.
     *
     * @return The cost unit of the contract
     */
    @Override
    public String getCostUnit() {
        return costUnit;
    }

    /**
     * Get the id referencing the course.
     *
     * @return The id of the course
     */
    @Override
    public int getCourseId() {
        return courseId;
    }

    /**
     * Get the fonds of the employment.
     *
     * @return The fonds of the employment
     */
    @Override
    public int getFonds() {
        return fonds;
    }

    /**
     * Get the hours worked during the employment.
     *
     * @return The hours worked during the employment
     */
    @Override
    public double getHourCount() {
        return hourCount;
    }

    /**
     * Get the month of the employment.
     *
     * @return The month of the employment
     */
    @Override
    public byte getMonth() {
        return month;
    }

    /**
     * Get the remarks regarding the employment.
     *
     * @return The remarks regarding the employment
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * Get the year of the employment.
     *
     * @return The year of the employment
     */
    @Override
    public short getYear() {
        return year;
    }

    /**
     * Set the id referencing the assistant.
     *
     * @param id
     *            The id of the assistant
     */
    @Override
    public void setAssistantId(int id) {
        assistantId = id;
    }

    /**
     * Set the id referencing the contract.
     *
     * @param id
     *            The id of the contract
     */
    @Override
    public void setContractId(int id) {
        contractId = id;
    }

    /**
     * Set the cost unit of the contract.
     *
     * @param cost
     *            The cost unit of the contract
     */
    @Override
    public void setCostUnit(String cost) {
        costUnit = cost;
    }

    /**
     * Set the id referencing the course.
     *
     * @param id
     *            The id of the course
     */
    @Override
    public void setCourseId(int id) {
        courseId = id;
    }

    /**
     * Set the fonds of the employment.
     *
     * @param fonds
     *            The fonds of the employment
     */
    @Override
    public void setFonds(int fonds) {
        this.fonds = fonds;
    }

    /**
     * Set the hours worked during the employment.
     *
     * @param hours
     *            The hours worked during the employment
     */
    @Override
    public void setHourCount(double hours) {
        hourCount = hours;
    }

    /**
     * Set the month of the employment.
     *
     * @param month
     *            The month of the employment
     */
    @Override
    public void setMonth(byte month) {
        this.month = month;

    }

    /**
     * Set the remarks regarding the employment.
     *
     * @param rem
     *            The remarks regarding the employment
     */
    @Override
    public void setRemark(String rem) {
        remark = rem;
    }

    /**
     * Set the year of the employment.
     *
     * @param year
     *            The year of the employment
     */
    @Override
    public void setYear(short year) {
        this.year = year;
    }

}
