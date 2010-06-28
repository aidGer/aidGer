/**
 * 
 */
package de.aidger.model.reports;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.view.UI;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;

/**
 * This class creates the activities for an activity protocol.
 * 
 * @author aidGer Team
 */
public class ProtocolCreator {

    /**
     * Initializes a new ProtocolCreator and creates the first protocol.
     */
    public ProtocolCreator() {
    }

    /**
     * Returns all the activities to the which are in the given time frame.
     * 
     * @param numberOfDays
     *            The number of days, of which to display activities.
     * @return The activities
     */
    public Vector<Object[]> createProtocol(int numberOfDays) {
        Object[] addedActivity;
        Vector<Object[]> addedActivities = new Vector<Object[]>();
        List<IActivity> activities = null;
        try {
            activities = new Activity().getAll();
        } catch (AdoHiveException e) {
            UI.displayError(e.toString());
        }
        Date checkDate;
        Calendar calendar = Calendar.getInstance();
        // Display all activities if numberOfDays == -1.
        if (numberOfDays != -1) {
            calendar.setTimeInMillis(calendar.getTimeInMillis() - 3600000 * 24
                    * numberOfDays);
            calendar.set(calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
            checkDate = calendar.getTime();
            // Remove the milliseconds that it took to calculate the time.
            checkDate.setTime(checkDate.getTime() - checkDate.getTime() % 1000);
        } else {
            calendar.set(1, 1, 1970, 0, 0, 0);
            checkDate = calendar.getTime();
        }
        for (IActivity activity : activities) {
            /**
             * If the date of the activity is after, or equal to the
             * currentDate, it lies within the wanted time frame.
             */
            if (checkDate.compareTo(activity.getDate()) <= 0) {
                try {
                    addedActivity = new Object[8];
                    String assistantName = "";
                    if (new Assistant().getById(activity.getAssistantId()) != null) {
                        assistantName = (new Assistant().getById(activity
                            .getAssistantId())).getFirstName()
                                + " "
                                + (new Assistant().getById(activity
                                    .getAssistantId())).getLastName();
                    }
                    addedActivity[0] = assistantName;
                    String courseName = "";
                    if (new Course().getById(activity.getCourseId()) != null) {
                        courseName = (new Course().getById(activity
                            .getCourseId())).getDescription()
                                + "("
                                + new Course().getById(activity.getCourseId())
                                    .getSemester() + ")";
                    }
                    addedActivity[1] = courseName;
                    addedActivity[2] = activity.getType();
                    addedActivity[3] = activity.getDate();
                    addedActivity[4] = activity.getContent();
                    addedActivity[5] = activity.getSender();
                    addedActivity[6] = activity.getProcessor();
                    addedActivity[7] = activity.getRemark();
                    addedActivities.add(addedActivity);
                } catch (AdoHiveException e) {
                    UI.displayError(e.toString());
                }
            }
        }
        return addedActivities;
    }
}
