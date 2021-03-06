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

package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.RepaintManager;
import javax.swing.ImageIcon;

import de.aidger.view.UI;

/**
 * This action sends the current contents to the printer.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class PrintAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public PrintAction() {
        putValue(Action.NAME, _("Print"));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,
    		Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        putValue(Action.SHORT_DESCRIPTION, _("Print program contents"));
        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
                "/de/aidger/res/icons/printer.png")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        /* Get the Panel to print */
        final javax.swing.JPanel panel = UI.getInstance().getCurrentTab();

        /* Get PrinterJob and modify default format */
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(_("Print current tab"));
        PageFormat format = pj.defaultPage();
        format.setOrientation(PageFormat.LANDSCAPE);

        /* Add a new printable which paints the panel */
        pj.setPrintable(new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum) {
                if (pageNum > 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());

                /* Disable double buffering while painting */
                RepaintManager curMgr = RepaintManager.currentManager(panel);
                curMgr.setDoubleBufferingEnabled(false);
                panel.paint(g2);
                curMgr.setDoubleBufferingEnabled(true);

                return Printable.PAGE_EXISTS;
            }
        });

        /* Display the print dialog and finally print */
        if (pj.printDialog()) {
            try {
                pj.print();
            } catch (Exception ex) {
                UI.displayError(ex.getMessage());
            }
        }
    }

}
