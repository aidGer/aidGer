/*
 * This file is part of the aidGer project.
 *
 * Copyright (C) 2010-2011 The aidGer Team
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
import de.aidger.model.Runtime;
import de.aidger.view.UI;
import de.aidger.view.Wizard;
import de.aidger.view.wizard.DatabaseDetails;

import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.AbstractAction;

/**
 * This action saves the username before advancing.
 *
 * @author rmbl
 */
public class DatabaseDetailsFinishAction extends AbstractAction {

    public void actionPerformed(ActionEvent e) {
        Wizard dlg = (Wizard) ((JComponent) e.getSource())
            .getTopLevelAncestor();

        String type = Runtime.getInstance().getOption("database-type");
        DatabaseDetails dtls = ((DatabaseDetails) dlg.getCurrentPanel());

        Runtime.getInstance().setOption("database-driver", dtls.getDriver());
        String uri;
        if (type.equals("0")) {
            if (dtls.getHost().isEmpty()) {
                UI.displayError(_("The path can't be empty."));
                e.setSource(null);
                return;
            } 

            uri = "jdbc:derby:" + dtls.getHost() + ";create=true";
        } else if (type.equals("1")) {
            if (dtls.getHost().isEmpty() || dtls.getPort().equals(0) || dtls.getDatabase().isEmpty()) {
                UI.displayError(_("The hostname, port and database can't be empty."));
                e.setSource(null);
                return;
            }
            uri = "jdbc:mysql://" + dtls.getHost() + ":" + dtls.getPort() + "/" + dtls.getDatabase();
            if (!dtls.getUsername().isEmpty()) {
                uri += "?user=" + dtls.getUsername() + "&password";
                if (!dtls.getPassword().isEmpty()) {
                	uri += "=" + dtls.getPassword();
                }
                uri += "&autoReconnect=true";
            } else {
            	uri += "?autoReconnect=true";
            }            
        } else {
            if (dtls.getHost().isEmpty()) {
                UI.displayError(_("The JDBC Uri can't be empty."));
                e.setSource(null);
                return;
            }
            uri = dtls.getHost();
        }
        Runtime.getInstance().setOption("database-uri", uri);
    }

}