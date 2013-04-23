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

/**
 * 
 */
package de.aidger.utils.pdf;

import static de.aidger.utils.Translation._;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;

import de.aidger.model.Runtime;
import de.aidger.utils.DateUtils;
import de.aidger.view.UI;

/**
 * This class converts activity reports to a format for itext and exports them
 * 
 * @author aidGer Team
 */
public class ActivityReportConverter {

    /**
     * Whether the file was created successfully.
     */
    private boolean fileCreated = false;

    /**
     * The name of the assistant.
     */
    private final String assistant;

    /**
     * The table rows that this report should contain.
     */
    private final ArrayList<String[]> tableRows;

    /**
     * The stamper used to stamp the fields.
     */
    private PdfStamper stamper;

    /**
     * The content byte used for this report.
     */
    private PdfContentByte contentByte;

    /**
     * The reader used to read from the template.
     */
    private PdfReader reader;

    /**
     * The AcroFields which is used to write data to the fields of the template.
     */
    private AcroFields form;

    /**
     * Initializes a new ActivityReportConverter and creates the .pdf file.
     * 
     * @param file
     *            The file for the report.
     */
    public ActivityReportConverter(File file, ArrayList<String[]> tableRows,
            String assistant) {
        this.tableRows = tableRows;
        this.assistant = assistant;
        file = checkExtension(file);
        makeNewDocument(file);
        if (fileCreated) {
            stampFields();
            writeLogo();
            createTable();
            try {
                stamper.close();
            } catch (DocumentException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            } catch (IOException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            /*
             * Open the created document if the setting is enabled with the
             * specified pdf viewer.
             */
            if (Runtime.getInstance().getOption("auto-open").equals("true")) {
                try {
                    java.lang.Runtime.getRuntime().exec(
                        new String[] {
                                Runtime.getInstance().getOption("pdf-viewer"),
                                file.getAbsolutePath() });
                } catch (IOException e) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e1) {
                        UI.displayError(_("No pdf viewer could be found!"));
                    }
                }
            }
        }
    }

    /**
     * Adds the specific values to the fields in the template.
     */
    private void stampFields() {
        form = stamper.getAcroFields();
        try {
            Calendar calendar = Calendar.getInstance();
            BaseFont fieldFont = BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.CP1252, false);
            BaseFont fatFieldFont = BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, false);
            form.setFieldProperty("CreatorName", "textfont", fieldFont, null);
            form.setFieldProperty("CreatorName", "textsize", 12.0f, null);
            form.setFieldProperty("CreatorName", "textcolor", BaseColor.BLACK,
                null);
            form.setField("CreatorName", Runtime.getInstance()
                .getOption("name"));
            form.setFieldProperty("AssistantName", "textfont", fatFieldFont,
                null);
            form.setFieldProperty("AssistantName", "textsize", 12.0f, null);
            form.setFieldProperty("AssistantName", "textcolor",
                BaseColor.BLACK, null);
            form.setField("AssistantName", assistant);
            form.setFieldProperty("Date", "textfont", fieldFont, null);
            form.setFieldProperty("Date", "textsize", 12.0f, null);
            form.setFieldProperty("Date", "textcolor", BaseColor.BLACK, null);
            form.setField("Date", DateUtils.formatDate(calendar.getTime()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Checks if the extension of the file is in fact .pdf. If not, it adds the
     * .pdf extension to the file name.
     * 
     * @param file
     *            The file to check.
     * @return The file name with the correct extension.
     */
    private File checkExtension(File file) {
        String fileName = file.getName();
        int fileExtensionStart = fileName.lastIndexOf('.');
        String fileExtension = fileName.substring(fileExtensionStart + 1);
        if (!fileExtension.equals("pdf")) {
            return new File(file.getPath() + ".pdf");
        }
        return file;
    }

    /**
     * Creates a new document.
     * 
     * @param file
     *            The file to create.
     */
    private void makeNewDocument(File file) {
        try {
            File template = new File(Runtime.getInstance().getConfigPath()
                    + "/templates/ActivityReportTemplate.pdf");
            URL templateURL = null;
            if (template.exists()) {
                templateURL = template.toURI().toURL();
            } else {
                templateURL = getClass().getResource(
                    "/de/aidger/res/pdf/ActivityReportTemplate.pdf");
            }
            reader = new PdfReader(templateURL);
            stamper = new PdfStamper(reader, new FileOutputStream(file));
            fileCreated = true;
            contentByte = stamper.getOverContent(1);
        } catch (FileNotFoundException e1) {
            UI.displayError(_("File could not be created.") + " "
                    + _("Please close all processes that are using the file."));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Writes the logos and the address of the institute.
     */
    private void writeLogo() {
        try {
            Font generatedByFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 8);
            Image aidger = Image.getInstance(getClass().getResource(
                "/de/aidger/res/pdf/AidgerLogo.png"));
            aidger.scaleAbsolute(80.0f, 20.0f);
            PdfPTable table = new PdfPTable(2);
            table.setTotalWidth(reader.getPageSize(1).getRight());
            PdfPCell cell = new PdfPCell(new Phrase(_("Generated by: "),
                generatedByFont));
            cell.setBorder(0);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPaddingBottom(5);
            cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
            table.addCell(cell);
            cell = new PdfPCell(Image.getInstance(aidger));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            table.addCell(cell);
            table.writeSelectedRows(0, -1, 0, 25, contentByte);
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates the table of employments.
     */
    private void createTable() {
        try {
            Font tableTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED),
                10);
            String[] tableTitles = { "Zeitraum", "Veranstaltung", "Umfang" };
            PdfPTable contentTable = new PdfPTable(1);
            PdfPTable titleTable = new PdfPTable(
                new float[] { 0.2f, 0.6f, 0.2f });
            /*
             * Create the titles of the table entries.
             */
            for (int i = 0; i < tableTitles.length; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(tableTitles[i],
                    tableTitleFont));
                titleTable.addCell(cell);
            }
            PdfPCell cell = new PdfPCell(titleTable);
            cell.setPaddingTop(10.0f);
            cell.setPaddingBottom(2.0f);
            cell.setBorder(0);
            contentTable.addCell(cell);
            cell = new PdfPCell(addRows());
            cell.setBorder(0);
            contentTable.addCell(cell);
            float xPos = 60, yPos = 500, width = reader.getPageSize(1)
                .getWidth() - 120f;
            if (form.getFieldPositions("TableField") != null) {
                FieldPosition position = form.getFieldPositions("TableField")
                    .get(0);
                xPos = position.position.getLeft();
                yPos = position.position.getTop();
                width = position.position.getWidth();
            }
            contentTable.setTotalWidth(width);
            contentTable.writeSelectedRows(0, -1, xPos, yPos, contentByte);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Adds the rows of employments to the table.
     */
    private PdfPTable addRows() {
        PdfPTable contentTable = new PdfPTable(new float[] { 0.2f, 0.6f, 0.2f });
        try {
            Font tableContentFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            for (String[] row : tableRows) {
                for (int i = 0; i < row.length; i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(row[i],
                        tableContentFont));
                    cell.setPaddingBottom(5);
                    contentTable.addCell(cell);
                }
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return contentTable;
    }

}
