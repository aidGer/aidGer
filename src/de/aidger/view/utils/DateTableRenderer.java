package de.aidger.view.utils;

import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renders a date in the given format on a table.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class DateTableRenderer extends DefaultTableCellRenderer {

    private final DateFormat format;

    public DateTableRenderer(String f) {
        format = new SimpleDateFormat(f);
    }

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

        Calendar cal = Calendar.getInstance();
        cal.clear();

        // it could be that no date shall be shown
        if (value.equals(cal.getTime())) {
            text = "";
        }

        return super.getTableCellRendererComponent(table, text, isSelected,
            hasFocus, row, column);
    }
}