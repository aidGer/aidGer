/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2013 The aidGer Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.aidger.view.utils;

import java.awt.Toolkit;
import java.text.DecimalFormatSymbols;

import javax.swing.JComboBox;
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

    /**
     * Adds a currency filter to the given text field.
     * 
     * @param textField
     *            the text field that will be filtered
     */
    public static void addCurrencyFilter(JTextField textField) {
        addFilter(textField, "[0-9]+["
                + (new DecimalFormatSymbols()).getDecimalSeparator()
                + "]?[0-9]{0,2}");
    }

    /**
     * Adds a double filter to the given text field.
     * 
     * @param textField
     *            the text field that will be filtered
     */
    public static void addDoubleFilter(JTextField textField) {
        addFilter(textField, "[0-9]+["
                + (new DecimalFormatSymbols()).getDecimalSeparator()
                + "]?[0-9]*");
    }

    /**
     * Adds this pattern filter to the given editable combo box.
     * 
     * @param cmb
     *            the editable combo box that will be filtered
     * @param pattern
     *            the pattern to match
     */
    public static void addFilter(JComboBox cmb, String pattern) {
        addFilter((JTextField) cmb.getEditor().getEditorComponent(), pattern);
    }
}
