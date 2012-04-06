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
package de.aidger.model.reports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.utils.DateUtils;
import de.aidger.view.UI;
import siena.SienaException;

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
    public ArrayList<Object[]> createProtocol(int numberOfDays) {
        Object[] addedActivity;
        ArrayList<Object[]> addedActivities = new ArrayList<Object[]>();
        List<Activity> activities = null;
        Calendar calendar = Calendar.getInstance();
        Date currentDate;
        /*
         * The end date of the time frame will be the end of the current day.
         */
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE), 0, 0, 0);
        currentDate = new Date(calendar.getTimeInMillis() + 3600000 * 24);
        Date checkDate;
        calendar = Calendar.getInstance();
        // Display all activities if numberOfDays == -1.
        if (numberOfDays != -1) {
            calendar.setTimeInMillis(calendar.getTimeInMillis() - 3600000 * 24
                    * numberOfDays);
            calendar.set(calendar.get(Calendar.YEAR), calendar
                .get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
            // Remove the milliseconds that it took to calculate the time.
            calendar.setTimeInMillis(calendar.getTimeInMillis()
                    - calendar.getTimeInMillis() % 1000);
            checkDate = new Date(calendar.getTimeInMillis());
        } else {
            /*
             * If the number of days to display is -1, set the check date to
             * 1.1.1970 at 00:00. Since the calculated time is one hour after
             * that, we need to subtract one hour in milliseconds.
             */
            calendar.setTimeInMillis(-3600000);
            checkDate = new Date(calendar.getTimeInMillis());
        }
        try {
            /*
             * Only get the activities, that lie between the start (checkDate)
             * and end (currentDate) of the wanted time frame.
             */
            activities = new Activity().getActivities(new java.sql.Date(
                checkDate.getTime()), new java.sql.Date(currentDate.getTime()));
        } catch (SienaException e) {
            UI.displayError(e.toString());
        }
        for (Activity activity : activities) {
            /*
             * If the date of the activity is after, or equal to the
             * currentDate, it lies within the wanted time frame.
             */
            try {
                addedActivity = new Object[8];
                String assistantName = "";
                if (activity.getAssistantId() != null) {
                    assistantName = (new Assistant().getById(activity
                        .getAssistantId())).getFirstName()
                            + " "
                            + (new Assistant().getById(activity
                                .getAssistantId())).getLastName();
                }
                addedActivity[0] = assistantName;
                String courseName = "";
                if (activity.getCourseId() != null) {
                    courseName = (new Course().getById(activity.getCourseId()))
                        .getDescription()
                            + "("
                            + new Course().getById(activity.getCourseId())
                                .getSemester() + ")";
                }                
                addedActivity[1] = courseName;
                addedActivity[2] = activity.getType();
                addedActivity[3] = DateUtils.formatDate(activity.getDate());
                addedActivity[4] = activity.getContent();
                addedActivity[5] = activity.getSender();
                addedActivity[6] = activity.getProcessor();
                addedActivity[7] = activity.getRemark();
                addedActivities.add(addedActivity);
            } catch (SienaException e) {
                UI.displayError(e.toString());
            }
        }
        return addedActivities;
    }

    /**
     * Returns the specified activity as an array of objects.
     * 
     * @param activity
     *            The activity to convert.
     * @return The activity as an object array.
     */
    public Object[] getObjectArray(Activity activity) {
        Object[] addedActivity = new Object[8];
        String assistantName = "";
        try {
            if (activity.getAssistantId() != null) {
                assistantName = (new Assistant().getById(activity
                    .getAssistantId())).getFirstName()
                        + " "
                        + (new Assistant().getById(activity.getAssistantId()))
                            .getLastName();
            }
            addedActivity[0] = assistantName;
            String courseName = "";
            if (activity.getCourseId() != null) {
                courseName = (new Course().getById(activity.getCourseId()))
                    .getDescription()
                        + "("
                        + new Course().getById(activity.getCourseId())
                            .getSemester() + ")";
            }            
            addedActivity[1] = courseName;
            addedActivity[2] = activity.getType();
            addedActivity[3] = DateUtils.formatDate(activity.getDate());
            addedActivity[4] = activity.getContent();
            addedActivity[5] = activity.getSender();
            addedActivity[6] = activity.getProcessor();
            addedActivity[7] = activity.getRemark();
        } catch (SienaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return addedActivity;
    }
}
