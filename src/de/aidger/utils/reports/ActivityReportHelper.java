/**
 * 
 */
package de.aidger.utils.reports;

import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Employment;
import de.aidger.model.reports.ActivityEmployment;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

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

    public Vector<ActivityEmployment> getEmployments(Assistant assistant) {
        try {
            Vector<ActivityEmployment> activityEmployments = new Vector<ActivityEmployment>();
            List<Employment> employments = new Employment()
                .getEmployments(assistant);
            for (Employment employment : employments) {

            }
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
