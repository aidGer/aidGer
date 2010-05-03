package de.aidger.model.models;

import de.aidger.model.AbstractModel;
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
     * Clone the current employment.
     */
    @Override
    public Employment clone() {
        return new Employment();
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

    /**
     * Returns the unique id of the employment.
     * 
     * @return The unique id of the employment
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Set the unique id of the employment. <b>!!! THIS IS FOR INTERNAL ADOHIVE
     * USAGE ONLY !!!</b>
     * 
     * @param id
     *            The unique id of the employment
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

}
