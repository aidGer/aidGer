package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

import javax.swing.JButton;

import de.aidger.controller.ActionNotFoundException;
import de.aidger.controller.ActionRegistry;
import de.aidger.controller.actions.masterdata.MasterDataEditorCancelAction;
import de.aidger.controller.actions.masterdata.MasterDataEditorSaveAction;
import de.aidger.model.AbstractModel;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.aidger.view.UI;
import de.aidger.view.tabs.MasterDataViewerTab.MasterDataType;

/**
 * A abstract tab which will be used to add and edit the master data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public abstract class MasterDataEditorTab extends Tab {

    /**
     * The master data model for the editor.
     */
    @SuppressWarnings("unchecked")
    protected AbstractModel model = null;

    /**
     * The type of the master data.
     */
    private final MasterDataType type;

    /**
     * Constructs a master data editor tab.
     * 
     * @param type
     *            type of the master data
     */
    public MasterDataEditorTab(MasterDataType type) {
        this.type = type;

        init();
        initActions();
    }

    /**
     * Constructs a master data editor tab.
     * 
     * @param type
     *            type of the master data
     * @param model
     *            the master data model
     */
    @SuppressWarnings("unchecked")
    public MasterDataEditorTab(MasterDataType type, AbstractModel model) {
        this.type = type;
        this.model = model;

        init();
        initActions();
    }

    /**
     * Initializes the button actions.
     */
    private void initActions() {
        try {
            getButtonSave().setAction(
                    ActionRegistry.getInstance().get(
                            MasterDataEditorSaveAction.class.getName()));

            getButtonCancel().setAction(
                    ActionRegistry.getInstance().get(
                            MasterDataEditorCancelAction.class.getName()));
        } catch (ActionNotFoundException e) {
            UI.displayError(e.getMessage());
        }
    }

    /**
     * Initializes the components.
     */
    protected abstract void init();

    /**
     * Sets the model to the values of the gui form.
     * 
     */
    public abstract void setModel();

    /**
     * Returns the save button.
     * 
     * @return the save button
     */
    protected abstract JButton getButtonSave();

    /**
     * Returns the cancel button.
     * 
     * @return the cancel button
     */
    protected abstract JButton getButtonCancel();

    /**
     * Returns the master data model.
     * 
     * @return the master data model
     */
    @SuppressWarnings("unchecked")
    public AbstractModel getModel() {
        return model;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getTabName()
     */
    @Override
    public String getTabName() {
        if (isEditMode()) {
            switch (type) {
            case Course:
                return _("Edit course");
            case Assistant:
                return _("Edit assistant");
            case FinancialCategory:
                return _("Edit financial category");
            case HourlyWage:
                return _("Edit hourly wage");
            }
        }

        switch (type) {
        case Course:
            return _("Add course");
        case Assistant:
            return _("Add assistant");
        case FinancialCategory:
            return _("Add financial category");
        case HourlyWage:
            return _("Add hourly wage");
        }

        return "";
    }

    /**
     * Retrieves whether the tab is in edit mode.
     * 
     * @return whether the tab is in edit mode
     */
    public boolean isEditMode() {
        return model != null;
    }

    /**
     * Creates a new master data model.
     */
    public void createModel() {
        switch (type) {
        case Course:
            model = new Course();
            break;
        case Assistant:
            model = new Assistant();
            break;
        case FinancialCategory:
            model = new FinancialCategory();
            break;
        case HourlyWage:
            model = new HourlyWage();
            break;
        }
    }

    /**
     * Returns the type of the master data that is added / edited.
     * 
     * @return the type of the master data
     */
    public MasterDataType getType() {
        return type;
    }
}
