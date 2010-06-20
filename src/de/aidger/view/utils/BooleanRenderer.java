package de.aidger.view.utils;

import static de.aidger.utils.Translation._;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Renders a boolean value on the table properly.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class BooleanRenderer extends DefaultTableCellRenderer {

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
        String text = _("no");

        if ((Boolean) value) {
            text = _("yes");
        }

        return super.getTableCellRendererComponent(table, text, isSelected,
            hasFocus, row, column);
    }
}