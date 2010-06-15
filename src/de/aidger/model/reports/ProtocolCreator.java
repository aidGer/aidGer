/**
 * 
 */
package de.aidger.model.reports;

import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.view.tabs.ProtocolViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;

/**
 * This class creates the activity protocol and adds it to the its viewer tab.
 * 
 * @author aidGer Team
 */
public class ProtocolCreator {

    /**
     * The viewer tab of this protocol.
     */
    private final ProtocolViewerTab protocolViewerTab = null;

    /**
     * Initializes a new ProtocolCreator and creates the first protocol.
     * 
     */
    public ProtocolCreator() {
    }

    /**
     * Adds all the activities to the table in the viewer which are in the
     * wanted time frame.
     * 
     * @throws AdoHiveException
     */
    public Vector createProtocol(int numberOfDays) {
        Object[] addedActivity;
        Vector addedActivities = new Vector();
        List<IActivity> activities = null;
        try {
            activities = new Activity().getAll();
        } catch (AdoHiveException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Calendar currentDate = Calendar.getInstance();
        // Display all activities if numberOfDays == -1.
        if (numberOfDays != -1) {
            currentDate.add(Calendar.DATE, (-numberOfDays));
        } else {
            currentDate.setTimeInMillis(-3600000);
        }
        for (IActivity activity : activities) {
            /**
             * If the date of the activity is after, or equal to the
             * currentDate, it lies within the wanted time frame.
             */
            if (currentDate.getTime().before(activity.getDate())
                    || activity.getDate().equals(currentDate.getTime())) {
                try {
                    addedActivity = new Object[8];
                    addedActivity[0] = (new Assistant().getById(activity
                        .getAssistantId())).getFirstName()
                            + " "
                            + (new Assistant().getById(activity
                                .getAssistantId())).getLastName();
                    addedActivity[1] = (new Course().getById(activity
                        .getCourseId())).getDescription();
                    addedActivity[2] = activity.getType();
                    addedActivity[3] = activity.getDate();
                    addedActivity[4] = activity.getContent();
                    addedActivity[5] = activity.getSender();
                    addedActivity[6] = activity.getProcessor();
                    addedActivity[7] = activity.getRemark();
                    addedActivities.add(addedActivity);
                } catch (AdoHiveException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return addedActivities;
    }
}
