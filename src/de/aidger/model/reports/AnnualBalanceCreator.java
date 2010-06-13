/**
 * 
 */
package de.aidger.model.reports;

import de.aidger.view.tabs.BalanceViewerTab;
import de.aidger.view.tabs.Tab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * @author Phil
 * 
 */
public class AnnualBalanceCreator extends BalanceCreator {

    SemesterBalanceCreator semesterBalanceCreator = null;

    public AnnualBalanceCreator() {
        if (semesterBalanceCreator == null) {
            semesterBalanceCreator = new SemesterBalanceCreator();
        }
        if (balanceViewerTab == null) {
            balanceViewerTab = new BalanceViewerTab();
        }
    }

    /**
     * Adds all the semester of the given year, each containing the groups of
     * the respective semester, to the tab.
     * 
     * @param year
     *            The year, of which the semester should be added.
     * @throws AdoHiveException
     */
    public boolean addYear(int year, BalanceFilter filters) {
        // Lose the first two numbers of the year
        int semester = year % 100;
        boolean returnBoolean = false;
        /*
         * Contains the year in YYYY form, the previous, current and next
         * semester in that order.
         */
        String[] semesters = new String[4];
        semesters[0] = "" + year;
        switch (semester) {
        /*
         * If the given year is 2001-2008, (year % 100) will give a single
         * number below 9. Therefore, the previous, current and next semester
         * all need a leading 0 added.
         */
        case 0:
            semesters[1] = "WS" + "99" + "00";
            semesters[2] = "SS" + "00";
            semesters[3] = "WS" + "00" + "01";
            break;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
            semesters[1] = "WS0" + (semester - 1) + "0" + semester;
            semesters[2] = "SS0" + semester;
            semesters[3] = "WS0" + semester + "0" + (semester + 1);
            break;
        /*
         * If the given year is 2009, the previous and current semester will
         * both be a single number and therefore need a leading 0 added. The
         * next semester will be 10 and thus needs no adjustments.
         */
        case 9:
            semesters[1] = "WS0" + (semester - 1) + "0" + semester;
            semesters[2] = "SS0" + semester;
            semesters[3] = "WS0" + semester + (semester + 1);
            break;
        /*
         * If the given year is 2010, the current and next semesters will be 10
         * and 11 and therefore don't need a leading 0. The previous semester
         * will be 9 though.
         */
        case 10:
            semesters[1] = "WS0" + (semester - 1) + semester;
            semesters[2] = "SS" + semester;
            semesters[3] = "WS" + semester + (semester + 1);
            break;
        case 99:
            semesters[1] = "WS" + (semester - 1) + semester;
            semesters[2] = "SS" + semester;
            semesters[3] = "WS" + semester + "00";
            break;
        /*
         * In all other relevant cases (11 and higher), the semesters can be
         * used the way (year % 100) returns them.
         */
        default:
            semesters[1] = "WS" + (semester - 1) + semester;
            semesters[2] = "SS" + semester;
            semesters[3] = "WS" + semester + (semester + 1);
            break;
        }
        // Check if the semester has a course and add it if it does.
        for (String currentSemester : semesters) {
            if (semesterBalanceCreator.addSemester(currentSemester, filters)) {
                returnBoolean = true;
            }
        }
        return returnBoolean;
    }

    @Override
    public Tab getViewerTab() {
        return semesterBalanceCreator.getViewerTab();
    }
}
