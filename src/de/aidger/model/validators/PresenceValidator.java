package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

/**
 * Validates the presence of one or several members in the specified model class.
 *
 * @author aidGer Team
 */
public class PresenceValidator extends Validator {

    /**
     * Initialize the PresenceValidator class.
     */
    private PresenceValidator() {  }

    /**
     * Initialize the PresenceValidator class.
     *
     * @param model The model to validate
     * @param members The members of the model to validate
     */
    public PresenceValidator(AbstractModel model, String[] members) {
        super(model, members);
        message = _("is empty");
    }

    /**
     * Validate one variable independently.
     *
     * @param o The variable to validate
     * @return True if the variable validates
     */
    public static boolean validate(Object o) {
        return (new PresenceValidator()).validateVar(o);
    }

    /**
     * Validate the variable.
     *
     * @param o
     *            The variable to validate
     * @return True if the input validates, false otherwise
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null) {
            return false;
        } else if (o.getClass().getName().equals(String.class.getName()) &&
                    ((String) o).isEmpty()) {
            return false;
        }
        return true;
    }
}
