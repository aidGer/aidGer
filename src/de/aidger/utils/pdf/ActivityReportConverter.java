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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
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
     * The name of this report.
     */
    private static String name;

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
        name = _("Activity report");
        makeNewDocument(file);
        if (fileCreated) {
            stampFields();
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            form.setField("CreatorName", Runtime.getInstance()
                .getOption("name"));
            form.setField("AssistantName", assistant);
            form.setField("Date", dateFormat.format(calendar.getTime()));
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
            reader = new PdfReader("template.pdf");

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
     * Creates the table of employments.
     */
    private void createTable() {
        try {
            Font tableTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED),
                10);
            String[] tableTitles = { _("Period"), _("Course"), _("Extent") };
            PdfPTable contentTable = new PdfPTable(1);
            PdfPTable titleTable = new PdfPTable(3);
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
        PdfPTable contentTable = new PdfPTable(3);
        try {
            Font tableContentFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            for (String[] row : tableRows) {
                for (int i = 0; i < row.length; i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(row[i],
                        tableContentFont));
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
