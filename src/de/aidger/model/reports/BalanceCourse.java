/**
 * 
 */
package de.aidger.model.reports;

/**
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

    private String Lecturer;

    private String TargetAudience;

    private String PlannedAWS;

    private String BasicAWS;

    private int StudentFees;

    private int Resources;

    public BalanceCourse() {
        StudentFees = Resources = 0;
        Title = Lecturer = TargetAudience = PlannedAWS = BasicAWS = "";
        Part = '-';
    }

    public Object[] getCourseObject() {
        return new Object[] { Title, Part, Lecturer, TargetAudience,
                PlannedAWS, BasicAWS, StudentFees, Resources };
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        Title = title;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param part
     *            the part to set
     */
    public void setPart(char part) {
        Part = part;
    }

    /**
     * @return the part
     */
    public char getPart() {
        return Part;
    }

    /**
     * @param lecturer
     *            the lecturer to set
     */
    public void setLecturer(String lecturer) {
        Lecturer = lecturer;
    }

    /**
     * @return the lecturer
     */
    public String getLecturer() {
        return Lecturer;
    }

    /**
     * @param targetAudience
     *            the targetAudience to set
     */
    public void setTargetAudience(String targetAudience) {
        TargetAudience = targetAudience;
    }

    /**
     * @return the targetAudience
     */
    public String getTargetAudience() {
        return TargetAudience;
    }

    /**
     * @param plannedAWS
     *            the plannedAWS to set
     */
    public void setPlannedAWS(String plannedAWS) {
        PlannedAWS = plannedAWS;
    }

    /**
     * @return the plannedAWS
     */
    public String getPlannedAWS() {
        return PlannedAWS;
    }

    /**
     * @param basicAWS
     *            the basicAWS to set
     */
    public void setBasicAWS(String basicAWS) {
        BasicAWS = basicAWS;
    }

    /**
     * @return the basicAWS
     */
    public String getBasicAWS() {
        return BasicAWS;
    }

    /**
     * @param studentFees
     *            the studentFees to set
     */
    public void setStudentFees(int studentFees) {
        StudentFees = studentFees;
    }

    /**
     * @return the studentFees
     */
    public int getStudentFees() {
        return StudentFees;
    }

    /**
     * @param resources
     *            the resources to set
     */
    public void setResources(int resources) {
        Resources = resources;
    }

    /**
     * @return the resources
     */
    public int getResources() {
        return Resources;
    }
}
