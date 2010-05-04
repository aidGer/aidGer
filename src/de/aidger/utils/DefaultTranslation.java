package de.aidger.utils;

import java.util.MissingResourceException;

/**
 * Contains the default language
 * 
 * @author aidGer Team
 */
public class DefaultTranslation extends Translation {

    public DefaultTranslation(String path) {
        // TODO specify the id's for languages
        /*
         * The default translation is the translation with the german language.
         */
        super(path, "de");
    }

    /**
     * Returns the translated string of the id.
     * 
     * @param id
     *            The string to translate.
     * @return The translated string.
     */
    @Override
    public String get(String id) {
        try {
            return bundle.getString(id);
        } catch (MissingResourceException e) {
            /*
             * If the string is not translated in the default translation,
             * return the untranslated string.
             */
            return id;
        }
    }
}
