package de.aidger.view.utils;

import de.aidger.model.AbstractModel;

/**
 * Factory for UI models.
 * 
 * @author aidGer Team
 */
public class UIModelFactory {

    /**
     * Creates an UI model from given abstract model.
     * 
     * @param model
     *            the abstract model
     * @return the created UI model. If there was an error, null is returned.
     */
    @SuppressWarnings("unchecked")
    public static Object create(AbstractModel model) {
        try {
            Class classUI = Class.forName("de.aidger.view.models.UI"
                    + model.getClass().getSimpleName());

            Class classInterface = Class
                .forName("de.unistuttgart.iste.se.adohive.model.I"
                        + model.getClass().getSimpleName());

            return classUI.getConstructor(classInterface).newInstance(
                classInterface.cast(model));
        } catch (Exception e) {
        }

        return null;
    }
}
