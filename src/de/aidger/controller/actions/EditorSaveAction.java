package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.aidger.model.AbstractModel;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Course;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.aidger.model.validators.PresenceValidator;
import de.aidger.view.UI;
import de.aidger.view.forms.AssistantEditorForm;
import de.aidger.view.forms.CourseEditorForm;
import de.aidger.view.forms.FinancialCategoryEditorForm;
import de.aidger.view.forms.HourlyWageEditorForm;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.ViewerTab;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * This action saves the model and replaces the current tab with the model
 * viewer tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EditorSaveAction extends AbstractAction {

    /**
     * Initializes the action.
     */
    public EditorSaveAction() {
        putValue(Action.NAME, _("Save"));
    }

    /**
     * Prepares the course model stored in the models list by setting the values
     * of the course editor form to this model.
     * 
     * @param models
     *            a list that contains the course model of the editor
     * @param form
     *            the course editor form
     */
    @SuppressWarnings("unchecked")
    private void prepareModels(List<AbstractModel> models, CourseEditorForm form) {
        Course course = (Course) models.get(0);

        course.setDescription(form.getDescription());
        course.setSemester(form.getSemester());
        course.setLecturer(form.getLecturer());
        course.setAdvisor(form.getAdvisor());
        course.setNumberOfGroups(form.getNumberOfGroups());
        course.setTargetAudience(form.getTargetAudience());
        course.setScope(form.getScope());
        course.setGroup(form.getGroup());
        course.setRemark(form.getRemark());
        course.setFinancialCategoryId(form.getFinancialCategoryId());

        try {
            course
                .setUnqualifiedWorkingHours(form.getUnqualifiedWorkingHours());
        } catch (NumberFormatException e) {
            course.addError("unqualifiedWorkingHours", new PresenceValidator(
                course, new String[] {}).getMessage());
        }

        try {
            course.setPart(form.getPart());
        } catch (StringIndexOutOfBoundsException e) {
            course.addError("part", new PresenceValidator(course,
                new String[] {}).getMessage());
        }
    }

    /**
     * Prepares the assistant model stored in the models list by setting the
     * values of the assistant editor form to this model.
     * 
     * @param models
     *            a list that contains the assistant model of the editor
     * @param form
     *            the assistant editor form
     */
    @SuppressWarnings("unchecked")
    private void prepareModels(List<AbstractModel> models,
            AssistantEditorForm form) {
        Assistant assistant = (Assistant) models.get(0);

        assistant.setFirstName(form.getFirstName());
        assistant.setLastName(form.getLastName());
        assistant.setEmail(form.getEmail());
        assistant.setQualification(form.getQualification());
    }

    /**
     * Prepares the financial category model stored in the models list by
     * setting the values of the financial category editor form to this model.
     * 
     * @param models
     *            a list that contains the financial category model of the
     *            editor
     * @param form
     *            the financial category editor form
     */
    @SuppressWarnings("unchecked")
    private void prepareModels(List<AbstractModel> models,
            FinancialCategoryEditorForm form) {
        FinancialCategory fc = (FinancialCategory) models.get(0);

        fc.setName(form.getFCName());

        try {
            fc.setBudgetCosts(form.getBudgetCosts());
        } catch (NumberFormatException e) {
            fc.addError("budgetCosts", new PresenceValidator(fc,
                new String[] {}).getMessage());
        }

        try {
            fc.setFunds(form.getFunds());
        } catch (NumberFormatException e) {
            fc.addError("funds", new PresenceValidator(fc, new String[] {})
                .getMessage());
        }

        try {
            fc.setYear(form.getYear());
        } catch (NumberFormatException e) {
            fc.addError("year", new PresenceValidator(fc, new String[] {})
                .getMessage());
        }
    }

    /**
     * Prepares the hourly wage models by setting the values of the hourly wage
     * editor form to the models.
     * 
     * @param models
     *            a list that contains the hourly wage model of the editor
     * @param form
     *            the hourly wage editor form
     */
    @SuppressWarnings("unchecked")
    private void prepareModels(List<AbstractModel> models,
            HourlyWageEditorForm form) {
        HourlyWage hw = (HourlyWage) models.get(0);

        hw.setQualification(form.getQualification());

        try {
            hw.setWage(new BigDecimal(form.getWage()));
        } catch (NumberFormatException e) {
            hw.addError("wage", new PresenceValidator(hw, new String[] {})
                .getMessage());
        }

        if (form.isEditMode()) {
            hw.setMonth(form.getMonth());
            hw.setYear(form.getYear());
        } else {
            Calendar start = Calendar.getInstance();
            start.setTime(form.getStartDate());

            Calendar finish = Calendar.getInstance();
            finish.setTime(form.getFinishDate());
            finish.add(Calendar.MONTH, 1);

            if (start.after(finish)) {
                hw.addError("end date", _("must be after start date"));
            } else {
                models.clear();

                while (finish.after(start)) {
                    HourlyWage clone = hw.clone();
                    models.add(clone);

                    clone.setMonth((byte) (start.get(Calendar.MONTH) + 1));
                    clone.setYear((short) start.get(Calendar.YEAR));

                    start.add(Calendar.MONTH, 1);
                }
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void actionPerformed(ActionEvent e) {
        EditorTab tab = (EditorTab) UI.getInstance().getCurrentTab();

        List<AbstractModel> models = new Vector<AbstractModel>();
        models.add(tab.getModel());

        switch (tab.getType()) {
        case Course:
            prepareModels(models, (CourseEditorForm) tab.getEditorForm());
            break;
        case Assistant:
            prepareModels(models, (AssistantEditorForm) tab.getEditorForm());
            break;
        case FinancialCategory:
            prepareModels(models, (FinancialCategoryEditorForm) tab
                .getEditorForm());
            break;
        case HourlyWage:
            prepareModels(models, (HourlyWageEditorForm) tab.getEditorForm());
            break;
        }

        for (AbstractModel model : models) {

            try {
                if (!model.save()) {
                    tab.updateHints();

                    return;
                }
            } catch (AdoHiveException e1) {
                if (e1.getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                    UI
                        .displayError(_("Could not save the model because it already exists in the database."));
                } else {
                    UI.displayError(_("Could not save the model to database."));
                }

                break;
            }
        }

        UI.getInstance().replaceCurrentTab(new ViewerTab(tab.getType()));
    }
}