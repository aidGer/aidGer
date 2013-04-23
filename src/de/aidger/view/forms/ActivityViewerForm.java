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

package de.aidger.view.forms;

import static de.aidger.utils.Translation._;

import java.awt.Color;

import javax.swing.JPanel;

import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.utils.DateUtils;
import de.aidger.view.models.UIAssistant;
import de.aidger.view.models.UICourse;
import siena.SienaException;

/**
 * A form used for viewing activities in detail.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class ActivityViewerForm extends JPanel {

    /**
     * Constructs an activity viewer form.
     * 
     * @param activity
     *            The activity that will be displayed
     */
    public ActivityViewerForm(Activity activity) {
        initComponents();

        try {
            if (activity.getAssistantId() == null) {
                assistant.setText(_("None"));
            } else {
                assistant.setText(new UIAssistant((new Assistant()).getById(activity.getAssistantId())).toString());
            }

            if (activity.getCourseId() == null) {
                course.setText(_("None"));
            } else {
                course.setText(new UICourse((new Course()).getById(activity.getCourseId())).toString());
            }

            processor.setText(activity.getProcessor());
            initiator.setText(activity.getSender());
            type.setText(activity.getType());
            documentType.setText(activity.getDocumentType());
            date.setText(DateUtils.formatDate(activity.getDate()));
            remark.setText(activity.getRemark());
            content.setText(activity.getContent());

            content.setLineWrap(true);
            content.setWrapStyleWord(true);
        } catch (SienaException e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblAssistant = new javax.swing.JLabel();
        lblCourse = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblProcessor = new javax.swing.JLabel();
        lblInitiator = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        lblDocumentType = new javax.swing.JLabel();
        lblRemark = new javax.swing.JLabel();
        lblContent = new javax.swing.JLabel();
        assistant = new javax.swing.JLabel();
        course = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        processor = new javax.swing.JLabel();
        initiator = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        documentType = new javax.swing.JLabel();
        remark = new javax.swing.JLabel();
        content = new javax.swing.JTextArea();

        setLayout(new java.awt.GridBagLayout());

        lblAssistant.setText(_("Assistant"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblAssistant, gridBagConstraints);

        lblCourse.setText(_("Course"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblCourse, gridBagConstraints);

        lblDate.setText(_("Date"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblDate, gridBagConstraints);

        lblProcessor.setText(_("Processor"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblProcessor, gridBagConstraints);

        lblInitiator.setText(_("Initiator"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblInitiator, gridBagConstraints);

        lblType.setText(_("Type"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblType, gridBagConstraints);

        lblDocumentType.setText(_("Document Type"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblDocumentType, gridBagConstraints);

        lblRemark.setText(_("Remark"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 50, 10, 10);
        add(lblRemark, gridBagConstraints);

        lblContent.setText(_("Content"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(lblContent, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(assistant, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(course, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(date, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(processor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(initiator, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(type, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(documentType, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(remark, gridBagConstraints);

        content.setBackground(new Color(0, 0, 0, 0));
        content.setColumns(20);
        content.setEditable(false);
        content.setRows(5);
        content.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0,
            0));
        content.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(content, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel assistant;
    private javax.swing.JTextArea content;
    private javax.swing.JLabel course;
    private javax.swing.JLabel date;
    private javax.swing.JLabel documentType;
    private javax.swing.JLabel initiator;
    private javax.swing.JLabel lblAssistant;
    private javax.swing.JLabel lblContent;
    private javax.swing.JLabel lblCourse;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDocumentType;
    private javax.swing.JLabel lblInitiator;
    private javax.swing.JLabel lblProcessor;
    private javax.swing.JLabel lblRemark;
    private javax.swing.JLabel lblType;
    private javax.swing.JLabel processor;
    private javax.swing.JLabel remark;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}
