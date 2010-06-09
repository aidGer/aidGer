package de.aidger.controller;

import static de.aidger.utils.Translation._;

import java.text.MessageFormat;

@SuppressWarnings("serial")
public class ActionNotFoundException extends Exception {
    /**
     * 
     * 
     * @param className
     */
    public ActionNotFoundException(String className) {
        super(MessageFormat.format(_("action class {0} not found"),
            new Object[] { className }));
    }
}
