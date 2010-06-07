package de.aidger.model.validators;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

/**
 * Validates email adresses in the specified model class.
 *
 * @author aidGer Team
 */
public class EmailValidator extends Validator {

    /**
     * Initializes the EmailValidator class.
     *
     * @param model
     *            The model to validate
     * @param members
     *            The member variables of the model
     */
    public EmailValidator(AbstractModel model, String[] members) {
        super(model, members);
        message = _("is an invalid email address");
    }

    /**
     * Validate an email address.
     *
     * @param o
     *            Variable to validate
     * @return True if the variable validates
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null) {
            return false;
        } else if (!o.getClass().getName().equals(String.class.getName())) {
            return false;
        }

        String expression =
                "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = (String) o;
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();        
    }

}
