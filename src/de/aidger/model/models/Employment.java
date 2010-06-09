package de.aidger.model.models;

import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import java.sql.Date;
import java.util.List;
import java.util.Calendar;
import java.util.Vector;

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
    private int    funds;

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
     * The qualification of the employment.
     */
    private String qualification;

    /**
     * Remarks regarding the employment.
     */
    private String remark;

    /**
     * Initializes the Employment class.
     */
    public Employment() {
        validatePresenceOf(new String[] { "costUnit", "qualification" });
        validateInclusionOf(new String[] { "qualification" }, new String[] {
                "g", "u", "b"});
        //TODO: Validate the Ids
        //TODO: Validate the fonds
        //TODO: Validate the hourCount
    }

    /**
     * Initializes the Employment class with the given employment model.
     *
     * @param e
     *            the employment model
     */
    public Employment(IEmployment e) {
        this();
        setId(e.getId());
        setAssistantId(e.getAssistantId());
        setContractId(e.getContractId());
        setCostUnit(e.getCostUnit());
        setFunds(e.getFunds());
        setHourCount(e.getHourCount());
        setMonth(e.getMonth());
        setQualification(e.getQualification());
        setRemark(e.getRemark());
        setYear(e.getYear());
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
        e.setFunds(funds);
        e.setHourCount(hourCount);
        e.setMonth(month);
        e.setQualification(qualification);
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
        if (o instanceof IEmployment) {
            IEmployment e = (IEmployment) o;
            return e.getId() == id && e.getAssistantId() == assistantId &&
                    e.getContractId() == contractId && e.getFunds() == funds &&
                    e.getCourseId() == courseId && e.getMonth() == month &&
                    e.getYear() == year &&
                    (costUnit == null ? e.getCostUnit() == null :
                            costUnit.equals(e.getCostUnit())) &&
                    ((Double) hourCount).equals(e.getHourCount()) &&
                    (qualification == null ? e.getQualification() == null :
                            qualification.equals(e.getQualification())) &&
                    (remark == null ? e.getRemark() == null :
                            remark.equals(e.getRemark()));
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
        hash = 29 * hash + assistantId;
        hash = 29 * hash + contractId;
        hash = 29 * hash + courseId;
        hash = 29 * hash + funds;
        hash = 29 * hash + month;
        hash = 29 * hash + year;
        hash = 29 * hash + (int) (Double.doubleToLongBits(hourCount) ^
                (Double.doubleToLongBits(hourCount) >>> 32));
        hash = 29 * hash + (costUnit != null ? costUnit.hashCode() : 0);
        hash = 29 * hash + (qualification != null ? qualification.hashCode() : 0);
        hash = 29 * hash + (remark != null ? remark.hashCode() : 0);
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
    public List<Employment> getEmployments(Date start, Date end) 
            throws AdoHiveException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        short startYear = (short) cal.get(Calendar.YEAR);
        byte startMonth = (byte) cal.get(Calendar.MONTH);

        cal.setTime(end);
        short endYear = (short) cal.get(Calendar.YEAR);
        byte endMonth = (byte) cal.get(Calendar.MONTH);

    	return getEmployments(startYear, startMonth, endYear, endMonth);
    }

    /**
     * Get all employments during the given time.
     *
     * @param startYear
     * 			The start year
     * @param startMonth
     *                  The start month
     * @param endYear
     * 			The end year
     * @param endMonth
     *                  The end month
     * @return The employments during the given time
     */
    public List<Employment> getEmployments(short startYear, byte startMonth,
            short endYear, byte endMonth) throws AdoHiveException {
    	IEmploymentManager mgr = (IEmploymentManager)getManager();
    	return castList(mgr.getEmployments(startYear, startMonth, endYear,
                endMonth));
    }

    /**
     * Get all employments with the given contract.
     *
     * @param contract
     * 			The given contract
     * @return The employments with the given contract
     */
    public List<Employment> getEmployments(Contract contract) 
            throws AdoHiveException {
    	IEmploymentManager mgr = (IEmploymentManager)getManager();
    	return castList(mgr.getEmployments(contract));
    }

    /**
     * Get all employments of the given assistant.
     *
     * @param assistant
     *                  The given assistant
     * @return The employments of the given assistant
     */
    public List<Employment> getEmployments(Assistant assistant) 
            throws AdoHiveException {
    	IEmploymentManager mgr = (IEmploymentManager)getManager();
    	return castList(mgr.getEmployments(assistant));
    }

    /**
     * Get all employments for the given course.
     *
     * @param course
     *                  The given course
     * @return The employments for the given course
     */
    public List<Employment> getEmployments(Course course)
            throws AdoHiveException {
    	IEmploymentManager mgr = (IEmploymentManager)getManager();
    	return castList(mgr.getEmployments(course));
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
     * Get the funds of the employment.
     *
     * @return The funds of the employment
     */
    @Override
    public int getFunds() {
        return funds;
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
     * Get the qualification of the employment.
     *
     * @return The qualification of the employment
     */
    @Override
    public String getQualification() {
        return qualification;
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
     * Set the funds of the employment.
     *
     * @param funds
     *            The funds of the employment
     */
    @Override
    public void setFunds(int funds) {
        this.funds = funds;
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
     * Set the qualification of the employment.
     *
     * @param quali
     *            The qualification of the employment
     */
    @Override
    public void setQualification(String quali) {
        qualification = quali;
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
     * Cast the list from interface to real class.
     *
     * @param list
     *              The list to cast
     * @return The new list
     */
    protected List<Employment> castList(List<IEmployment> list) {
        List<Employment> ret = new Vector<Employment>();
        for (IEmployment emp : list) {
            ret.add(new Employment(emp));
        }
        return ret;
    }

}
