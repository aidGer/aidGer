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

package de.aidger.controller.actions;

import static de.aidger.utils.Translation._;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import de.aidger.model.AbstractModel;
import de.aidger.model.Runtime;
import de.aidger.model.inspectors.CostUnitBudgetLimitInspector;
import de.aidger.model.inspectors.CourseBudgetLimitInspector;
import de.aidger.model.inspectors.EmploymentLimitInspector;
import de.aidger.model.inspectors.IdenticalAssistantInspector;
import de.aidger.model.inspectors.Inspector;
import de.aidger.model.inspectors.OverlapContractInspector;
import de.aidger.model.inspectors.WorkingHourLimitInspector;
import de.aidger.model.models.Activity;
import de.aidger.model.models.Assistant;
import de.aidger.model.models.Contract;
import de.aidger.model.models.CostUnit;
import de.aidger.model.models.Course;
import de.aidger.model.models.Employment;
import de.aidger.model.models.FinancialCategory;
import de.aidger.model.models.HourlyWage;
import de.aidger.view.UI;
import de.aidger.view.forms.ActivityEditorForm;
import de.aidger.view.forms.AssistantEditorForm;
import de.aidger.view.forms.ContractEditorForm;
import de.aidger.view.forms.CostUnitEditorForm;
import de.aidger.view.forms.CourseEditorForm;
import de.aidger.view.forms.EmploymentEditorForm;
import de.aidger.view.forms.FinancialCategoryEditorForm;
import de.aidger.view.forms.HourlyWageEditorForm;
import de.aidger.view.models.TableModel;
import de.aidger.view.models.UIAssistant;
import de.aidger.view.tabs.DetailViewerTab;
import de.aidger.view.tabs.EditorTab;
import de.aidger.view.tabs.Tab;
import de.aidger.view.tabs.ViewerTab;
import de.aidger.view.utils.InvalidLengthException;
import siena.SienaException;

/**
 * This action saves the model and replaces the current tab with the model
 * viewer tab.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EditorSaveAction extends AbstractAction {

    /**
     * Inspectors that check models before they are saved.
     */
    private final List<Inspector> inspectors = new ArrayList<Inspector>();

    /**
     * Initializes the action.
     */
    public EditorSaveAction() {
        putValue(Action.NAME, _("Save"));
    }

    /**
     * Prepares the course model stored in the models list by setting the values
     * of the course editor form to this model. Returns the model from the
     * database before it is edited. If a database error occurs the model before
     * it was edited is returned.
     * 
     * @param models
     *            a list that contains the course model of the editor
     * @param form
     *            the course editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            CourseEditorForm form) {
        Course course = (Course) models.get(0);

        Course courseBeforeEdit = course.clone();

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
        } catch (ParseException e) {
            course.setUnqualifiedWorkingHours(null);
        }

        try {
            course.setPart(form.getPart());
        } catch (StringIndexOutOfBoundsException e) {
        }

        try {
            Course c = course.getById(course.getId());

            return c == null ? courseBeforeEdit : new Course(c);
        } catch (SienaException e) {
            return courseBeforeEdit;
        }
    }

    /**
     * Prepares the assistant model stored in the models list by setting the
     * values of the assistant editor form to this model. Returns the model from
     * the database before it is edited. If a database error occurs the model
     * before it was edited is returned.
     * 
     * @param models
     *            a list that contains the assistant model of the editor
     * @param form
     *            the assistant editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            AssistantEditorForm form) {
        Assistant assistant = (Assistant) models.get(0);

        Assistant assistantBeforeEdit = assistant.clone();

        assistant.setFirstName(form.getFirstName());
        assistant.setLastName(form.getLastName());
        assistant.setEmail(form.getEmail());
        assistant.setQualification(form.getQualification());

        try {
            Assistant a = assistant.getById(assistant.getId());

            return a == null ? assistantBeforeEdit : new Assistant(a);
        } catch (SienaException e) {
            return assistantBeforeEdit;
        }
    }

    /**
     * Prepares the financial category model stored in the models list by
     * setting the values of the financial category editor form to this model.
     * Returns the model from the database before it is edited. If a database
     * error occurs the model before it was edited is returned.
     * 
     * @param models
     *            a list that contains the financial category model of the
     *            editor
     * @param form
     *            the financial category editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            FinancialCategoryEditorForm form) {
        FinancialCategory fc = (FinancialCategory) models.get(0);

        FinancialCategory fcBeforeEdit = fc.clone();

        fc.setName(form.getFCName());

        form.sortCostUnits();

        try {
            fc.setCostUnits(form.getCostUnits());
        } catch (InvalidLengthException e) {
            fc.addError("costUnits", _("Cost unit"), _("has to have a length of 8"));
        } catch (NumberFormatException e) {
        	fc.setCostUnits(null);
        }

        try {
            fc.setBudgetCosts(form.getBudgetCosts());
        } catch (NumberFormatException e) {
            fc.setBudgetCosts(null);
        }

        try {
            fc.setYear(form.getYear());
        } catch (NumberFormatException e) {
            fc.setYear(null);
        }

        try {
            // fc.getByKeys expects only the ID as key
            FinancialCategory f = fc.getByKey(fc.getId());

            return f == null ? fcBeforeEdit : new FinancialCategory(f);
        } catch (SienaException e) {
            return fcBeforeEdit;
        }
    }

    /**
     * Prepares the hourly wage models by setting the values of the hourly wage
     * editor form to the models. Returns the model from the database before it
     * is edited. If a database error occurs the model before it was edited is
     * returned.
     * 
     * @param models
     *            a list that contains the hourly wage model of the editor
     * @param form
     *            the hourly wage editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            HourlyWageEditorForm form) {
        HourlyWage hw = (HourlyWage) models.get(0);

        HourlyWage hwBeforeEdit = hw.clone();

        hw.setQualification(form.getQualification());

        try {
            hw.setWage(round(form.getWage(), 2));
        } catch (ParseException e) {
            hw.setWage(null);
        }

        if (form.isEditMode()) {
            hw.setMonth(form.getMonth());
            hw.setYear(form.getYear());
        } else {
            Calendar start = Calendar.getInstance();
            start.setTime(form.getStartDate());

            Calendar finish = Calendar.getInstance();
            finish.setTime(form.getFinishDate());

            if (start.after(finish)) {
                hw.addError("end date", _("End date"),
                    _("must be after start date"));
            } else {
                models.clear();

                finish.add(Calendar.MONTH, 1);

                while (finish.after(start)) {
                    HourlyWage clone = hw.clone();

                    // add also previous errors to clone
                    for (String error : hw.getErrors()) {
                        clone.addError(error);
                    }

                    models.add(clone);

                    clone.setMonth((byte) (start.get(Calendar.MONTH) + 1));
                    clone.setYear((short) start.get(Calendar.YEAR));

                    start.add(Calendar.MONTH, 1);
                }
            }
        }

        try {
            //TODO: Rewrite
            HourlyWage h = null;//hwBeforeEdit.getByKeys(hwBeforeEdit
                //.getQualification(), hwBeforeEdit.getMonth(), hwBeforeEdit
                //.getYear());

            return h == null ? hwBeforeEdit : new HourlyWage(h);
        } catch (SienaException e) {
            return hwBeforeEdit;
        }
    }

    /**
     * Prepares the cost unit model by setting the values of the cost unit
     * editor form to this model.
     * 
     * @param models
     *            a list that contains the course model of the editor
     * @param form
     *            the course editor form
     * @return the edited model
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            CostUnitEditorForm form) {
        CostUnit costUnit = (CostUnit) models.get(0);

        CostUnit costUnitBeforeEdit = costUnit.clone();

        costUnit.setCostUnit(form.getCostUnit());
        costUnit.setFunds(form.getFunds());
        costUnit.setTokenDB(form.getTokenDB());

        return costUnitBeforeEdit;
    }

    /**
     * Prepares the employment models by setting the values of the employment
     * editor form to this model. Returns the model from the database before it
     * is edited. If a database error occurs the model before it was edited is
     * returned.
     * 
     * @param models
     *            a list that contains the employment model of the editor
     * @param form
     *            the employment editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            EmploymentEditorForm form) {
        Employment employment = (Employment) models.get(0);

        Employment employmentBeforeEdit = employment.clone();

        Long assistantId = null, courseId = null;

        if (form.getAssistant() != null) {
            assistantId = form.getAssistant().getId();
        }

        if (form.getCourse() != null) {
            courseId = form.getCourse().getId();
        }

        employment.setAssistantId(assistantId);
        employment.setCourseId(courseId);
        employment.setContractId(form.getContractId());
        employment.setFunds(form.getFunds());
        employment.setCostUnit(form.getCostUnit());
        employment.setQualification(form.getQualification());
        employment.setRemark(form.getRemark());

        List<Date> dates = form.getDates();
        List<Double> hcs = new ArrayList<Double>();

        try {
            hcs = form.getHourCounts();

            models.clear();

            for (int i = 0; i < dates.size(); ++i) {
                Employment clone = employment.clone();

                for (String error : employment.getErrors()) {
                    clone.addError(error);
                }

                models.add(clone);

                Calendar cal = Calendar.getInstance();
                cal.setTime(dates.get(i));

                clone.setMonth((byte) (cal.get(Calendar.MONTH) + 1));
                clone.setYear((short) cal.get(Calendar.YEAR));
                clone.setHourCount(hcs.get(i));
            }
        } catch (ParseException e) {
            employment.setHourCount(null);
        }

        try {
            Employment e = employment.getById(employment.getId());

            return e == null ? employmentBeforeEdit : new Employment(e);
        } catch (SienaException e) {
            return employmentBeforeEdit;
        }
    }

    /**
     * Prepares the contract model by setting the values of the contract editor
     * form to this model. Returns the model from the database before it is
     * edited. If a database error occurs the model before it was edited is
     * returned.
     * 
     * @param models
     *            a list that contains the course model of the editor
     * @param form
     *            the course editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            ContractEditorForm form) {
        Contract contract = (Contract) models.get(0);

        Contract contractBeforeEdit = contract.clone();

        Long assistantId = null;
        if (form.getAssistant() != null) {
            assistantId = form.getAssistant().getId();
        }

        contract.setAssistantId(assistantId);
        contract.setCompletionDate(form.getCompletionDate());
        contract.setConfirmationDate(form.getConfirmationDate());
        contract.setStartDate(form.getStartDate());
        contract.setEndDate(form.getEndDate());
        contract.setType(form.getType());
        contract.setDelegation(form.isDelegation());

        try {
            Contract c = contract.getById(contract.getId());

            return c == null ? contractBeforeEdit : new Contract(c);
        } catch (SienaException e) {
            return contractBeforeEdit;
        }
    }

    /**
     * Prepares the activity models by setting the values of the activity editor
     * form to this model. Returns the model from the database before it is
     * edited. If a database error occurs the model before it was edited is
     * returned.
     * 
     * @param models
     *            a list that contains the activity model of the editor
     * @param form
     *            the activity editor form
     * @return the model from database before it is edited
     */
    @SuppressWarnings("unchecked")
    private AbstractModel prepareModels(List<AbstractModel> models,
            ActivityEditorForm form) {
        Activity activity = (Activity) models.get(0);

        Activity activityBeforeEdit = activity.clone();

        activity.setDate(form.getDate());
        activity.setProcessor(form.getProcessor());
        activity.setType(form.getType());
        activity.setDocumentType(form.getDocumentType());
        activity.setContent(form.getContent());
        activity.setRemark(form.getRemark());

        List<Course> courses = form.getCourses();
        List<Assistant> assistants = form.getAssistants();

        models.clear();

        for (int i = 0; i < courses.size(); ++i) {
            for (int j = 0; j < assistants.size(); ++j) {
                Activity clone = activity.clone();

                for (String error : activity.getErrors()) {
                    clone.addError(error);
                }

                models.add(clone);

                clone.setCourseId(courses.get(i).getId());
                clone.setAssistantId(assistants.get(j).getId());

                if (form.isInitiatorReferenced()) {
                    clone.setSender(new UIAssistant(assistants.get(j))
                        .toString());
                } else {
                    clone.setSender(form.getInitiator());
                }
            }
        }

        try {
            Activity a = activity.getById(activity.getId());

            return a == null ? activityBeforeEdit : new Activity(a);
        } catch (SienaException e) {
            return activityBeforeEdit;
        }
    }

    /**
     * Returns the rounded double.
     * 
     * @param d
     *            the double value
     * @param scale
     *            the scale
     * @return the rounded double value
     */
    public static Double round(double d, int scale) {
        return (new BigDecimal(d).setScale(scale, BigDecimal.ROUND_HALF_EVEN)).doubleValue();
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

        ViewerTab viewerTab = new ViewerTab(tab.getType());
        TableModel tableModel = viewerTab.getTableModel();

        // if something went wrong just the clone model is affected
        AbstractModel clone = (AbstractModel) tab.getModel().clone();

        List<AbstractModel> models = new ArrayList<AbstractModel>();
        models.add(clone);

        AbstractModel modelBeforeEdit = tab.getModel();

        // preparation of the models (type specific)
        switch (tab.getType()) {
        case Course:
            modelBeforeEdit = prepareModels(models, (CourseEditorForm) tab
                .getEditorForm());
            break;
        case Assistant:
            modelBeforeEdit = prepareModels(models, (AssistantEditorForm) tab
                .getEditorForm());
            break;
        case FinancialCategory:
            modelBeforeEdit = prepareModels(models,
                (FinancialCategoryEditorForm) tab.getEditorForm());
            break;
        case HourlyWage:
            modelBeforeEdit = prepareModels(models, (HourlyWageEditorForm) tab
                .getEditorForm());
            break;
        case CostUnit:
            modelBeforeEdit = prepareModels(models, (CostUnitEditorForm) tab
                .getEditorForm());
            break;
        case Employment:
            modelBeforeEdit = prepareModels(models, (EmploymentEditorForm) tab
                .getEditorForm());
            break;
        case Contract:
            modelBeforeEdit = prepareModels(models, (ContractEditorForm) tab
                .getEditorForm());
            break;
        case Activity:
            modelBeforeEdit = prepareModels(models, (ActivityEditorForm) tab
                .getEditorForm());
            break;
        }

        // check model validation first

        for (AbstractModel model : models) {
            if (!model.validateModel()) {
                tab.updateHints(model);

                return;
            }
        }

        // inspector checks

        tab.clearHints();
        inspectors.clear();

        switch (tab.getType()) {
        case Employment:
            EmploymentEditorForm editorForm = (EmploymentEditorForm) tab
                .getEditorForm();

            inspectors.add(new EmploymentLimitInspector(editorForm
                .getAssistant()));
            inspectors.add(new WorkingHourLimitInspector(editorForm
                .getAssistant(), editorForm.getDates()));

            inspectors.add(new CourseBudgetLimitInspector(editorForm
                .getCourse()));
            inspectors.add(new CostUnitBudgetLimitInspector(editorForm
                .getCourse(), editorForm.getCostUnit()));

            break;
        case Assistant:
            inspectors.add(new IdenticalAssistantInspector(new UIAssistant(
                (Assistant) models.get(0))));

            break;
        case Contract:
            inspectors.add(new OverlapContractInspector((Contract) models
                .get(0)));

            break;
        }

        if (!inspectors.isEmpty()) {
            try {
                List<String> messages = new ArrayList<String>();

                if (Inspector.isUpdatedDBRequired(inspectors)) {
                    // save models temporarly
                    for (AbstractModel model : models) {
                        model.save();
                    }
                }

                // perform the inspector checks
                for (Inspector inspector : inspectors) {
                    inspector.check();

                    if (inspector.isFail()) {
                        messages.add(inspector.getResult());
                    }
                }

                if (Inspector.isUpdatedDBRequired(inspectors)) {
                    // reset the changes in database
                    if (tab.isEditMode()) {
                        modelBeforeEdit.save();
                    } else {
                        for (AbstractModel model : models) {
                            model.remove();
                        }
                    }
                }

                // has any check failed?
                if (!messages.isEmpty()) {
                    String message = "";

                    if (messages.size() == 1) {
                        message = messages.get(0) + "\n\n";
                    } else {
                        for (String msg : messages) {
                            message += "\u2022 " + msg + "\n\n";
                        }
                    }

                    message += _("Would you like to continue anyway?");

                    if (JOptionPane.showConfirmDialog(null, message,
                        _("Confirmation"), JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
            } catch (SienaException e2) {
                // continue as if no checks were performed
            }
        }

        // finally save all prepared models

        for (AbstractModel model : models) {
            
            boolean isNew = !model.isInDatabase();
            
            if(!isNew) {    
                UI.getInstance().addObserversTo(model, tab.getType());
            }

            try {
                model.save();
                
                if(isNew) {
                    UI.getInstance().addObserversTo(model, tab.getType());
                }

                UI.getInstance().setStatusMessage(
                    MessageFormat.format(
                        _("The entity {0} was saved successfully."),
                        new Object[] { tab.getType().getDisplayName() }));
            } catch (SienaException e1) {
                if (e1.getCause().getClass() == SQLIntegrityConstraintViolationException.class) {
                    UI
                        .displayError(MessageFormat
                            .format(
                                _("Could not save the entity {0} because it already exists in the database."),
                                new Object[] { tab.getType().getDisplayName() }));
                    System.out.println(e1.getMessage());
                } else {
                    UI.displayError(MessageFormat.format(
                        _("Could not save the entity {0} to database: {1}"),
                        new Object[] { tab.getType().getDisplayName(),
                                e1.getMessage() }));
                }

                break;
            }
        }

        Tab p = tab.getPredecessor();

        if (p instanceof DetailViewerTab) {
            p = viewerTab;
        }

        UI.getInstance().replaceCurrentTab(p);
    }
}
