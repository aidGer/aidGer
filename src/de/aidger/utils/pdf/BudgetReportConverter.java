/**
 * 
 */
package de.aidger.utils.pdf;

import static de.aidger.utils.Translation._;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
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
import de.aidger.model.budgets.BudgetCreator;
import de.aidger.model.budgets.BudgetFilter;
import de.aidger.model.budgets.CourseBudget;
import de.aidger.model.models.Course;
import de.aidger.view.UI;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * @author aidGer Team
 */
public class BudgetReportConverter {

    /**
     * The PDF-document which will be created.
     */
    private Document document = null;

    /**
     * The PdfWriter used to write the document.
     */
    private PdfWriter writer = null;

    /**
     * The filters used for this budget report.
     */
    private BudgetFilter filters = null;

    /**
     * Initializes this BalanceReportConverter with a given path and a name.
     * 
     * @param path
     *            The path, at which to save the document.
     * @param name
     *            The desired name of the document.
     */
    public BudgetReportConverter(File file, BudgetFilter filters) {
        document = new Document(PageSize.A4);
        this.filters = filters;
        file = checkExtension(file);
        makeNewDocument(file);
        writeHeader();
        createCourses();
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
            left.addElement(new Phrase(_("Budget Report"), pageTitleFont));

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
     * Creates all the course budgets and writes them into a table.
     */
    private void createCourses() {
        try {
            PdfPTable contentTable = new PdfPTable(1);
            PdfPCell contentCell = new PdfPCell(createTableHeader());

            contentCell.setBorder(1);
            contentCell.setPaddingTop(10);
            contentTable.addCell(contentCell);

            BudgetCreator budgetCreator = new BudgetCreator();
            List<ICourse> courses = (new Course()).getAll();
            /*
             * Get course budgets for all courses along with the filter
             * criteria.
             */
            for (ICourse course : courses) {
                budgetCreator.addCourseBudget(new Course(course), filters);
            }
            Vector<CourseBudget> courseBudgets = budgetCreator
                .getCourseBudgets();
            /*
             * Add a new row for every course that passed the filters.
             */
            for (CourseBudget courseBudget : courseBudgets) {
                contentCell = new PdfPCell(addRow(budgetCreator
                    .getObjectArray(courseBudget)));
                contentCell.setBorder(0);
                contentTable.addCell(contentCell);
            }
            document.add(contentTable);
        } catch (AdoHiveException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Creates the titles of the table.
     * 
     * @return The table with the titles.
     */
    private PdfPTable createTableHeader() {
        try {
            Font tableTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            PdfPTable tableHeader = new PdfPTable(new float[] { 0.3f, 0.15f,
                    0.15f, 0.15f, 0.15f, 0.10f });
            String[] titles = { _("Course"), _("Semester"), _("Lecturer"),
                    _("Booked budgets"), _("Available budgets"),
                    _("Total Budgets") };
            /*
             * Add the title of every column.
             */
            for (int i = 0; i < titles.length; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(titles[i],
                    tableTitleFont));
                tableHeader.addCell(cell);
            }
            PdfPTable tableHeaderTable = new PdfPTable(1);
            PdfPCell cell = new PdfPCell(tableHeader);
            cell.setPaddingBottom(1);
            cell.setBorder(0);
            tableHeaderTable.addCell(cell);
            return tableHeaderTable;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Adds a row, containing a course budget, to the table.
     * 
     * @param objectArray
     *            The course budget.
     * @return The table containing the row.
     */
    private PdfPTable addRow(Object[] objectArray) {
        try {
            Font tableContentFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            PdfPTable tableContent = new PdfPTable(new float[] { 0.3f, 0.15f,
                    0.15f, 0.15f, 0.15f, 0.10f });
            for (int i = 0; i < objectArray.length; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(objectArray[i]
                    .toString(), tableContentFont));
                tableContent.addCell(cell);
            }
            return tableContent;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
