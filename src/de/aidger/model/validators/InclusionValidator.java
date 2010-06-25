package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

/**
 * Validates the inclusion in the specified model class.
 *
 * @author aidGer Team
 */
public class InclusionValidator extends Validator {

    /**
     * List of strings in which to check for inclusion
     */
    protected String[] list = null;

    /**
     * Initialize the InclusionValidator class.
     *
     * @param model
     *            The model to validate
     * @param members
     *            The members of the model to validate
     * @param trans
     *            The translated names
     * @param inc
     *            Array of possible values
     */
    public InclusionValidator(AbstractModel model, String[] members, String[] trans,
            String[] inc) {
        super(model, members, trans);

        message = _("has an invalid value");
        list = inc;
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
        if (o == null || !(o instanceof String)) {
            return false;
        } else {
            String check = (String) o;
            for (String s : list) {
                if (check.equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

}
