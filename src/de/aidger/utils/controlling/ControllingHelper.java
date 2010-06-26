/**
 * 
 */
package de.aidger.utils.controlling;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Employment;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IEmployment;

/**
 * @author aidGer Team
 */
public class ControllingHelper {

    public ControllingHelper() {

    }

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int[] sortedYears = new int[years.size()];
        for (int i = 0; i < sortedYears.length; i++) {
            sortedYears[i] = years.get(i);
        }
        Arrays.sort(sortedYears);
        return sortedYears;
    }

    public int[] getYearMonths(int year) {
        Vector<Byte> months = new Vector<Byte>();
        List<Employment> employments;
        try {
            employments = new Employment().getEmployments((short) year,
                (byte) 1, (short) year, (byte) 12);
            for (Employment employment : employments) {
                if (employment.getYear() == year) {
                    if (!months.contains(employment.getMonth())) {
                        months.add(employment.getMonth());
                    }
                }
            }
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int[] sortedMonths = new int[months.size()];
        for (int i = 0; i < sortedMonths.length; i++) {
            sortedMonths[i] = months.get(i);
        }
        Arrays.sort(sortedMonths);
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

    public int[] getFunds(int year, int month) {
        Vector<Integer> funds = new Vector<Integer>();
        List<Employment> employments;
        try {
            employments = new Employment().getEmployments((short) year,
                (byte) 1, (short) year, (byte) month);
            for (Employment employment : employments) {
                if (employment.getYear() == year
                        && employment.getMonth() <= month) {
                    if (!funds.contains(employment.getFunds())) {
                        funds.add(employment.getFunds());
                    }
                }
            }
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int[] sortedFunds = new int[funds.size()];
        for (int i = 0; i < sortedFunds.length; i++) {
            sortedFunds[i] = funds.get(i);
        }
        Arrays.sort(sortedFunds);
        return sortedFunds;
    }
}
