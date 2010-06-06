package de.aidger.model.models;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * Represents a single entry in the course column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
public class Course extends AbstractModel<ICourse> implements ICourse {

    /**
     * References the corresponding financial category.
     */
    private int financeCategoryId;

    /**
     * The advisor of the course.
     */
    private String advisor;

    /**
     * The description of the course.
     */
    private String description;

    /**
     * The semester in which the course takes place.
     */
    private String semester;

    /**
     * The lecturer of the course.
     */
    private String lecturer;

    /**
     * The number of groups in the course.
     */
    private int numberOfGroups;

    /**
     * The target audience for the course.
     */
    private String targetAudience;

    /**
     * The amount of unqualified working hours granted.
     */
    private double unqualifiedWorkingHours; // UHKS

    /**
     * The scope of the course.
     */
    private String scope;

    /**
     * The part of the course (e.g. 'a' and 'b' or '1' and '2')
     */
    private char part;

    /**
     * The group of the course.
     */
    private String group;

    /**
     * Remarks regarding the course.
     */
    private String remark;

    /**
     * Initializes the Course class.
     */
    public Course() {
        validatePresenceOf(new String[] { "advisor", "description", "semester",
                "lecturer", "targetAudience", "group" });
        // TODO: Validate the rest of the variables
    }

    /**
     * Initializes the couse class with the given course model.
     * 
     * @param course
     *            the course model
     */
    public Course(ICourse course) {
        setId(course.getId());
        setAdvisor(course.getAdvisor());
        setDescription(course.getDescription());
        setFinancialCategoryId(course.getFinancialCategoryId());
        setGroup(course.getGroup());
        setLecturer(course.getLecturer());
        setNumberOfGroups(course.getNumberOfGroups());
        setPart(course.getPart());
        setRemark(course.getRemark());
        setScope(course.getScope());
        setSemester(course.getSemester());
        setTargetAudience(course.getTargetAudience());
        setUnqualifiedWorkingHours(course.getUnqualifiedWorkingHours());
    }

    /**
     * Clone the current course.
     */
    @Override
    public Course clone() {
        Course c = new Course();
        c.setId(id);
        c.setAdvisor(advisor);
        c.setDescription(description);
        c.setFinancialCategoryId(financeCategoryId);
        c.setGroup(group);
        c.setLecturer(lecturer);
        c.setNumberOfGroups(numberOfGroups);
        c.setPart(part);
        c.setRemark(remark);
        c.setScope(scope);
        c.setSemester(semester);
        c.setTargetAudience(targetAudience);
        c.setUnqualifiedWorkingHours(unqualifiedWorkingHours);
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
        if (o instanceof Course) {
            Course c = (Course) o;
            return c.id == id
                    && c.numberOfGroups == numberOfGroups
                    && c.part == part
                    && ((Double) c.unqualifiedWorkingHours)
                            .equals(unqualifiedWorkingHours)
                    && (advisor == null ? c.advisor == null : advisor
                            .equals(c.advisor))
                    && (description == null ? c.description == null
                            : description.equals(c.description))
                    && (remark == null ? c.remark == null : remark
                            .equals(c.remark))
                    && (scope == null ? c.scope == null : scope.equals(c.scope))
                    && (semester == null ? c.semester == null : semester
                            .equals(c.semester))
                    && (targetAudience == null ? c.targetAudience == null
                            : targetAudience.equals(c.targetAudience));
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
        hash = 67 * hash + financeCategoryId;
        hash = 67 * hash + (advisor != null ? advisor.hashCode() : 0);
        hash = 67 * hash + (description != null ? description.hashCode() : 0);
        hash = 67 * hash + (semester != null ? semester.hashCode() : 0);
        hash = 67 * hash + (lecturer != null ? lecturer.hashCode() : 0);
        hash = 67 * hash + numberOfGroups;
        hash = 67 * hash
                + (targetAudience != null ? targetAudience.hashCode() : 0);
        hash = 67
                * hash
                + (int) (Double.doubleToLongBits(unqualifiedWorkingHours) ^ (Double
                        .doubleToLongBits(unqualifiedWorkingHours) >>> 32));
        hash = 67 * hash + (scope != null ? scope.hashCode() : 0);
        hash = 67 * hash + part;
        hash = 67 * hash + (group != null ? group.hashCode() : 0);
        hash = 67 * hash + (remark != null ? remark.hashCode() : 0);
        return hash;
    }

    /**
     * Get the advisor of the course.
     * 
     * @return The advisor of the course
     */
    @Override
    public String getAdvisor() {
        return advisor;
    }

    /**
     * Get the description of the course
     * 
     * @return The description of the course
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Get the id referencing the category.
     * 
     * @return The id of the category
     */
    @Override
    public int getFinancialCategoryId() {
        return financeCategoryId;
    }

    /**
     * Get the group of the course.
     * 
     * @return The group of the course
     */
    @Override
    public String getGroup() {
        return group;
    }

    /**
     * Get the lecturer of the course.
     * 
     * @return The lecturer of the course
     */
    @Override
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Get the number of groups in the course.
     * 
     * @return The number of groups
     */
    @Override
    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Get the part of the course.
     * 
     * @return The part of the course
     */
    @Override
    public char getPart() {
        return part;
    }

    /**
     * Get remarks regarding the course.
     * 
     * @return The remarks
     */
    @Override
    public String getRemark() {
        return remark;
    }

    /**
     * Get the scope of the course.
     * 
     * @return The scope of the course
     */
    @Override
    public String getScope() {
        return scope;
    }

    /**
     * Get the semester of the course.
     * 
     * @return The semester
     */
    @Override
    public String getSemester() {
        return semester;
    }

    /**
     * Get the target audience of the course.
     * 
     * @return The target audience
     */
    @Override
    public String getTargetAudience() {
        return targetAudience;
    }

    /**
     * Get the amount of unqualified working hours granted.
     * 
     * @return The amount of UWHs
     */
    @Override
    public double getUnqualifiedWorkingHours() {
        return unqualifiedWorkingHours;
    }

    /**
     * Set the advisor of the course
     * 
     * @param advisor
     *            The advisor of the course
     */
    @Override
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    /**
     * Set the description of the course
     * 
     * @param descr
     *            The description of the course
     */
    @Override
    public void setDescription(String descr) {
        description = descr;
    }

    /**
     * Set the id referencing the category.
     * 
     * @param id
     *            The id of the category
     */
    @Override
    public void setFinancialCategoryId(int id) {
        financeCategoryId = id;
    }

    /**
     * Set the group of the course.
     * 
     * @param group
     *            The group of the course
     */
    @Override
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Set the lecturer of the course.
     * 
     * @param lec
     *            The lecturer of the course
     */
    @Override
    public void setLecturer(String lec) {
        lecturer = lec;
    }

    /**
     * Set the number of groups in the course.
     * 
     * @param num
     *            The number of groups
     */
    @Override
    public void setNumberOfGroups(int num) {
        numberOfGroups = num;
    }

    /**
     * Set the part of the course.
     * 
     * @param part
     *            The part of the course
     */
    @Override
    public void setPart(char part) {
        this.part = part;
    }

    /**
     * Set remarks regarding the course.
     * 
     * @param rem
     *            The remarks
     */
    @Override
    public void setRemark(String rem) {
        remark = rem;
    }

    /**
     * Set the scope of the course.
     * 
     * @param scope
     *            The scope of the course
     */
    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * Set the semester of the course.
     * 
     * @param sem
     *            The semester
     */
    @Override
    public void setSemester(String sem) {
        semester = sem;
    }

    /**
     * Set the target audience of the course.
     * 
     * @param target
     *            The target audience
     */
    @Override
    public void setTargetAudience(String target) {
        targetAudience = target;
    }

    /**
     * Set the amount of unqualified working hours granted.
     * 
     * @param hours
     *            The amount of UWHs
     */
    @Override
    public void setUnqualifiedWorkingHours(double hours) {
        unqualifiedWorkingHours = hours;
    }

}
