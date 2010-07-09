package de.aidger.view.utils;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * This document filter allows only number input.
 * 
 * @author aidGer Team
 */
public class InputNumberFilter extends DocumentFilter {

    /**
     * Creates a number filter.
     */
    public InputNumberFilter() {
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

        try {
            NumberFormat.getInstance().parse(newStr);

            fb.replace(offset, length, str, attrs);
        } catch (ParseException e) {
        }
    }

    /**
     * Adds this number filter to the given text field.
     * 
     * @param textField
     *            the text field that will be filtered
     */
    public static void addFilter(JTextField textField) {
        ((AbstractDocument) textField.getDocument())
            .setDocumentFilter(new InputNumberFilter());
    }

    /**
     * Adds this number filter to the given editable combo box.
     * 
     * @param cmb
     *            the editable combo box that will be filtered
     */
    public static void addFilter(JComboBox cmb) {
        addFilter((JTextField) cmb.getEditor().getEditorComponent());
    }
}