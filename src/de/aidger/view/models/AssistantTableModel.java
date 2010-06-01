package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import de.aidger.model.models.Assistant;

/**
 * The class represents the table model for the master data assistants.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class AssistantTableModel extends DefaultTableModel {

    /**
     * The names of the columns.
     */
    private final String[] columnNames = { _("First Name"), _("Last Name"),
            _("Email"), _("Qualification") };

    /**
     * Constructs the table model for courses.
     */
    public AssistantTableModel() {
        setColumnIdentifiers(columnNames);

        refresh();
    }

    /**
     * Refreshs the whole table.
     */
    @SuppressWarnings("unchecked")
    public void refresh() {
        List<Assistant> assistants = (new Assistant()).getAll();

        Iterator<Assistant> it = assistants.iterator();
        while (it.hasNext()) {
            Assistant assistant = it.next();

            addRow(new Object[] { assistant.getFirstName(),
                    assistant.getLastName(), assistant.getEmail(),
                    assistant.getQualification() });
        }
    }
}
