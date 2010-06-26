/**
 * 
 */
package de.aidger.utils.reports;

import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.reports.ActivityEmployment;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * The class creates the activity employment objects for one assistant for the
 * activity report.
 * 
 * @author aidGer Team
 */
public class ActivityReportHelper {
    /**
     * Initializes a new ActivityReportHelper.
     */
    public ActivityReportHelper() {

    }

    private final Vector<CourseEmployment> courseEmployments = new Vector<CourseEmployment>();

    private int getIndexOf(String name) {
        int i = 0;
        for (CourseEmployment courseEmployment : courseEmployments) {
            if (courseEmployment.getName().equals(name)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private void addCourseEmployment(String name, Employment employment) {
        if (getIndexOf(name) > -1) {
            courseEmployments.get(getIndexOf(name)).addEmployment(employment);
        } else {
            courseEmployments.add(new CourseEmployment(name, employment));
        }
    }

    private class CourseEmployment {
        private String name;
        private final Vector<Employment> employments;

        public CourseEmployment(String name, Employment employment) {
            this.name = name;
            employments = new Vector<Employment>();
            employments.add(employment);
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addEmployment(Employment employment) {
            int i = 0;
            boolean employmentInserted = false;
            for (Employment thisEmployment : employments) {
                if (thisEmployment.getYear() * 12 + thisEmployment.getMonth() >= employment
                    .getYear()
                        * 12 + employment.getMonth()) {
                    employments.add(i, employment);
                    employmentInserted = true;
                    break;
                }
                i++;
            }
            if (!employmentInserted) {
                employments.add(employment);
            }
        }

        public String getName() {
            return name;
        }

        public Vector<Employment> getEmployments() {
            return employments;
        }

        public boolean contains(Employment employment) {
            if (employments.contains(employment)) {
                return true;
            }
            return false;
        }
    }

    public Vector<ActivityEmployment> getEmployments(Assistant assistant) {
        Vector<ActivityEmployment> activityEmployments = new Vector<ActivityEmployment>();
        try {
            List<Employment> employments = new Employment()
                .getEmployments(assistant);
            for (Employment employment : employments) {
                ICourse course = new Course().getById(employment.getCourseId());
                addCourseEmployment(course.getDescription() + "("
                        + course.getSemester() + ")", employment);
            }
            for (CourseEmployment courseEmployment : courseEmployments) {
                ActivityEmployment activityEmployment = new ActivityEmployment();
                activityEmployment.setCourse(courseEmployment.getName());
                int firstYear = courseEmployment.getEmployments().get(0)
                    .getYear();
                int firstMonth = courseEmployment.getEmployments().get(0)
                    .getMonth();
                int firstTotalMonth = firstYear * 12 + firstMonth;
                activityEmployment.addYear((short) firstYear);
                activityEmployment.addMonth((byte) firstMonth);
                int i = 1;
                int count = 0;
                double hourCount = 0;
                for (Employment employment : courseEmployment.getEmployments()) {
                    if (employment.getYear() * 12 + employment.getMonth() <= firstTotalMonth
                            + i) {
                        hourCount = hourCount + employment.getHourCount();
                        if (employment.getYear() * 12 + employment.getMonth() == firstYear
                                * 12 + firstMonth + i) {
                            i++;
                        }
                    } else {
                        activityEmployment.addHours(hourCount);
                        int currentYear = ((firstTotalMonth + i - 1) / 12);
                        int currentMonth = (firstMonth + i - 1) % 12;
                        if (currentMonth == 0) {
                            currentMonth = 12;
                            currentYear--;
                        }
                        activityEmployment.addYear((short) (currentYear));
                        activityEmployment.addMonth((byte) (currentMonth));
                        activityEmployments.add(activityEmployment);
                        activityEmployment = new ActivityEmployment();
                        firstYear = employment.getYear();
                        firstMonth = employment.getMonth();
                        firstTotalMonth = firstYear * 12 + firstMonth;
                        i = 1;
                        hourCount = employment.getHourCount();
                        activityEmployment.addYear((short) firstYear);
                        activityEmployment.addMonth((byte) firstMonth);
                    }
                    count++;
                    if (count == courseEmployment.getEmployments().size()) {
                        activityEmployment
                            .setCourse(courseEmployment.getName());
                        activityEmployment.addHours(hourCount);
                        int size = courseEmployment.getEmployments().size();
                        activityEmployment.addYear(courseEmployment
                            .getEmployments().get(size - 1).getYear());
                        activityEmployment.addMonth(courseEmployment
                            .getEmployments().get(size - 1).getMonth());
                        activityEmployments.add(activityEmployment);
                    }
                }
            }
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return activityEmployments;
    }

    public Object[] getEmploymentArray(ActivityEmployment employment) {
        Object[] returnArray = new Object[3];
        int monthSize = employment.getMonths().size();
        int yearSize = employment.getYears().size();
        String firstMonth = "";
        String lastMonth = "";
        if (employment.getMonths().get(0) < 10) {
            firstMonth = "0" + employment.getMonths().get(0);
        } else {
            firstMonth = employment.getMonths().get(0).toString();
        }
        if (employment.getMonths().get(monthSize - 1) < 10) {
            lastMonth = "0" + employment.getMonths().get(monthSize - 1);
        } else {
            lastMonth = employment.getMonths().get(monthSize - 1).toString();
        }
        returnArray[0] = firstMonth + "." + employment.getYears().get(0)
                + " - " + lastMonth + "."
                + employment.getYears().get(yearSize - 1);
        returnArray[1] = employment.getCourse();
        returnArray[2] = employment.getHours();
        return returnArray;
    }
}
