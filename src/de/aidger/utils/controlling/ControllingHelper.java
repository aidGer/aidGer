/**
 * 
 */
package de.aidger.utils.controlling;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Employment;
import de.aidger.view.UI;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * This class is used to calculate all the available years, the months of a year
 * and the funds of a given year and month.
 * 
 * @author aidGer Team
 */
public class ControllingHelper {

    /**
     * Initializes a new controlling helper.
     */
    public ControllingHelper() {
    }

    /**
     * Determines all the available years of all employments.
     * 
     * @return The years
     */
    public int[] getEmploymentYears() {
        Vector<Short> years = new Vector<Short>();
        try {
            List<IEmployment> employments = new Employment().getAll();
            for (IEmployment employment : employments) {
                if (!years.contains(employment.getYear())) {
                    years.add(employment.getYear());
                }
            }
        } catch (AdoHiveException e) {
            UI.displayError(e.toString());
        }
        int[] sortedYears = new int[years.size()];
        for (int i = 0; i < sortedYears.length; i++) {
            sortedYears[i] = years.get(i);
        }
        Arrays.sort(sortedYears);
        return sortedYears;
    }

    /**
     * Determines all the months of the employments of a given year.
     * 
     * @param year
     *            The year of which to get the months.
     * @return The months
     */
    public int[] getYearMonths(int year) {
        Vector<Byte> months = new Vector<Byte>();
        List<Employment> employments;
        try {
            employments = new Employment().getEmployments((short) year,
                (byte) 1, (short) year, (byte) 12);
            for (Employment employment : employments) {
                if (!months.contains(employment.getMonth())) {
                    months.add(employment.getMonth());
                }
            }
        } catch (AdoHiveException e) {
            UI.displayError(e.toString());
        }
        int[] sortedMonths = new int[months.size()];
        for (int i = 0; i < sortedMonths.length; i++) {
            sortedMonths[i] = months.get(i);
        }
        Arrays.sort(sortedMonths);
        /*
         * Since the controlling reports always include all the months of a year
         * before the given month, all the months following the first month must
         * be included.
         */
        if (sortedMonths.length == 12 - sortedMonths[0] + 1) {
            return sortedMonths;
        } else {
            int[] fullSortedMonths = new int[12 - sortedMonths[0] + 1];
            for (int i = 0; i < fullSortedMonths.length; i++) {
                fullSortedMonths[i] = sortedMonths[0] + i;
            }
            return fullSortedMonths;
        }
    }

    /**
     * Determines all the funds the employments of a given year and month
     * 
     * @param year
     *            The year of which to get the funds.
     * @param month
     *            The month of which to get the funds.
     * @return The funds.
     */
    public int[] getFunds(int year, int month) {
        Vector<Integer> funds = new Vector<Integer>();
        List<Employment> employments;
        try {
            /*
             * Since the controlling reports always include all the months
             * before the given one, the funds of the ones before it need to be
             * included.
             */
            employments = new Employment().getEmployments((short) year,
                (byte) 1, (short) year, (byte) month);
            for (Employment employment : employments) {
                if (!funds.contains(employment.getFunds())) {
                    funds.add(employment.getFunds());
                }
            }
        } catch (AdoHiveException e) {
            UI.displayError(e.toString());
        }
        int[] sortedFunds = new int[funds.size()];
        for (int i = 0; i < sortedFunds.length; i++) {
            sortedFunds[i] = funds.get(i);
        }
        Arrays.sort(sortedFunds);
        return sortedFunds;
    }
}
