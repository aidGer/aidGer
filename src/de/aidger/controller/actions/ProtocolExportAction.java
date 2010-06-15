package de.aidger.controller.actions;

import de.aidger.utils.pdf.ProtocolConverter;
import de.aidger.view.UI;
import de.aidger.view.tabs.ProtocolViewerTab;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import static de.aidger.utils.Translation._;

/**
 * This action exports the protocol.
 *
 * @author aidGer Team
 */
public class ProtocolExportAction extends AbstractAction{

   /**
     * Initializes the action.
     */
    public ProtocolExportAction() {
        putValue(Action.NAME, _("Export"));
        putValue(Action.SHORT_DESCRIPTION, _("Export the protocol to a pdf file"));

        putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource(
            "/de/aidger/view/icons/report--arrow.png")));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
        ProtocolViewerTab tab = (ProtocolViewerTab) UI.getInstance().
                getCurrentTab();

        JFileChooser fileChooser = new JFileChooser();
        File file;
        FileFilter pdfFilter = new FileFilter() {
            @Override
            public boolean accept(File arg0) {
                String fileName = arg0.getName();
                int fileExtensionStart = fileName.lastIndexOf('.');
                String fileExtension = fileName
                    .substring(fileExtensionStart + 1);
                return arg0.isDirectory() || fileExtension.equals("pdf");
            }

            @Override
            public String getDescription() {
                return _("PDF files");
            }
        };

        fileChooser.removeChoosableFileFilter(fileChooser
            .getAcceptAllFileFilter());
        fileChooser.addChoosableFileFilter(pdfFilter);

        boolean exit = false;

        do {
            int retVal = fileChooser.showDialog(tab, _("Export"));

            if (retVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                if (file.isDirectory()) {
                    // This shouldn't happen but check for it anyways
                    JOptionPane.showMessageDialog(tab,
                        _("Please choose a file."));
                } else if (file.exists()) {
                    // Ask if the user wants to overwrite the file
                    retVal = JOptionPane.showOptionDialog(tab,
                        _("Are you sure you want to overwrite the file?"),
                        _("Overwrite file?"), JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                    if (retVal == JOptionPane.YES_OPTION) {
                        exit = true;
                    }
                } else {
                    exit = true;
                }
            } else {
                return;
            }
        } while (!exit);

        new ProtocolConverter(file, tab.getDays());
    }

}