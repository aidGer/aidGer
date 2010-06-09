/**
 * 
 */
package de.aidger.model.reports;

/**
 * Represents a course in a balance report with all it's variables.
 * 
 * @author aidGer Team
 * 
 */
public class BalanceCourse {

    /**
     * The title of the course.
     */
    private String Title;

    /**
     * The part of the course.
     */
    private char Part;

    /**
     * The lecturer of the course.
     */
    private String Lecturer;

    /**
     * The target audience of the course.
     */
    private String TargetAudience;

    /**
     * The planned AWS of the course.
     */
    private double PlannedAWS;

    /**
     * The basic needed AWS of the course.
     */
    private double BasicAWS;

    /**
     * The budget costs paid with student fees.
     */
    private int StudentFees;

    /**
     * The budget costs paid with resources.
     */
    private int Resources;

    /**
     * Initializes a new BalanceCourse, which contains all the necessary
     * variables of a course in a balance report.
     */
    public BalanceCourse() {
        StudentFees = Resources = 0;
        Title = Lecturer = TargetAudience = "";
        PlannedAWS = BasicAWS = 0.0;
        Part = '-';
    }

    /**
     * Returns the course as an object for putting in a table.
     * 
     * @return The object
     */
    public Object[] getCourseObject() {
        return new Object[] { Title, Part, Lecturer, TargetAudience,
                PlannedAWS, BasicAWS, StudentFees, Resources };
    }

    /**
     * Sets the title of the course.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * Returns the title of the course.
     * 
     * @return the title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Sets the part of the course.
     * 
     * @param part
     *            the part to set
     */
    public void setPart(char part) {
        Part = part;
    }

    /**
     * Returns the part of the course.
     * 
     * @return the part
     */
    public char getPart() {
        return Part;
    }

    /**
     * Sets the lecturer of the course.
     * 
     * @param lecturer
     *            the lecturer to set
     */
    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    /**
     * Returns the lecturer of the course.
     * 
     * @return the lecturer
     */
    public String getLecturer() {
        return Lecturer;
    }

    /**
     * Sets the target audience of the course.
     * 
     * @param targetAudience
     *            the targetAudience to set
     */
    public void setTargetAudience(String targetAudience) {
        TargetAudience = targetAudience;
    }

    /**
     * Returns the target audience of the course.
     * 
     * @return the targetAudience
     */
    public String getTargetAudience() {
        return TargetAudience;
    }

    /**
     * Sets the planned AWS of the course.
     * 
     * @param plannedAWS
     *            the plannedAWS to set
     */
    public void setPlannedAWS(double plannedAWS) {
        PlannedAWS = plannedAWS;
    }

    /**
     * Returns the planned AWS of the course.
     * 
     * @return the plannedAWS
     */
    public double getPlannedAWS() {
        return PlannedAWS;
    }

    /**
     * Sets the basic AWS of the course.
     * 
     * @param basicAWS
     *            the basicAWS to set
     */
    public void setBasicAWS(double basicAWS) {
        BasicAWS = basicAWS;
    }

    /**
     * Returns the basic AWS of the course.
     * 
     * @return the basicAWS
     */
    public double getBasicAWS() {
        return BasicAWS;
    }

    /**
     * Sets the budget costs from student fees of the course.
     * 
     * @param studentFees
     *            the studentFees to set
     */
    public void setStudentFees(int studentFees) {
        StudentFees = studentFees;
    }

    /**
     * Returns the budget costs from student fees of the course.
     * 
     * @return the studentFees
     */
    public int getStudentFees() {
        return StudentFees;
    }

    /**
     * Sets the budget costs from resources of the course.
     * 
     * @param resources
     *            the resources to set
     */
    public void setResources(int resources) {
        Resources = resources;
    }

    /**
     * Returns the budget costs from resources of the course.
     * 
     * @return the resources
     */
    public int getResources() {
        return Resources;
    }
}
