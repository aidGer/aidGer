package de.aidger.model.inspectors;

import static de.aidger.utils.Translation._;

import java.util.List;

import de.aidger.model.models.Assistant;
import de.aidger.view.models.UIAssistant;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Inspector for identical assistants in the database.
 * 
 * @author aidGer Team
 */
public class IdenticalAssistantInspector extends Inspector {

    /**
     * The assistant to be checked.
     */
    private final UIAssistant assistant;

    /**
     * Creates an identical assistant inspector.
     * 
     * @param assistant
     *            the assistant to be checked
     */
    public IdenticalAssistantInspector(UIAssistant assistant) {
        this.assistant = assistant;
    }

    /*
     * Checks for identical assistants in the database.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void check() {
        try {
            List<Assistant> assistants = (new Assistant()).getAll();

            if (assistants.contains(assistant)) {
                result = _("The same assistant exists already in the database.");
            }
        } catch (AdoHiveException e) {
        }
    }

}
