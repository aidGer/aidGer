package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.aidger.controller.actions.MasterDataActivitiesAction;
import de.aidger.controller.actions.MasterDataAddAction;
import de.aidger.controller.actions.MasterDataDeleteAction;
import de.aidger.controller.actions.MasterDataEditAction;
import de.aidger.controller.actions.MasterDataViewAction;
import de.aidger.model.Runtime;
import de.aidger.model.models.Course;
import de.aidger.view.models.AssistantTableModel;
import de.aidger.view.models.CourseTableModel;
import de.aidger.view.models.FinancialCategoryTableModel;
import de.aidger.view.models.HourlyWageTableModel;
import de.aidger.view.models.MasterDataTableModel;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * A tab which will be used to display the master data.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class MasterDataViewerTab extends Tab {
    /**
     * The type of the master data that will be viewed.
     */
    public enum MasterDataType {
        Course, Assistant, FinancialCategory, HourlyWage
    }

    /**
     * The header size of the shown table.
     */
    private final int[][] tableHeaderSize;

    /**
     * The hidden columns for restoring configuration state.
     */
    private final List<String> hiddenColumns = new ArrayList<String>();

    /**
     * The type of the master data.
     */
    private final MasterDataType type;

    /**
     * The table model.
     */
    private MasterDataTableModel tableModel;

    /**
     * Constructs the master data viewer tab.
     * 
     * @param type
     *            the type of the master data
     */
    @SuppressWarnings("unchecked")
    public MasterDataViewerTab(MasterDataType type) {
        this.type = type;
        initComponents();

        Course c = new Course();
        c.setDescription("Description");
        c.setFinancialCategoryId(1);
        c.setGroup("2");
        c.setLecturer("Test Tester");
        c.setNumberOfGroups(3);
        c.setPart('a');
        c.setRemark("Remark");
        c.setScope("Sniper Scope");
        c.setSemester("SS 09");
        c.setTargetAudience("Testers");
        c.setUnqualifiedWorkingHours(100);

        try {
            c.save();
        } catch (AdoHiveException e) {
            e.printStackTrace();
        }

        // use different table model for each master data type
        switch (type) {
        case Course:
            tableModel = new CourseTableModel();
            break;
        case Assistant:
            tableModel = new AssistantTableModel();
            break;
        case FinancialCategory:
            tableModel = new FinancialCategoryTableModel();
            btnActivities.setVisible(false);
            break;
        case HourlyWage:
            tableModel = new HourlyWageTableModel();
            btnActivities.setVisible(false);
            break;
        }

        table.setModel(tableModel);

        // table.setComponentPopupMenu(new JPopupMenu());
        table.setAutoCreateRowSorter(true);
        table.setDoubleBuffered(true);
        table.setFocusCycleRoot(true);

        // initializes button actions
        btnView.setAction(new MasterDataViewAction(this));
        btnEdit.setAction(new MasterDataEditAction(this));
        btnAdd.setAction(new MasterDataAddAction(this));
        btnDelete.setAction(new MasterDataDeleteAction(this));
        btnActivities.setAction(new MasterDataActivitiesAction(this));

        tableHeaderSize = new int[table.getColumnCount()][3];

        // activate column filtering
        String[] hiddenColumns = Runtime.getInstance().getOptionArray(
                "hiddenColumns" + type);

        if (hiddenColumns == null) {
            switch (type) {
            case Course:
                hiddenColumns = new String[] { "6", "7", "8", "9", "10" };
                break;
            default:
                hiddenColumns = new String[] {};
                break;
            }

            Runtime.getInstance().setOptionArray("hiddenColumns" + type,
                    hiddenColumns);
        }

        for (int i = 0; i < hiddenColumns.length; ++i) {
            if (!hiddenColumns[i].isEmpty()) {
                toggleColumnVisibility(Integer.valueOf(hiddenColumns[i]));
            }
        }

        JPopupMenu headerMenu = new JPopupMenu();

        Enumeration en = table.getTableHeader().getColumnModel().getColumns();

        while (en.hasMoreElements()) {
            TableColumn column = (TableColumn) en.nextElement();

            JCheckBoxMenuItem mi = new JCheckBoxMenuItem(new AbstractAction(
                    column.getHeaderValue().toString()) {
                public void actionPerformed(ActionEvent evt) {
                    String cmd = evt.getActionCommand();

                    int index = table.getTableHeader().getColumnModel()
                            .getColumnIndex(cmd);

                    toggleColumnVisibility(index);
                }
            });

            if (column.getPreferredWidth() != 0) {
                mi.setSelected(true);
            } else {
                mi.setSelected(false);
            }

            headerMenu.add(mi);
        }

        table.getTableHeader().addMouseListener(new PopupListener(headerMenu));
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getTabName()
     */
    @Override
    public String getTabName() {
        switch (type) {
        case Course:
            return _("Master Data Courses");
        case Assistant:
            return _("Master Data Assistants");
        case FinancialCategory:
            return _("Master Data Financial Categories");
        case HourlyWage:
            return _("Master Data Hourly Wages");
        default:
            return _("Master Data");
        }
    }

    /**
     * Toggles the visibility of the given column.
     * 
     * @param index
     *            the column index whose visibility will be toggled
     */
    private void toggleColumnVisibility(int index) {
        TableColumn column = table.getTableHeader().getColumnModel().getColumn(
                index);

        if (column.getPreferredWidth() != 0) {
            tableHeaderSize[index][0] = column.getPreferredWidth();
            tableHeaderSize[index][1] = column.getMinWidth();
            tableHeaderSize[index][2] = column.getMaxWidth();

            column.setMinWidth(0);
            column.setMaxWidth(0);
            column.setPreferredWidth(0);

            hiddenColumns.add(String.valueOf(index));
        } else {
            column.setMinWidth(tableHeaderSize[index][1]);
            column.setMaxWidth(tableHeaderSize[index][2]);
            column.setPreferredWidth(tableHeaderSize[index][0]);

            column.sizeWidthToFit();

            hiddenColumns.remove(String.valueOf(index));
        }

        Runtime.getInstance().setOptionArray("hiddenColumns" + type,
                hiddenColumns.toArray(new String[0]));
    }

    /**
     * Returns the table model.
     * 
     * @return the table model
     */
    public MasterDataTableModel getTableModel() {
        return tableModel;
    }

    /**
     * Returns the shown table.
     * 
     * @return the table that is shown
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Returns the type of the master data that is shown.
     * 
     * @return the type of the shown master data
     */
    public MasterDataType getType() {
        return type;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        btnView = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnActivities = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        searchField = new javax.swing.JTextField();

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        btnView.setText(_("View"));
        btnView.setFocusable(false);
        btnView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnView.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnView);

        btnEdit.setText(_("Edit"));
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnEdit);

        btnAdd.setText(_("Add"));
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnAdd);

        btnDelete.setText(_("Delete"));
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnDelete);

        btnActivities.setText(_("Activites"));
        btnActivities.setFocusable(false);
        btnActivities
                .setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnActivities
                .setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(btnActivities);

        scrollPane.setViewportView(table);

        searchField.setText(_("Search"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout
                .setHorizontalGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addGroup(
                                                layout
                                                        .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(
                                                                layout
                                                                        .createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(
                                                                                layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(
                                                                                                searchField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                150,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(
                                                                                                scrollPane,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                530,
                                                                                                Short.MAX_VALUE)))
                                                        .addComponent(
                                                                toolBar,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                542,
                                                                Short.MAX_VALUE))
                                        .addContainerGap()));
        layout
                .setVerticalGroup(layout
                        .createParallelGroup(
                                javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(
                                layout
                                        .createSequentialGroup()
                                        .addComponent(
                                                toolBar,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                25,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                searchField,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(
                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(
                                                scrollPane,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                256, Short.MAX_VALUE)
                                        .addContainerGap()));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivities;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextField searchField;
    private javax.swing.JTable table;
    private javax.swing.JToolBar toolBar;

    // End of variables declaration//GEN-END:variables

    /**
     * A mouse listener that shows a given popup menu.
     * 
     * @author aidGer Team
     */
    class PopupListener extends MouseAdapter {
        JPopupMenu popupMenu;

        /**
         * Constructs the popup listener.
         * 
         * @param popup
         *            the popup menu that will be shown
         */
        public PopupListener(JPopupMenu popup) {
            this.popupMenu = popup;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
         */
        @Override
        public void mousePressed(MouseEvent me) {
            showPopup(me);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
         */
        @Override
        public void mouseReleased(MouseEvent me) {
            showPopup(me);
        }

        /**
         * Shows the popup menu.
         * 
         * @param me
         *            the mouse event
         */
        private void showPopup(MouseEvent me) {
            if (me.isPopupTrigger()) {
                popupMenu.show(me.getComponent(), me.getX(), me.getY());
            }
        }
    }
}
