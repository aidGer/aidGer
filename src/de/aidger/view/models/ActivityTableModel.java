package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Date;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.unistuttgart.iste.se.adohive.controller.AdoHiveController;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IActivity;
import java.util.ArrayList;
import java.util.List;

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
        super(new String[] { _("ID"), _("Assistant"), _("Course"), _("Date"),
                _("Initiator"), _("Processor"), _("Type"), _("Document Type"),
                _("Content"), _("Remark") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
     */
    @Override
    public Class<?> getColumnClass(int column) {
        // sort specific columns properly
        if (column == 0) {
            return Integer.class;
        } else if (column == 3) {
            return Date.class;
        }

        return super.getColumnClass(column);
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getRowValue(de.aidger.model.AbstractModel, int) 
     */
    @Override
    protected Object getRowValue(AbstractModel model, int row) {
        try {
            Activity activity = (Activity) model;
            switch (row) {
                case 0: return activity.getId();
                case 1:
                    UIAssistant assistant = (activity.getAssistantId() == null) ?
                        new UIAssistant() : new UIAssistant((new Assistant())
                            .getById(activity.getAssistantId()));
                    return assistant;
                case 2:
                    UICourse course = (activity.getCourseId() == null) ? new UICourse()
                    : new UICourse((new Course()).getById(activity
                        .getCourseId()));
                    return course;
                case 3: return activity.getDate();
                case 4: return activity.getSender();
                case 5: return activity.getProcessor();
                case 6: return activity.getType();
                case 7: return activity.getDocumentType();
                case 8: return activity.getContent();
                case 9: return activity.getRemark();
            }
        } catch (AdoHiveException ex) {
        }
        return null;
    }

    /**
     * (non-Javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModelFromDB(int)
     */
    @Override
    protected AbstractModel getModelFromDB(int idx) {
        try {
            return new Activity(AdoHiveController.getInstance().getActivityManager().get(idx));
        } catch (AdoHiveException ex) {
            return null;
        }
    }

    /**
     * (non-javadoc)
     *
     * @see de.aidger.view.models.TableModel#getModels()
     */
    protected List<AbstractModel> getModels() {
        List<AbstractModel> ret = new ArrayList<AbstractModel>();
        try {
            List<IActivity> lst = (new Activity()).getAll();
            for (IActivity e : lst) {
                ret.add(new Activity(e));
            }
        } catch (AdoHiveException ex) {
        }

        return ret;
    }

    /**
     * (non-Javadoc)
     *
     * @see javax.swing.table.AbstractTableModel#getRowCount() 
     */
    public int getRowCount() {
        try {
            return (new Activity()).size();
        } catch (AdoHiveException ex) {
            return 0;
        }
    }
}
