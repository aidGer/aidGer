package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;
import de.aidger.view.SettingsDialog;
import de.aidger.model.Runtime;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This action browses all files for pdf viewers.
 *
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class SettingsBrowseAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public SettingsBrowseAction() {
        putValue(Action.NAME, _("Browse"));
        putValue(Action.SHORT_DESCRIPTION, _("Browse for a pdf viewer"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SettingsDialog dlg = (SettingsDialog) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        File chosenFile = new File(Runtime.getInstance().getOption("pdf-viewer"));
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(chosenFile);
        boolean exit = false;
        File file = null;

        

        do {
            int retVal = fileChooser.showOpenDialog(dlg);

            if (retVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
                if (file.isDirectory() || !file.exists()) {
                    JOptionPane.showMessageDialog(dlg,
                            _("Please choose a file."));
                } else if (!file.canExecute()) {
                    JOptionPane.showMessageDialog(dlg,
                            _("Please select an executable."));
                } else {
                    exit = true;
                }
            } else {
                return;
            }
        } while (!exit);

        dlg.setPdfPath(file.getAbsolutePath());
    }

}
