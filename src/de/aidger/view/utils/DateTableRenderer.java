package de.aidger.view.utils;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renders a date in the format "dd.MM.yyyy" on a table.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class DateTableRenderer extends DefaultTableCellRenderer {

    private final DateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
     * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        String text = format.format((Date) value);

        return super.getTableCellRendererComponent(table, text, isSelected,
            hasFocus, row, column);
    }
}