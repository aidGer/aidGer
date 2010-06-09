package de.aidger.utils.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

public class BalanceReportConverter implements ReportConverter {
    /*
     * The PDF-document which will be created.
     */
    private Document document = null;

    /*
     * The PdfWriter used to write the document.
     */
    private PdfWriter writer = null;

    /**
     * Initializes this BalanceReportConverter with a given path and a name.
     * 
     * @param path
     *            The path, at which to save the document.
     * @param name
     *            The desired name of the document.
     */
    public BalanceReportConverter(String path, String name) {
        if (document == null) {
            document = new Document(PageSize.A4.rotate());
        }
        makeNewDocument(path, name);
        writeHeader();
        writeTables();
        document.close();
    }

    /**
     * Creates a new document.
     * 
     * @param path
     *            The path at which to create the PDF-document.
     * @param name
     *            The desired name of the PDF-file.
     */
    private void makeNewDocument(String path, String name) {
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(path + name + ".pdf");
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
            left.addElement(new Phrase("Bilanz-bericht", pageTitleFont));

            PdfPCell right = new PdfPCell();
            right.setHorizontalAlignment(Element.ALIGN_RIGHT);
            right.setVerticalAlignment(Element.ALIGN_BOTTOM);
            right.setBorder(0);
            right.addElement(new Phrase("Author:", authorNameFont));

            head.addCell(left);
            head.addCell(right);
        } catch (DocumentException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            document.add(head);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Writes the Balance Tables, which belong to this report.
     */
    private void writeTables() {
        String[] group = { "Bachelor Bio-Informatik", "Bachelor Mechatronik",
                "Bachelor Mechatroniker" };
        String[] courseTitles = { "BEZEICHNUNG", "TEIL", "DOZENT",
                "ZIELPUBLIKUM", "EINGEPLANTE HKS", "HKS GRUNDBEDARF",
                "PLANKOSTEN AUS STUDIENGEBUEHREN",
                "PLANKOSTEN AUS HAUSHALTSMITTEL" };
        String[] courseNames = { "Drittmittelprojekt", "", "nicht relevant",
                "", "0", "", "10000", "", "" };

        try {
            Font groupTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED),
                11);
            Font groupNameFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 11);
            Font tableTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            Font tableContentFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 9);

            PdfPTable contentTable = new PdfPTable(1);

            /*
             * As long as there are groups for this Balance report, create new
             * group tables.
             */
            for (int i = 0; i < 10; i++) {
                PdfPTable groupTable = new PdfPTable(1);
                PdfPTable groupNameTable = new PdfPTable(new float[] { 0.2f,
                        0.8f });
                PdfPCell groupTitle = new PdfPCell(new Phrase("GRUPPE",
                    groupTitleFont));
                groupTitle.setBorder(2);
                groupNameTable.addCell(groupTitle);
                PdfPCell groupName = new PdfPCell(new Phrase(group[1],
                    groupNameFont));
                groupName.setBorder(2);
                groupNameTable.addCell(groupName);
                PdfPCell groupContent = new PdfPCell(groupNameTable);
                groupContent.setBorder(0);
                groupContent.setPaddingTop(10.0f);
                groupContent.setPaddingBottom(2.0f);
                groupTable.addCell(groupContent);
                PdfPTable groupContentTable = new PdfPTable(new float[] {
                        0.25f, 0.05f, 0.15f, 0.15f, 0.1f, 0.1f, 0.1f, 0.1f });
                /*
                 * Create the titles of the table entries.
                 */
                for (int j = 0; j < 8; j++) {
                    PdfPCell cell = new PdfPCell(new Phrase(courseTitles[j],
                        tableTitleFont));
                    if (j != 0) {
                        cell.setBorder(6);
                    } else {
                        cell.setBorder(2);
                    }
                    groupContentTable.addCell(cell);
                }
                /*
                 * Create new table entries, as long as there are new Courses
                 * for the current group.
                 */
                for (int k = 0; k < 5; k++) {
                    for (int j = 0; j < 8; j++) {
                        PdfPCell cell;
                        if (courseNames[j] != "") {
                            cell = new PdfPCell(new Phrase(courseNames[j],
                                tableContentFont));
                        } else {
                            cell = new PdfPCell(new Phrase("-",
                                tableContentFont));
                        }
                        if (j != 0) {
                            cell.setBorder(4);
                        } else {
                            cell.setBorder(0);
                        }
                        groupContentTable.addCell(cell);
                    }
                }
                /*
                 * Add the entry of the current group to the table and make sure
                 * it doesn't get torn apart when a new page is added.
                 */
                PdfPCell cell = new PdfPCell(groupContentTable);
                cell.setBorder(0);
                groupTable.addCell(cell);
                groupTable.setKeepTogether(true);
                cell = new PdfPCell(groupTable);
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
     * Writes the Footer on every page of the document.
     */
    private void writeFooter() {
    }
}
