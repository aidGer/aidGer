package de.aidger.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.PropertyResourceBundle;

/**
 * Sets the translation of the program. If Strings of the current language can't
 * be found, it will fall back to the default language.
 * 
 * @author aidGer Team
 */
public class Translation {

    /**
     * The file name of the translation.
     */
    protected String filePath;

    /**
     * The bundle holding the translation.
     */
    protected static PropertyResourceBundle bundle = null;

    /**
     * Initializes this class. Resolves the file path of the translation files.
     * 
     * @param path
     *            The path of the aidGer settings.
     * @param language
     *            The language to which the program will be translated.
     */
    public Translation(String path, String language) {
        /* Create path if necessary */
        filePath = path + "lang/";
        File languagePath = new File(filePath);
        if ((!languagePath.exists() || !languagePath.isDirectory())
                && !languagePath.mkdirs()) {
            System.err.println("Konnte Verzeichnis für Übersetzung nicht "
                    + "erstellen");
        }
        filePath = filePath + language + ".properties";

        /* Load the language file */
        try {
            File inputFile = new File(filePath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            bundle = new PropertyResourceBundle(inputStream);
            inputStream.close();
        } catch (Exception e) {
        }
    }

    /**
     * Returns the translation of the specified string from the bundle.
     * 
     * @param id
     *            The string to translate.
     * @return The translated string or return the id.
     */
    public static String _(String id) {
        try {
            return bundle.getString(id);
        } catch (Exception e) {
            return id;
        }
    }

}
