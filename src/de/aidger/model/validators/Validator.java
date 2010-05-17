package de.aidger.model.validators;

import de.aidger.model.AbstractModel;

/**
 * Defines the interface every validator needs to implement.
 *
 * @author aidGer Team
 */
public abstract class Validator {

    /**
     * The model to check.
     */
    protected AbstractModel model;

    /**
     * The members of the model to check.
     */
    protected String[] members;

    /**
     * Initialize the PresenceValidator class.
     */
    public Validator() {
        this.model = null;
        this.members = new String[0];
    }

    /**
     * Initialize the PresenceValidator class.
     *
     * @param model The model to validate
     * @param members The members of the model to validate
     */
    public Validator(AbstractModel model, String[] members) {
        this.model = model;
        this.members = members;
    }

    /**
     * Validate the input.
     *
     * @return True if it validates
     */
    public boolean validate() {
        for(String member : members) {
            Object value = getValueOf(member);
            if (!validateVar(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validate the variable.
     *
     * @param o
     *            The variable to validate
     * @return True if the input validates, false otherwise
     */
    abstract public boolean validateVar(Object o);

    /**
     * Get the value of the given member variable.
     *
     * @param name The name of the member variable
     * @return The value of the member variable or null
     */
    public Object getValueOf(String name) {
        String functionname = "get" + name.substring(0,1).toUpperCase() +
                name.substring(1);
        try {
            java.lang.reflect.Method m = model.getClass().getDeclaredMethod(
                    functionname, new Class<?>[0]);
            return m.invoke(model, new Object[0]);
        } catch (Exception ex) {
            try {
                return model.getClass().getField(name);
            } catch (Exception e) {
                return null;
            }
        }
    }

}
