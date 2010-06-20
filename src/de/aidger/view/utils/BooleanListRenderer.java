package de.aidger.view.utils;

import static de.aidger.utils.Translation._;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 * Renders a boolean value on a list properly.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class BooleanListRenderer extends DefaultListCellRenderer {

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.swing.DefaultListCellRenderer#getListCellRendererComponent(javax
     * .swing.JList, java.lang.Object, int, boolean, boolean)
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        String text = _("no");

        if ((Boolean) value) {
            text = _("yes");
        }

        return super.getListCellRendererComponent(list, text, index,
            isSelected, cellHasFocus);
    }
}