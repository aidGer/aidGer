package de.aidger.view.models;

import static de.aidger.utils.Translation._;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Assistant;

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
                _("Qualification") });
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#refresh()
     */
    @Override
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

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.models.MasterDataTableModel#getModel(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public AbstractModel getModel(int i) {
        Vector row = (Vector) getDataVector().elementAt(i);

        Assistant assistant = new Assistant();
        assistant.setFirstName((String) row.get(0));
        assistant.setLastName((String) row.get(1));
        assistant.setEmail((String) row.get(2));
        assistant.setQualification((String) row.get(3));

        return assistant;
    }
}
