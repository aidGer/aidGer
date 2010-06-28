package de.aidger.model.models;

import static de.aidger.utils.Translation._;

import java.util.List;
import java.util.Vector;

import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.controller.ICourseManager;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
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
    private Integer financialCategoryId;

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
    private Integer numberOfGroups;

    /**
     * The target audience for the course.
     */
    private String targetAudience;

    /**
     * The amount of unqualified working hours granted.
     */
    private Double unqualifiedWorkingHours; // UHKS

    /**
     * The scope of the course.
     */
    private String scope;

    /**
     * The part of the course (e.g. 'a' and 'b' or '1' and '2')
     */
    private Character part;

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
                "lecturer", "targetAudience", "group" }, new String[] {
                _("Advisor"), _("Description"), _("Semester"), _("Lecturer"),
                _("Target Audience"), _("Group") });
        validateExistanceOf(new String[] { "financialCategoryId" },
            new String[] { _("Financial Category") }, new FinancialCategory());
        validateFormatOf(new String[] { "semester" },
            new String[] { _("Semester") },
            "^(SS[0-9]{2}|WS[0-9]{4}|[0-9]{4})$");
    }

    /**
     * Initializes the couse class with the given course model.
     * 
     * @param course
     *            the course model
     */
    public Course(ICourse course) {
        this();
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
        setNew(false);
    }

    /**
     * Clone the current course.
     */
    @Override
    public Course clone() {
        Course c = new Course();
        c.setAdvisor(advisor);
        c.setDescription(description);
        c.setFinancialCategoryId(financialCategoryId);
        c.setGroup(group);
        c.setLecturer(lecturer);
        c.setNumberOfGroups(numberOfGroups);
        c.setPart(part);
        c.setRemark(remark);
        c.setScope(scope);
        c.setSemester(semester);
        c.setTargetAudience(targetAudience);
        c.setUnqualifiedWorkingHours(unqualifiedWorkingHours);
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
        if (o instanceof Course) {
            Course c = (Course) o;
            return (id == null ? c.id == null : id.equals(c.id))
                    && (numberOfGroups == null ? c.numberOfGroups == null :
                            numberOfGroups.equals(c.numberOfGroups))
                    && (part == null ? c.part == null : part.equals(c.part))
                    && (financialCategoryId == null ? c.financialCategoryId == null :
                            financialCategoryId.equals(c.financialCategoryId))
                    && (unqualifiedWorkingHours == null ? c.unqualifiedWorkingHours == null :
                            unqualifiedWorkingHours.equals(c.unqualifiedWorkingHours))
                    && (advisor == null ? c.advisor == null : advisor
                            .equals(c.advisor))
                    && (description == null ? c.description == null
                            : description.equals(c.description))
                    && (group == null ? c.group == null : group.equals(c.group))
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
        hash = 67 * hash + (financialCategoryId != null ? financialCategoryId.hashCode() : 0);
        hash = 67 * hash + (advisor != null ? advisor.hashCode() : 0);
        hash = 67 * hash + (description != null ? description.hashCode() : 0);
        hash = 67 * hash + (semester != null ? semester.hashCode() : 0);
        hash = 67 * hash + (lecturer != null ? lecturer.hashCode() : 0);
        hash = 67 * hash + (numberOfGroups != null ? numberOfGroups.hashCode() : 0);
        hash = 67 * hash + (targetAudience != null ? targetAudience.hashCode() : 0);
        hash = 67 * hash
                + (int) (Double.doubleToLongBits(unqualifiedWorkingHours) ^ (Double
                    .doubleToLongBits(unqualifiedWorkingHours) >>> 32));
        hash = 67 * hash + (scope != null ? scope.hashCode() : 0);
        hash = 67 * hash + (part != null ? part.hashCode() : 0);
        hash = 67 * hash + (group != null ? group.hashCode() : 0);
        hash = 67 * hash + (remark != null ? remark.hashCode() : 0);
        return hash;
    }

    /**
     * Custom validation function
     * 
     * @return True if everything is correct
     */
    public boolean validate() {
        boolean ret = true;
        if (numberOfGroups <= 0) {
            addError("numberOfGroups", _("Number of Groups"),
                _("has to be bigger than 0"));
            ret = false;
        }
        if (unqualifiedWorkingHours <= 0.0) {
            addError("unqualifiedWorkingHours", _("AWH per group"),
                _("has to be bigger than 0"));
            ret = false;
        }
        return ret;
    }

    /**
     * Custom validation function for remove().
     * 
     * @return True if everything is correct
     */
    public boolean validateOnRemove() throws AdoHiveException {
        boolean ret = true;

        List act = (new Activity()).getActivities(this);
        List emp = (new Employment()).getEmployments(this);

        if (act.size() > 0) {
            addError(_("Course is still linked to an Activity."));
            ret = false;
        }
        if (emp.size() > 0) {
            addError(_("Course is still linked to an Employment"));
            ret = false;
        }
        return ret;
    }

    /**
     * Get a list of all courses with the given financial category.
     * 
     * @param category
     *            The given financial category
     * @return A list of courses
     * @throws AdoHiveException
     */
    public List<Course> getCourses(FinancialCategory category)
            throws AdoHiveException {
        ICourseManager mgr = (ICourseManager) getManager();
        return castList(mgr.getCourses(category));
    }

    /**
     * Get a list of all courses for a given semester.
     *
     * @param semester
     *            The given semester
     * @return List of courses
     * @throws AdoHiveException
     */
    public List<Course> getCoursesBySemester(String semester) 
            throws AdoHiveException {
        ICourseManager mgr = (ICourseManager) getManager();
        return castList(mgr.getCoursesBySemester(semester));
    }

    /**
     * Get a list of all courses belonging to the specified group.
     *
     * @param group
     *            The given group
     * @return List of courses
     * @throws AdoHiveException
     */
    public List<Course> getCoursesByGroup(String group) throws AdoHiveException {
        ICourseManager mgr = (ICourseManager) getManager();
        return castList(mgr.getCoursesByGroup(group));
    }

    /**
     * Get a list of distinct semesters.
     *
     * @return List of semesters
     * @throws AdoHiveException
     */
    public List<String> getDistinctSemesters() throws AdoHiveException {
        return ((ICourseManager) getManager()).getDistinctSemesters();
    }

    /**
     * Get a list of distinct groups.
     *
     * @return List of groups
     * @throws AdoHiveException
     */
    public List<String> getDistinctGroups() throws AdoHiveException {
        return ((ICourseManager) getManager()).getDistinctGroups();
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
    public Integer getFinancialCategoryId() {
        return financialCategoryId;
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
    public Integer getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Get the part of the course.
     * 
     * @return The part of the course
     */
    @Override
    public Character getPart() {
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
    public Double getUnqualifiedWorkingHours() {
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
    public void setFinancialCategoryId(Integer id) {
        financialCategoryId = id;
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
    public void setNumberOfGroups(Integer num) {
        numberOfGroups = num;
    }

    /**
     * Set the part of the course.
     * 
     * @param part
     *            The part of the course
     */
    @Override
    public void setPart(Character part) {
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
    public void setUnqualifiedWorkingHours(Double hours) {
        unqualifiedWorkingHours = hours;
    }

    /**
     * Cast from interface to correct class
     *
     * @param list
     *            The list to cast
     * @return The casted list
     */
    protected List<Course> castList(List<ICourse> list) {
        List<Course> ret = new Vector<Course>();
        for (ICourse course : list) {
            ret.add(new Course(course));
        }
        return ret;
    }

}
