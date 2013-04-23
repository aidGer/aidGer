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

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.View;

/**
 * A multi line cell renderer for swing tables.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MultiLineTableRenderer extends JTextArea implements
        TableCellRenderer {

    /**
     * Constructs the mulit line cell renderer.
     */
    public MultiLineTableRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
     * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        setFont(table.getFont());

        if (hasFocus) {
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));

            if (table.isCellEditable(row, column)) {
                setForeground(UIManager.getColor("Table.focusCellForeground"));
                setBackground(UIManager.getColor("Table.focusCellBackground"));
            }
        } else if (isSelected) {
            setBorder(UIManager.getBorder("Table.cellNoFocusBorder"));
        } else {
            Color bg = table.getBackground();

            if (row % 2 == 0) {
                bg = UIManager.getColor("Table.alternateRowColor");
            }

            setBorder(BorderFactory.createMatteBorder(2, 5, 2, 5, bg));
        }

        adjustRowHeight(table, row, column, value);

        return this;
    }

    /**
     * Sets the row height on the table.
     */
    private void adjustRowHeight(JTable table, int row, int column, Object value) {
        if (value != null) {
            String text = value.toString();
            setText(text);

            View view = getUI().getRootView(this);
            view.setSize((float) table.getColumnModel().getColumn(column)
                .getWidth() - 3, -1);
            float y = view.getPreferredSpan(View.Y_AXIS);
            int h = (int) Math.ceil(y + 3);

            if (table.getRowHeight(row) != h) {
                table.setRowHeight(row, h);
            }
        } else {
            setText("");
        }
    }
}
