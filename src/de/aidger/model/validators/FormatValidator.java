package de.aidger.model.validators;

import static de.aidger.utils.Translation._;
import de.aidger.model.AbstractModel;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Validates the format of member variables in a specified model.
 *
 * @author aidGer Team
 */
public class FormatValidator extends Validator {

    /**
     * Matches the string against the given format.
     */
    private Pattern pattern;

    /**
     * Initializes the FormatValidator class.
     *
     * @param model
     *              The model to validate
     * @param members
     *              The member variables to check
     * @param format
     *              The format to check for
     * @param casesensitive
     *              Is the regex string casesensitive or not
     */
    public FormatValidator(AbstractModel model, String[] members,
            String format, boolean casesensitive) {
        super(model, members);

        this.message = _("has an incorrect format");
        
        if (casesensitive) {
            pattern = Pattern.compile(format);
        } else {
            pattern = Pattern.compile(format, Pattern.CASE_INSENSITIVE);
        }


    }

    /**
     * Initializes the FormatValidator class.
     *
     * @param model
     *              The model to validate
     * @param members
     *              The member variables to check
     * @param format
     *              The format to check for
     */
    public FormatValidator(AbstractModel model, String[] members,
           String format) {
        this(model, members, format, true);
    }

    /**
     * Validate a string to the format
     *
     * @param o
     *            Variable to validate
     * @return True if the variable validates
     */
    @Override
    public boolean validateVar(Object o) {
        if (o == null || !(o instanceof String)) {
            return false;
        }

        Matcher matcher = pattern.matcher((CharSequence) o);
        return matcher.matches();
    }

}
