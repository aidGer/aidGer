/**
 * 
 */
package de.aidger.utils.pdf;

import static de.aidger.utils.Translation._;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import de.aidger.model.Runtime;
import de.aidger.model.reports.ProtocolCreator;
import de.aidger.view.UI;

/**
 * This class converts protocols to a format for iText and exports it to a .pdf
 * file.
 * 
 * @author aidGer Team
 */
public class ProtocolConverter {

    /**
     * The PDF-document which will be created.
     */
    private Document document = null;

    /**
     * The PdfWriter used to write the document.
     */
    private PdfWriter writer = null;

    /**
     * The number of days before the current one to display activities of.
     */
    private int numberOfDays = 0;

    /**
     * Initializes a new ProtocolConverter and creates the .pdf file.
     * 
     * @param file
     *            The filepath of the file.
     * @param numberOfDays
     *            The number of days to display activities of.
     */
    public ProtocolConverter(File file, int numberOfDays) {
        this.numberOfDays = numberOfDays;
        document = new Document(PageSize.A4.rotate());
        file = checkExtension(file);
        makeNewDocument(file);
        writeHeader();
        writeTable();
        document.close();
        if (Runtime.getInstance().getOption("auto-open").equals("true")) {
            try {
                java.lang.Runtime.getRuntime().exec(
                    new String[] {
                            Runtime.getInstance().getOption("pdf-viewer"),
                            file.getAbsolutePath() });
            } catch (IOException e) {
                UI.displayError(_("Pdf viewer could not be found!"));
            }
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
     * @param path
     *            The path at which to create the PDF-document.
     * @param name
     *            The desired name of the PDF-file.
     */
    private void makeNewDocument(File file) {
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file.getPath());
            writer = PdfWriter.getInstance(document, outStream);
            document.open();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Writes the Header of the document on the first page only.
     */
    private void writeHeader() {
        PdfPTable head = new PdfPTable(new float[] { 0.8f, 0.2f });
        try {
            Font pageTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED),
                18);
            Font authorNameFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 8);

            PdfPCell left = new PdfPCell();
            left.setHorizontalAlignment(Element.ALIGN_LEFT);
            left.setVerticalAlignment(Element.ALIGN_BOTTOM);
            left.setBorder(0);
            left.addElement(new Phrase(_("Activity Protocol"), pageTitleFont));

            PdfPCell right = new PdfPCell();
            right.setHorizontalAlignment(Element.ALIGN_RIGHT);
            right.setVerticalAlignment(Element.ALIGN_BOTTOM);
            right.setBorder(0);
            right.addElement(new Phrase(_("Author") + ": "
                    + Runtime.getInstance().getOption("name"), authorNameFont));

            head.addCell(left);
            head.addCell(right);
            document.add(head);
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * Writes the Table of activities.
     */
    private void writeTable() {
        try {
            Font tableTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            String[] courseTitles = { _("Affected assistant"),
                    _("Affected course"), _("Type"), _("Date"), _("Content"),
                    _("Sender"), _("Processor"), _("Remark") };
            PdfPTable contentTable = new PdfPTable(1);
            PdfPTable titleTable = new PdfPTable(8);
            /*
             * Create the titles of the table entries.
             */
            for (int i = 0; i < courseTitles.length; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(courseTitles[i],
                    tableTitleFont));
                if (i != 0) {
                    cell.setBorder(6);
                } else {
                    cell.setBorder(2);
                }
                titleTable.addCell(cell);
            }
            PdfPCell cell = new PdfPCell(titleTable);
            cell.setPaddingTop(10.0f);
            cell.setBorder(0);
            contentTable.addCell(cell);
            document.add(contentTable);
            addActivities();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Adds activities to the activity table.
     */
    private void addActivities() {

        try {
            PdfPTable contentTable = new PdfPTable(1);
            Vector activities = new ProtocolCreator()
                .createProtocol(numberOfDays);
            for (Object activity : activities) {
                PdfPCell cell = new PdfPCell(addRow((Object[]) activity));
                cell.setBorder(0);
                contentTable.addCell(cell);
            }
            document.add(contentTable);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Adds one activity to the activity table.
     * 
     * @param activity
     *            The activity to add.
     * @return The PdfPTable of the row.
     * @throws DocumentException
     * @throws IOException
     */
    private PdfPTable addRow(Object[] activity) throws DocumentException,
            IOException {
        Font tableContentFont = new Font(BaseFont.createFont(
            BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
        PdfPTable contentTable = new PdfPTable(8);
        for (int i = 0; i < 8; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(activity[i].toString(),
                tableContentFont));
            if (i != 0) {
                cell.setBorder(4);
            } else {
                cell.setBorder(0);
            }
            contentTable.addCell(cell);
        }
        return contentTable;
    }
}
