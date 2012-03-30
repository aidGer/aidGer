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

import java.util.List;

import siena.Table;
import siena.Column;

import de.aidger.model.AbstractModel;

/**
 * Represents a single entry in the course column of the database. Contains
 * functions to retrieve and change the data in the database.
 * 
 * @author aidGer Team
 */
@Table("Veranstaltung")
public class Course extends AbstractModel<Course> {

    /**
     * References the corresponding financial category.
     */
    @Column("Finanzkategorie_ID")
    private Integer financialCategoryId;

    /**
     * The advisor of the course.
     */
    @Column("Betreuer")
    private String advisor;

    /**
     * The description of the course.
     */
    @Column("Bezeichnung")
    private String description;

    /**
     * The semester in which the course takes place.
     */
    @Column("Semester")
    private String semester;

    /**
     * The lecturer of the course.
     */
    @Column("Dozent")
    private String lecturer;

    /**
     * The number of groups in the course.
     */
    @Column("Gruppenanzahl")
    private Integer numberOfGroups;

    /**
     * The target audience for the course.
     */
    @Column("Zielpublikum")
    private String targetAudience;

    /**
     * The amount of unqualified working hours granted.
     */
    @Column("HKS")
    private Double unqualifiedWorkingHours; // UHKS

    /**
     * The scope of the course.
     */
    @Column("Umfang")
    private String scope;

    /**
     * The part of the course (e.g. 'a' and 'b' or '1' and '2')
     */
    @Column("Teil")
    private Character part;

    /**
     * The group of the course.
     */
    @Column("Gruppe")
    private String group;

    /**
     * Remarks regarding the course.
     */
    @Column("Bemerkung")
    private String remark;

    /**
     * Initializes the Course class.
     */
    public Course() {
        if (getValidators().isEmpty()) {
            validatePresenceOf(new String[] { "description", "semester",
                    "lecturer", "group", "unqualifiedWorkingHours" }, new String[] {
                    _("Description"), _("Semester"), _("Lecturer"), _("Group"),
                    _("AWH per group") });
            validateExistenceOf(new String[]{"financialCategoryId"},
                    new String[]{_("Financial Category")}, new FinancialCategory());
            validateFormatOf(new String[] { "semester" },
                    new String[] { _("Semester") },
                    "^(SS[0-9]{2}|WS[0-9]{4}|[0-9]{4})$");
        }
    }

    /**
     * Initializes the couse class with the given course model.
     * 
     * @param course
     *            the course model
     */
    public Course(Course course) {
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
        return c;
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
    public boolean validateOnRemove() {
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
     */
    public List<Course> getCourses(FinancialCategory category) {
        //TODO: Implement
        return null;
    }

    /**
     * Get a list of all courses for a given semester.
     * 
     * @param semester
     *            The given semester
     * @return List of courses
     */
    public List<Course> getCoursesBySemester(String semester) {
        //TODO: Implement
        return null;
    }

    /**
     * Get a list of all courses belonging to the specified group.
     * 
     * @param group
     *            The given group
     * @return List of courses
     */
    public List<Course> getCoursesByGroup(String group) {
        //TODO: Implement
        return null;
    }

    /**
     * Get a list of distinct semesters.
     * 
     * @return List of semesters
     */
    public List<String> getDistinctSemesters() {
        //TODO: Implement
        return null;
    }

    /**
     * Get a list of distinct groups.
     * 
     * @return List of groups
     */
    public List<String> getDistinctGroups() {
        //TODO: Implement
        return null;
    }

    /**
     * Get the advisor of the course.
     * 
     * @return The advisor of the course
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * Get the description of the course
     * 
     * @return The description of the course
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the id referencing the category.
     * 
     * @return The id of the category
     */
    public Integer getFinancialCategoryId() {
        return financialCategoryId;
    }

    /**
     * Get the group of the course.
     * 
     * @return The group of the course
     */
    public String getGroup() {
        return group;
    }

    /**
     * Get the lecturer of the course.
     * 
     * @return The lecturer of the course
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Get the number of groups in the course.
     * 
     * @return The number of groups
     */
    public Integer getNumberOfGroups() {
        return numberOfGroups;
    }

    /**
     * Get the part of the course.
     * 
     * @return The part of the course
     */
    public Character getPart() {
        return part;
    }

    /**
     * Get remarks regarding the course.
     * 
     * @return The remarks
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Get the scope of the course.
     * 
     * @return The scope of the course
     */
    public String getScope() {
        return scope;
    }

    /**
     * Get the semester of the course.
     * 
     * @return The semester
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Get the target audience of the course.
     * 
     * @return The target audience
     */
    public String getTargetAudience() {
        return targetAudience;
    }

    /**
     * Get the amount of unqualified working hours granted.
     * 
     * @return The amount of UWHs
     */
    public Double getUnqualifiedWorkingHours() {
        return unqualifiedWorkingHours;
    }

    /**
     * Set the advisor of the course
     * 
     * @param advisor
     *            The advisor of the course
     */
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    /**
     * Set the description of the course
     * 
     * @param descr
     *            The description of the course
     */
    public void setDescription(String descr) {
        description = descr;
    }

    /**
     * Set the id referencing the category.
     * 
     * @param id
     *            The id of the category
     */

    public void setFinancialCategoryId(Integer id) {
        financialCategoryId = id;
    }

    /**
     * Set the group of the course.
     * 
     * @param group
     *            The group of the course
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * Set the lecturer of the course.
     * 
     * @param lec
     *            The lecturer of the course
     */
    public void setLecturer(String lec) {
        lecturer = lec;
    }

    /**
     * Set the number of groups in the course.
     * 
     * @param num
     *            The number of groups
     */
    public void setNumberOfGroups(Integer num) {
        numberOfGroups = num;
    }

    /**
     * Set the part of the course.
     * 
     * @param part
     *            The part of the course
     */
    public void setPart(Character part) {
        this.part = part;
    }

    /**
     * Set remarks regarding the course.
     * 
     * @param rem
     *            The remarks
     */
    public void setRemark(String rem) {
        remark = rem;
    }

    /**
     * Set the scope of the course.
     * 
     * @param scope
     *            The scope of the course
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * Set the semester of the course.
     * 
     * @param sem
     *            The semester
     */
    public void setSemester(String sem) {
        semester = sem;
    }

    /**
     * Set the target audience of the course.
     * 
     * @param target
     *            The target audience
     */
    public void setTargetAudience(String target) {
        targetAudience = target;
    }

    /**
     * Set the amount of unqualified working hours granted.
     * 
     * @param hours
     *            The amount of UWHs
     */
    public void setUnqualifiedWorkingHours(Double hours) {
        unqualifiedWorkingHours = hours;
    }

}
