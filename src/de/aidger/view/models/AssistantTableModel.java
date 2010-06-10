package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.Assistant;
import de.aidger.utils.Logger;
import de.aidger.view.forms.HourlyWageEditorForm.Qualification;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.IAssistant;

/**
 * The class represents the table model for the master data assistants.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class AssistantTableModel extends MasterDataTableModel {
    /**
     * Constructs the table model for assistants.
     */
    public AssistantTableModel() {
        super(new String[] { _("First Name"), _("Last Name"), _("Email"),
                _("Qualification"), _("ID") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh() {
        super.refresh();

        List<IAssistant> assistants = null;

        try {
            assistants = (new Assistant()).getAll();
        } catch (AdoHiveException e) {
            Logger.error(e.getMessage());
        }

        for (IAssistant assistant : assistants) {
            masterData.add(new Assistant(assistant));

            addRow(new Object[] { assistant.getFirstName(),
                    assistant.getLastName(), assistant.getEmail(),
                    Qualification.valueOf(assistant.getQualification()),
                    assistant.getId() });
        }
    }
}
