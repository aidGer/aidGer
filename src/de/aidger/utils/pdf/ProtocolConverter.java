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
import java.util.Vector;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
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
     * Whether the file was created successfully.
     */
    private boolean fileCreated = false;

    /**
     * The name of this report.
     */
    private static String name;

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
        document.setMargins(document.leftMargin(), document.rightMargin(),
            document.topMargin() + 15, document.bottomMargin());
        file = checkExtension(file);
        name = _("Activity Protocol");
        makeNewDocument(file);
        if (fileCreated) {
            writeTable();
            document.close();
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
            HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);
            document.open();
            fileCreated = true;
        } catch (FileNotFoundException e1) {
            UI.displayError(_("File could not be created.") + " "
                    + _("Please close all processes that are using the file."));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This class is used to write the headers and footers of every page.
     * 
     * @author aidGer Team
     */
    static class HeaderFooter extends PdfPageEventHelper {

        /**
         * The template containing the total number of pages.
         */
        PdfTemplate total;

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.itextpdf.text.pdf.PdfPageEventHelper#onOpenDocument(com.itextpdf
         * .text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onOpenDocument(PdfWriter writer, Document document) {
            total = writer.getDirectContent().createTemplate(30, 16);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.itextpdf.text.pdf.PdfPageEventHelper#onStartPage(com.itextpdf
         * .text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(new float[] { 0.8f, 0.2f });
            table.setTotalWidth(writer.getPageSize().getRight()
                    - document.rightMargin() - document.leftMargin());
            try {
                Font pageTitleFont = new Font(BaseFont
                    .createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252,
                        BaseFont.EMBEDDED), 18);
                Font authorNameFont = new Font(BaseFont.createFont(
                    BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 8);

                PdfPCell left = new PdfPCell();
                left.setHorizontalAlignment(Element.ALIGN_LEFT);
                left.setVerticalAlignment(Element.ALIGN_BOTTOM);
                left.setBorder(Rectangle.BOTTOM);
                if (writer.getCurrentPageNumber() == 1) {
                    left.addElement(new Phrase(name, pageTitleFont));
                } else {
                    left.addElement(new Phrase(""));
                }

                PdfPCell right = new PdfPCell(new Phrase(_("Author") + ": "
                        + Runtime.getInstance().getOption("name"),
                    authorNameFont));
                right.setVerticalAlignment(Element.ALIGN_BOTTOM);
                right.setHorizontalAlignment(Element.ALIGN_RIGHT);
                right.setBorder(Rectangle.BOTTOM);

                table.addCell(left);
                table.addCell(right);
                table.writeSelectedRows(0, -1, document.leftMargin(), document
                    .getPageSize().getTop() - 15, writer.getDirectContent());
            } catch (DocumentException e1) {
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.itextpdf.text.pdf.PdfPageEventHelper#onEndPage(com.itextpdf.text
         * .pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable table = new PdfPTable(2);
            try {
                Font pageFont = new Font(BaseFont.createFont(
                    BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 12);
                table.setWidths(new int[] { 48, 2 });
                table.setTotalWidth(writer.getPageSize().getRight()
                        - document.rightMargin() - document.leftMargin());
                table.setLockedWidth(true);
                table.getDefaultCell().setBorder(Rectangle.BOTTOM);
                table.getDefaultCell().setFixedHeight(20);
                table.getDefaultCell().setHorizontalAlignment(
                    Element.ALIGN_RIGHT);
                table.addCell(new Phrase(_("Page") + ": "
                        + writer.getCurrentPageNumber() + _(" of"), pageFont));
                PdfPCell cell = new PdfPCell(Image.getInstance(total));
                cell.setBorder(Rectangle.BOTTOM);
                table.addCell(cell);
                table.writeSelectedRows(0, -1, document.leftMargin(), 50,
                    writer.getDirectContent());
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.itextpdf.text.pdf.PdfPageEventHelper#onCloseDocument(com.itextpdf
         * .text.pdf.PdfWriter, com.itextpdf.text.Document)
         */
        @Override
        public void onCloseDocument(PdfWriter writer, Document document) {
            try {
                Font pageFont = new Font(BaseFont.createFont(
                    BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 12);
                ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
                    new Phrase(String.valueOf(writer.getPageNumber() - 1),
                        pageFont), 2, 2, 0);
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
