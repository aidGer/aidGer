/**
 * 
 */
package de.aidger.utils.reports;

import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Course;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * @author aidGer Team
 * 
 */
public class BalanceHelper {

    public BalanceHelper() {

    }

    public Vector getSemesters() {
        Vector semesters = new Vector();
        List<ICourse> courses = null;
        semesters.add("");
        try {
            courses = (new Course()).getAll();
        }
        catch (AdoHiveException e) {
            e.printStackTrace();
        }
        for (ICourse course : courses) {
            if (!semesters.contains(course.getSemester())) {
                semesters.add(course.getSemester());
            }
        }
        return semesters;
    }

    public Vector getYears() {
        Vector semesters = getSemesters();
        semesters.remove(0);
        Vector years = new Vector();
        years.add("");
        int k = 1;
        for (Object semester : semesters) {
            char[] semesterChar = ((String) semester).toCharArray();
            int year = 0;
            if (Character.isDigit(semesterChar[0])) {
                for (int i = 0; i < semesterChar.length; i++) {
                    year = year + Character.getNumericValue(semesterChar[i])
                            * (int) Math.pow(10, 3 - i);
                }
            } else {
                int i = 0;
                while (!Character.isDigit(semesterChar[i])) {
                    i++;
                }
                year = 2000;
                int power = 10;
                for (int j = i; j < semesterChar.length; j++) {
                    year = year + Character.getNumericValue(semesterChar[j])
                            * power;
                    if (power == 10) {
                        power = 1;
                    } else {
                        power = 10;
                    }
                }
            }
            if (!years.contains(year)) {
                years.add(k, year);
            }
            k++;
        }
        return years;
    }
}
