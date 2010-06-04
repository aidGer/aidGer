package de.aidger.view.forms;

import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * This document filter allows only input that matches with the given pattern.
 * 
 * @author aidGer Team
 */
public class InputPatternFilter extends DocumentFilter {
    /**
     * The regular expression pattern.
     */
    private final String pattern;

    /**
     * Creates a pattern filter with the given regular expression pattern.
     * 
     * @param pattern
     *            the regular expression pattern
     */
    public InputPatternFilter(String pattern) {
        this.pattern = pattern;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.text.DocumentFilter#insertString(javax.swing.text.DocumentFilter
     * .FilterBypass, int, java.lang.String, javax.swing.text.AttributeSet)
     */
    @Override
    public void insertString(FilterBypass fb, int offset, String str,
            AttributeSet attr) throws BadLocationException {
        replace(fb, offset, 0, str, attr);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.text.DocumentFilter#replace(javax.swing.text.DocumentFilter
     * .FilterBypass, int, int, java.lang.String, javax.swing.text.AttributeSet)
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String str,
            AttributeSet attrs) throws BadLocationException {
        String oldStr = fb.getDocument().getText(0,
                fb.getDocument().getLength());

        String newStr = oldStr.substring(0, offset) + str
                + oldStr.substring(offset, oldStr.length());

        if (newStr.matches(pattern)) {
            fb.replace(offset, length, str, attrs);
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Adds this pattern filter to the given text field.
     * 
     * @param textField
     *            the text field that will be filtered
     * @param pattern
     *            the pattern to match
     */
    public static void addFilter(JTextField textField, String pattern) {
        ((AbstractDocument) textField.getDocument())
                .setDocumentFilter(new InputPatternFilter(pattern));
    }
}