package de.aidger.model.validators;

import de.aidger.model.AbstractModel;

/**
 * Validates the presence of one or several members in the specified model class.
 *
 * @author aidGer Team
 */
public class PresenceValidator extends Validator {

    /**
     * Initialize the PresenceValidator class.
     *
     * @param model The model to validate
     * @param members The members of the model to validate
     */
    public PresenceValidator(AbstractModel model, String[] members) {
        super(model, members);
    }

    @Override
	public boolean validate() {
        for(String member : members) {
            Object value = getValueOf(member);
            if (value == null) {
                return false;
            } else if (value.getClass().getName().equals("String") &&
            		((String) value).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
