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

/**
 * 
 */
package de.aidger.utils.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.aidger.model.Runtime;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.HourlyWage;
import de.aidger.model.reports.BalanceCourse;
import de.aidger.model.reports.BalanceFilter;
import de.aidger.view.UI;
import siena.SienaException;

/**
 * This class is used to get all the existing semesters and years.
 * 
 * @author aidGer Team
 */
public class BalanceHelper {

    /**
     * Initializes a new BalanceHelper.
     */
    public BalanceHelper() {
    }

    /**
     * Filters the given courses using the given filters.
     * 
     * @param courses
     *            The courses to filter.
     * @param filters
     *            The filters to use.
     * @return The filtered courses
     */
    public List<Course> filterCourses(List<Course> courses,
            BalanceFilter filters) {
        List<Course> filteredOnceCourses = new ArrayList<Course>();
        List<Course> filteredTwiceCourses = new ArrayList<Course>();
        List<Course> filteredTriceCourses = new ArrayList<Course>();
        /*
         * Only use courses, which have the filtered criteria.
         */
        if (!(filters == null)) {
            boolean filterExists = false;
            /*
             * There are existing filters.
             */
            if (!filters.getGroups().isEmpty()) {
                /*
                 * There are existing group filters.
                 */
                for (Object group : filters.getGroups()) {
                    for (Course course : courses) {
                        if (!filteredOnceCourses.contains(course)
                                && course.getGroup().equals(group)) {
                            /*
                             * The course is not already in the filtered courses
                             * and meets the group criteria.
                             */
                            filteredOnceCourses.add(course);
                        }
                    }
                }
                filterExists = true;
            } else {
                filteredOnceCourses = courses;
            }
            if (!filters.getLecturers().isEmpty()) {
                /*
                 * There are existing lecture filters.
                 */
                for (Object lecturer : filters.getLecturers()) {
                    for (Course course : filteredOnceCourses) {
                        if (!filteredTwiceCourses.contains(course)
                                && course.getLecturer().equals(lecturer)) {
                            /*
                             * The course is not already in the filtered courses
                             * and meets the lecturer criteria.
                             */
                            filteredTwiceCourses.add(course);
                        }
                    }
                }
                filterExists = true;
            } else {
                filteredTwiceCourses = filteredOnceCourses;
            }
            if (!filters.getTargetAudiences().isEmpty()) {
                /*
                 * There are existing target audience filters.
                 */
                for (Object lecturer : filters.getTargetAudiences()) {
                    for (Course course : filteredTwiceCourses) {
                        if (!filteredTriceCourses.contains(course)
                                && course.getTargetAudience().equals(lecturer)) {
                            /*
                             * The course is not already in the filtered courses
                             * and meets the target audience criteria.
                             */
                            filteredTriceCourses.add(course);
                        }
                    }
                }
                filterExists = true;
            } else {
                filteredTriceCourses = filteredTwiceCourses;
            }
            if (!filterExists) {
                filteredTriceCourses = courses;
            }
        } else {
            /*
             * If there are no filters, use the normal courses.
             */
            filteredTriceCourses = courses;
        }
        return filteredTriceCourses;
    }

    /**
     * Gets all the years of a semester.
     * 
     * @param year
     *            The semester to check.
     * @return The years of the semester.
     */
    public ArrayList<String> getYearSemesters(int year) {
        ArrayList<String> semesters;
        if (year != 0) {
            // Lose the first two numbers of the year
            int semester = year % 100;
            /*
             * Contains the year in YYYY form, the previous, current and next
             * semester in that order.
             */
            semesters = new ArrayList<String>();
            semesters.add("" + year);
            switch (semester) {
            /*
             * If the given year is 2001-2008, (year % 100) will give a single
             * number below 9. Therefore, the previous, current and next
             * semester all need a leading 0 added.
             */
            case 0:
                semesters.add("WS" + "99" + "00");
                semesters.add("SS" + "00");
                semesters.add("WS" + "00" + "01");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                semesters.add("WS0" + (semester - 1) + "0" + semester);
                semesters.add("SS0" + semester);
                semesters.add("WS0" + semester + "0" + (semester + 1));
                break;
            /*
             * If the given year is 2009, the previous and current semester will
             * both be a single number and therefore need a leading 0 added. The
             * next semester will be 10 and thus needs no adjustments.
             */
            case 9:
                semesters.add("WS0" + (semester - 1) + "0" + semester);
                semesters.add("SS0" + semester);
                semesters.add("WS0" + semester + (semester + 1));
                break;
            /*
             * If the given year is 2010, the current and next semesters will be
             * 10 and 11 and therefore don't need a leading 0. The previous
             * semester will be 9 though.
             */
            case 10:
                semesters.add("WS0" + (semester - 1) + semester);
                semesters.add("SS" + semester);
                semesters.add("WS" + semester + (semester + 1));
                break;
            case 99:
                semesters.add("WS" + (semester - 1) + semester);
                semesters.add("SS" + semester);
                semesters.add("WS" + semester + "00");
                break;
            /*
             * In all other relevant cases (11 and higher), the semesters can be
             * used the way (year % 100) returns them.
             */
            default:
                semesters.add("WS" + (semester - 1) + semester);
                semesters.add("SS" + semester);
                semesters.add("WS" + semester + (semester + 1));
                break;
            }
        } else {
            ArrayList<String> allSemesters = getSemesters();
            semesters = new ArrayList<String>();
            for (String semester : allSemesters) {
                if (semester == null) {
                    semesters.add(semester);
                    continue;
                }
                Pattern semesterPattern = Pattern
                    .compile("([S|W]S)?(\\d{2})(\\d{0,2})");
                Matcher semesterMatcher = semesterPattern.matcher(semester);
                if (!semesterMatcher.find()) {
                    semesters.add(semester);
                }
            }
        }
        return semesters;
    }

    /**
     * Calculates the data relevant for the balance into a balance course model.
     * Returns that balance course model.
     * 
     * @param course
     *            The course to be calculated.
     * @return The balance course model.
     */
    public static BalanceCourse getBalanceCourse(Course course) {
        BalanceCourse balanceCourse = new BalanceCourse();
        balanceCourse.setTitle(course.getDescription());
        balanceCourse.setPart(course.getPart());
        balanceCourse.setLecturer(course.getLecturer());
        balanceCourse.setTargetAudience(course.getTargetAudience());
        double plannedAWS = 0;
        double basicAWS = course.getNumberOfGroups()
                * course.getUnqualifiedWorkingHours();
        balanceCourse.setBasicAWS(basicAWS);
        List<Employment> employments = null;
        try {
            employments = (new Employment()).getEmployments(new Course(course));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Employment employment : employments) {
            /*
             * Sum up the budget costs of the course by multiplying the hours of
             * the fitting employments.
             */
            Double budgetCost = calculateBudgetCost(employment);
            balanceCourse.addBudgetCost(employment.getCostUnit(), employment
                .getFunds(), budgetCost.doubleValue());
            plannedAWS = plannedAWS + employment.getHourCount();
        }
        balanceCourse.setPlannedAWS(new BigDecimal(plannedAWS).setScale(2,
            BigDecimal.ROUND_HALF_EVEN).doubleValue());
        return balanceCourse;
    }

    /**
     * Calculates the budget costs of this employment
     */
    public static double calculateBudgetCost(Employment employment) {
        String qualification = employment.getQualification();
        double calculationFactor = 1.0;
        double calculationMethod = Integer.parseInt(Runtime.getInstance()
            .getOption("calc-method"));
        if (calculationMethod == 1) {
            calculationFactor = Double.parseDouble(de.aidger.model.Runtime
                .getInstance().getOption("pessimistic-factor", "1.0"));
        } else {
            calculationFactor = Double.parseDouble(de.aidger.model.Runtime
                .getInstance().getOption("historic-factor", "1.0"));
        }
        List<HourlyWage> hourlyWages;
        try {
            hourlyWages = new HourlyWage().getAll();
            if (hourlyWages == null)
                return 0;
            for (HourlyWage hourlyWage : hourlyWages) {
                if (hourlyWage.getMonth().equals(employment.getMonth())
                        && hourlyWage.getYear().equals(employment.getYear())
                        && hourlyWage.getQualification().equals(qualification)) {
                    return hourlyWage.getWage().doubleValue()
                            * calculationFactor * employment.getHourCount();
                }
            }
        } catch (SienaException e) {
            UI.displayError(e.toString());
        }
        return 0;
    }

    /**
     * Calculates the budget costs of this employment as pre-tax.
     */
    public static double calculatePreTaxBudgetCost(Employment employment) {
        String qualification = employment.getQualification();
        double calculationFactor = 1.0;
        List<HourlyWage> hourlyWages;
        try {
            hourlyWages = new HourlyWage().getAll();
            if (hourlyWages == null)
                return 0;
            for (HourlyWage hourlyWage : hourlyWages) {
                if (hourlyWage.getMonth().equals(employment.getMonth())
                        && hourlyWage.getYear().equals(employment.getYear())
                        && hourlyWage.getQualification().equals(qualification)) {
                    return hourlyWage.getWage().doubleValue()
                            * calculationFactor * employment.getHourCount();
                }
            }
        } catch (SienaException e) {
            UI.displayError(e.toString());
        }
        return -1;
    }

    /**
     * Checks all the courses for their semesters and returns all the semesters
     * afterwards.
     * 
     * @return A ArrayList containing the semesters as Strings.
     */
    public ArrayList<String> getSemesters() {
        ArrayList<String> semestersVector = new ArrayList<String>();
        /*
         * Add an empty semester string as the first entry. Relevant for the
         * combo boxes.
         */
        try {
            List<String> semesters = (new Course()).getDistinctSemesters();
            semestersVector = new ArrayList<String>(semesters);
        } catch (SienaException e) {
            e.printStackTrace();
        }
        return semestersVector;
    }

    /**
     * Checks all the semesters for their years and returns the years as a
     * vector afterwards.
     * 
     * @return The vector of years as ints.
     */
    public ArrayList<Integer> getYears() {
        ArrayList<String> semesters = getSemesters();
        if (semesters.size() > 0) {
            /*
             * Only get the years, if there are any valid courses with a
             * semester.
             */
            ArrayList<Integer> years = new ArrayList<Integer>();
            /*
             * Check for every semester out of the semester vector, if the year
             * of that semester is already noted and add it if it's not.
             */
            for (String semester : semesters) {
                int year = 0;
                if (semester != null) {
                    Pattern semesterPattern = Pattern
                        .compile("([S|W]S)?(\\d{2})(\\d{0,2})");
                    Matcher semesterMatcher = semesterPattern.matcher(semester);
                    if (semesterMatcher.find()) {
                        if (semesterMatcher.group(1) != null) {
                            /*
                             * Semester is either SS or WS. Add the first year
                             * regardless.
                             */
                            year = 2000 + Integer.parseInt(semesterMatcher
                                .group(2));
                            if (semesterMatcher.group(1).equals("WS")) {
                                // Semester is WS. Add the second year.
                                if (!years.contains(year)) {
                                    years.add(year);
                                }
                                year = 2000 + Integer.parseInt(semesterMatcher
                                    .group(3));
                            }
                        } else {
                            // Semester is in the form YYYY.
                            year = Integer.parseInt(semester);
                        }
                    }
                }
                if (!years.contains(year)) {
                    years.add(year);
                }
            }
            ArrayList<Integer> sortedYears = new ArrayList<Integer>();
            sortedYears.add(years.get(0));
            for (int i = 1; i < years.size(); i++) {
                boolean addedYear = false;
                for (int j = 0; j < sortedYears.size(); j++) {
                    if ((Integer) years.get(i) <= (Integer) sortedYears.get(j)) {
                        sortedYears.add(j, years.get(i));
                        addedYear = true;
                        break;
                    }
                }
                if (!addedYear) {
                    sortedYears.add(years.get(i));
                }
            }
            return sortedYears;
        } else {
            /*
             * There are no valid courses. Return empty vector.
             */
            ArrayList<Integer> years = new ArrayList<Integer>();
            return years;
        }
    }

    /**
     * Checks if the given semester contains any courses.
     * 
     * @param semester
     *            The semester to check
     * @return true if the semester contains one or more courses.
     */
    public boolean courseExists(String semester, BalanceFilter filters) {
        List<Course> courses = null;
        try {
            if (semester != null) {
                courses = (new Course()).getCoursesBySemester(semester);
            } else {
                courses = new ArrayList<Course>();
                List<Course> unsortedCourses = new Course().getAll();
                for (Course course : unsortedCourses) {
                    if (course.getSemester() == null) {
                        courses.add(new Course(course));
                    }
                }
            }
        } catch (SienaException e) {
            UI.displayError(e.toString());
        }
        List<Course> filteredCourses = this.filterCourses(courses, filters);
        if (!filteredCourses.isEmpty()) {
            return true;
        }
        return false;
    }
}
