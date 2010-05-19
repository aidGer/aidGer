package de.aidger.model.validators;

import java.util.regex.Pattern;

import de.aidger.model.AbstractModel;

/**
 *
 * @author rmbl
 */
public class EmailValidator extends Validator {

    public EmailValidator(AbstractModel model, String[] members) {
        this.model = model;
        this.members = members;
    }

    @Override
    public boolean validateVar(Object o) {
        if (o == null) {
            return false;
        } else if (!o.getClass().getName().equals(String.class.getName())) {
            return false;
        }

        return Pattern.matches("/^[0-9a-z_\\.-]+@(([0-9]{1,3}\\.){3}[0-9]{1,3}|"
                + "([0-9a-z][0-9a-z-]*[0-9a-z]\\.)+[a-z]{2,3})$/i", (String) o);
    }

}
