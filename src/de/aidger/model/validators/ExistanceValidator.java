package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * Validates the existance of an id in the database.
 *
 * @author aidGer Team
 */
public class ExistanceValidator extends Validator {

    /**
     * The type of model to check for.
     */
    private AbstractModel type = null;

    /**
     * Initializes the ExistanceValidator class.
     *
     * @param model
     *              The model to validate
     * @param members
     *              The member variables to check
     * @param trans
     *              The translated names
     * @param type
     *              The type of model to check for
     */
    public ExistanceValidator(AbstractModel model, String[] members,
            String[] trans, AbstractModel type) {
        super(model, members, trans);

        this.message = _("doesn't exist in the database");
        this.type = type;
    }

    /**
     * Validate the existance of the object
     *
     * @param o
     *          The object to validate
     * @return True if the model exists
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null) {
            return true;
        } else if (!(o instanceof Integer)) {
            return false;
        } else {
            try {
                return type.getById((Integer) o) != null;
            } catch (AdoHiveException ex) {
                return false;
            }
        }
    }

}
