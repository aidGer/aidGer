/**
 * 
 */
package de.aidger.model.reports;

import java.util.Calendar;
import java.util.List;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.view.tabs.ProtocolViewerTab;
import de.aidger.view.tabs.Tab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;

/**
 * This class creates the activity protocol and adds it to the its viewer tab.
 * 
 * @author aidGer Team
 */
public class ProtocolCreator {

    /**
     * The number of days before today of which to display the activities.
     */
    private int numberOfDays;

    /**
     * The viewer tab of this protocol.
     */
    private ProtocolViewerTab protocolViewerTab = null;

    /**
     * Initializes a new ProtocolCreator and creates the first protocol.
     */
    public ProtocolCreator() {
        if (protocolViewerTab == null) {
            protocolViewerTab = new ProtocolViewerTab(this);
        }
        createProtocol();
    }

    /**
     * Adds all the activities to the table in the viewer which are in the
     * wanted time frame.
     */
    public void createProtocol() {
        numberOfDays = protocolViewerTab.getSpinnerValue();
        protocolViewerTab.clearTable();
        Object[] addedActivity = new Object[8];
        List<IActivity> activities = null;
        try {
            activities = new Activity().getAll();
        }
        catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar currentDate = Calendar.getInstance();
        //Display all activities if numberOfDays == -1.
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
                    addedActivity[0] = new Assistant().getById(activity
                        .getAssistantId());
                    addedActivity[1] = new Course().getById(activity
                        .getCourseId());
                    addedActivity[2] = activity.getType();
                    addedActivity[3] = activity.getDate();
                    addedActivity[4] = activity.getContent();
                    addedActivity[5] = activity.getSender();
                    addedActivity[6] = activity.getProcessor();
                    addedActivity[7] = activity.getRemark();
                    protocolViewerTab.addActivity(addedActivity);
                }
                catch (AdoHiveException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Returns the viewer tab of this report creator.
     * 
     * @return The viewer tab
     */
    public Tab getViewerTab() {
        return protocolViewerTab;
    }
}
