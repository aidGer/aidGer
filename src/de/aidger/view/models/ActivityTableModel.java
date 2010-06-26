package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Date;
import java.util.List;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.utils.Logger;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;
import de.unistuttgart.iste.se.adohive.model.IAssistant;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * The class represents the table model for the activities.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ActivityTableModel extends TableModel {
    /**
     * Constructs the table model for activities.
     */
    public ActivityTableModel() {
        super(new String[] { _("Assistant"), _("Course"), _("Date"),
                _("Sender"), _("Processor"), _("Type"), _("Document Type"),
                _("Content"), _("Remark"), _("ID") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.TableModel#getAllModels()
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void getAllModels() {
        List<IActivity> activities = null;

        try {
            activities = (new Activity()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IActivity a : activities) {
            Activity activity = new Activity(a);

            models.add(activity);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @seede.aidger.view.models.TableModel#convertModelToRow(de.aidger.model.
     * AbstractModel)
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Object[] convertModelToRow(AbstractModel model) {
        Activity activity = (Activity) model;

        try {
            IAssistant a = (new Assistant()).getById(activity.getAssistantId());

            ICourse c = (new Course()).getById(activity.getCourseId());

            return new Object[] { new UIAssistant(a), new UICourse(c),
                    activity.getDate(), activity.getSender(),
                    activity.getProcessor(), activity.getType(),
                    activity.getDocumentType(), activity.getContent(),
                    activity.getRemark(), activity.getId() };
        } catch (AdoHiveException e) {
            return new Object[] {};
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 2) {
            return Date.class;
        }

        return super.getColumnClass(column);
    }
}
