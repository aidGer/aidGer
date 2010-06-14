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
import de.aidger.model.models.Course;
import de.aidger.model.reports.BalanceCourse;
import de.aidger.model.reports.BalanceFilter;
import de.aidger.utils.reports.BalanceHelper;
import de.unistuttgart.iste.se.adohive.model.ICourse;

/**
 * This class converts balance reports to a format for iText and exports it to a
 * .pdf file.
 *
 * @author aidGer Team
 */
public class BalanceReportConverter {

    /**
     * The PDF-document which will be created.
     */
    private Document document = null;

    /**
     * Contains the group tables and their names.
     */
    private Vector balanceReportGroups = null;

    /**
     * The PdfWriter used to write the document.
     */
    private PdfWriter writer = null;

    /**
     * The filters to use for this converter.
     */
    private BalanceFilter filters = null;

    /**
     * The balance helper used to filter the courses for this converter.
     */
    private BalanceHelper balanceHelper = null;

    /**
     * Initializes this BalanceReportConverter with a given path and a name.
     *
     * @param path
     *            The path, at which to save the document.
     * @param name
     *            The desired name of the document.
     */
    public BalanceReportConverter(File file, int index, Object semester,
            BalanceFilter filters) {
        if (document == null) {
            document = new Document(PageSize.A4.rotate());
        }
        if (this.filters == null) {
            this.filters = filters;
        }
        if (balanceHelper == null) {
            balanceHelper = new BalanceHelper();
        }
        file = checkExtension(file);
        makeNewDocument(file);
        writeHeader();
        switch (index) {
        case 1:
            Vector semesters = new BalanceHelper().getSemesters();
            for (int i = 1; i < semesters.size(); i++) {
                balanceReportGroups = new Vector<Vector>();
                createSemester((String) semesters.get(i));
            }
            break;
        case 2:
            balanceReportGroups = new Vector<Vector>();
            createYear((Integer) semester);
            break;
        case 3:
            balanceReportGroups = new Vector<Vector>();
            createSemester("" + semester);
            break;
        }
        document.close();
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
            left.addElement(new Phrase(_("Balance Report"), pageTitleFont));

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
     * Writes a semester table and adds the groups of that semester to it.
     *
     * @param semester
     *            The name of the semester to be added.
     */
    private void createSemester(String semester) {
        try {
            Font semesterTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED),
                13);
            Font semesterNameFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 13);

            PdfPTable finalSemesterTable = new PdfPTable(1);

            PdfPTable semesterTitleTable = new PdfPTable(new float[] { 0.2f,
                    0.8f });

            PdfPCell semesterCell = new PdfPCell(new Phrase(_("Semester"),
                semesterTitleFont));
            semesterCell.setBorder(0);
            semesterTitleTable.addCell(semesterCell);

            semesterCell = new PdfPCell(new Phrase(semester, semesterNameFont));
            semesterCell.setBorder(0);
            semesterTitleTable.addCell(semesterCell);

            semesterCell = new PdfPCell(semesterTitleTable);
            semesterCell.setBorder(0);
            semesterCell.setPaddingTop(7.0f);
            finalSemesterTable.addCell(semesterCell);

            PdfPTable contentTable = new PdfPTable(1);

            /*
             * As long as there are groups for this Balance report, create new
             * group tables.
             */
            List<ICourse> courses = null;
            courses = (new Course()).getAll();
            List<ICourse> filteredCourses = balanceHelper.filterCourses(
                courses, filters);
            for (ICourse course : filteredCourses) {
                if (course.getSemester().equals(semester)) {
                    PdfPTable groupTable = null;
                    if (balanceReportGroups.isEmpty()) {
                        groupTable = createGroup(course);
                    } else {
                        boolean foundGroup = false;
                        for (int i = 0; i <= balanceReportGroups.size() - 1; i++) {
                            if (((Vector) balanceReportGroups.get(i)).get(1)
                                .equals(course.getGroup())) {
                                groupTable = (PdfPTable) ((Vector) balanceReportGroups
                                    .get(i)).get(0);
                                foundGroup = true;
                                break;
                            }
                        }
                        if (!foundGroup) {
                            groupTable = createGroup(course);
                        }
                    }
                    groupTable.addCell(addRow(course));
                    groupTable.setKeepTogether(true);
                }
            }
            for (int i = 0; i <= balanceReportGroups.size() - 1; i++) {
                PdfPCell cell = new PdfPCell(
                    (PdfPTable) ((Vector) balanceReportGroups.get(i)).get(0));
                cell.setBorder(0);
                cell.setPaddingBottom(8.0f);
                contentTable.addCell(cell);
            }
            PdfPCell contentCell = new PdfPCell(contentTable);
            contentCell.setPaddingLeft(10.0f);
            contentCell.setBorder(1);
            finalSemesterTable.addCell(contentCell);
            finalSemesterTable.setKeepTogether(true);
            document.add(finalSemesterTable);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Creates a row with the data of one course and returns it.
     *
     * @param course
     *            The course of which the data shall be written to a row.
     * @return The row as a PdfPCell
     */
    private PdfPCell addRow(ICourse course) {
        PdfPTable groupContentTable = new PdfPTable(new float[] { 0.25f, 0.05f,
                0.15f, 0.15f, 0.1f, 0.1f, 0.1f, 0.1f });
        Font tableContentFont;
        try {
            tableContentFont = new Font(BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            BalanceCourse balanceCourse = BalanceHelper
                .getBalanceCourse(course);
            int i = 0;
            for (Object courseObject : balanceCourse.getCourseObject()) {
                PdfPCell cell = new PdfPCell(new Phrase("" + courseObject,
                    tableContentFont));
                if (i != 0) {
                    cell.setBorder(4);
                } else {
                    cell.setBorder(0);
                    i++;
                }
                groupContentTable.addCell(cell);
            }
            PdfPCell cell = new PdfPCell(groupContentTable);
            cell.setBorder(0);
            return cell;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a new group table with the title of the group. Adds the table and
     * its title to the group table vector.
     *
     * @param course
     *            The course for which a group shall be created.
     * @return The PdfPTable of the group.
     */
    private PdfPTable createGroup(ICourse course) {
        Font tableTitleFont;
        try {
            tableTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED), 9);
            String[] courseTitles = { _("Title"), _("Part"), _("Lecturer"),
                    _("Target Audience"), _("Planned AWS"),
                    _("Basic needed AWS"), _("Budget costs from student fees"),
                    _("Budget costs from resources") };

            Font groupTitleFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED),
                11);
            Font groupNameFont = new Font(BaseFont.createFont(
                BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED), 11);

            PdfPTable groupTable = new PdfPTable(1);
            PdfPTable groupNameTable = new PdfPTable(new float[] { 0.2f, 0.8f });
            PdfPCell groupTitle = new PdfPCell(new Phrase("GRUPPE",
                groupTitleFont));
            groupTitle.setBorder(2);
            groupNameTable.addCell(groupTitle);
            PdfPCell groupName = new PdfPCell(new Phrase(course.getGroup(),
                groupNameFont));
            groupName.setBorder(2);
            groupNameTable.addCell(groupName);
            PdfPCell groupContent = new PdfPCell(groupNameTable);
            groupContent.setBorder(0);
            groupContent.setPaddingTop(3.0f);
            groupContent.setPaddingBottom(2.0f);
            groupTable.addCell(groupContent);
            PdfPTable groupContentTable = new PdfPTable(new float[] { 0.25f,
                    0.05f, 0.15f, 0.15f, 0.1f, 0.1f, 0.1f, 0.1f });
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
                groupContentTable.addCell(cell);
            }

            PdfPCell cell = new PdfPCell(groupContentTable);
            cell.setBorder(0);
            groupTable.addCell(cell);
            groupTable.setKeepTogether(true);

            balanceReportGroups.add(new Vector<Object>());
            int i = balanceReportGroups.size() - 1;
            ((Vector) balanceReportGroups.get(i)).add(groupTable);
            ((Vector) balanceReportGroups.get(i)).add(course.getGroup());

            return groupTable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates the balance reports of the year by calling the functions to
     * create all semesters of the year.
     *
     * @param year
     *            The year to be added.
     */
    private void createYear(int year) {
        // Lose the first two numbers of the year
        int semester = year % 100;
        /*
         * Contains the year in YYYY form, the previous, current and next
         * semester in that order.
         */
        String[] semesters = new String[4];
        semesters[0] = Integer.toString(year);
        switch (semester) {
        /*
         * If the given year is 2001-2008, (year % 100) will give a single
         * number below 9. Therefore, the previous, current and next semester
         * all need a leading 0 added.
         */
        case 0:
            semesters[1] = "WS" + "99" + "00";
            semesters[2] = "SS" + "00";
            semesters[3] = "WS" + "00" + "01";
            break;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
            semesters[1] = "WS0" + (semester - 1) + "0" + semester;
            semesters[2] = "SS0" + semester;
            semesters[3] = "WS0" + semester + "0" + (semester + 1);
            break;
        /*
         * If the given year is 2009, the previous and current semester will
         * both be a single number and therefore need a leading 0 added. The
         * next semester will be 10 and thus needs no adjustments.
         */
        case 9:
            semesters[1] = "WS0" + (semester - 1) + "0" + semester;
            semesters[2] = "SS0" + semester;
            semesters[3] = "WS0" + semester + (semester + 1);
            break;
        /*
         * If the given year is 2010, the current and next semesters will be 10
         * and 11 and therefore don't need a leading 0. The previous semester
         * will be 9 though.
         */
        case 10:
            semesters[1] = "WS0" + (semester - 1) + semester;
            semesters[2] = "SS" + semester;
            semesters[3] = "WS" + semester + (semester + 1);
            break;
        case 99:
            semesters[1] = "WS" + (semester - 1) + semester;
            semesters[2] = "SS" + semester;
            semesters[3] = "WS" + semester + "00";
            break;
        /*
         * In all other relevant cases (11 and higher), the semesters can be
         * used the way (year % 100) returns them.
         */
        default:
            semesters[1] = "WS" + (semester - 1) + semester;
            semesters[2] = "SS" + semester;
            semesters[3] = "WS" + semester + (semester + 1);
            break;
        }
        // Check if the semester has a course and add it if it does.
        for (String currentSemester : semesters) {
            if (courseExists(currentSemester)) {
                createSemester(currentSemester);
            }
        }
    }

    /**
     * Checks if the given semester contains any courses.
     *
     * @param semester
     *            The semester to check
     * @return true if the semester contains one or more courses.
     */
    private boolean courseExists(String semester) {
        List<ICourse> courses = null;
        try {
            courses = (new Course()).getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Check for every course, if it belongs to the given semester.
        for (ICourse course : courses) {
            if (course.getSemester().equals(semester)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Writes the Footer on every page of the document.
     */
    private void writeFooter() {
    }
}
