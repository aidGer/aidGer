package de.aidger.model.models;

import static de.aidger.utils.Translation._;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.List;

import siena.Column;
import siena.Generator;
import siena.Id;
import siena.Model;
import siena.Query;
import siena.Table;
import de.aidger.utils.Logger;
import de.aidger.utils.history.HistoryEvent;
import de.aidger.utils.history.HistoryException;
import de.aidger.utils.history.HistoryManager;


@Table("Finanzkategorie")
public class InternalFinancialCategory extends Model {
	
	@Id(Generator.AUTO_INCREMENT)
	private Long id = null;
	
	@Column("Gruppe")
	private Long group;

    @Column("Name")
    private String name;
    
    @Column("Plankosten")
    private Integer budgetCost;

    @Column("Kostenstelle")
    private Integer costUnit;
    
    @Column("Jahr")
    private Short year;
    
    public InternalFinancialCategory() {
    	
    }
    
    public InternalFinancialCategory(InternalFinancialCategory entry) {
    	this();
    	setId(entry.getId());
    	setName(entry.getName());
    	setBudgetCost(entry.getBudgetCost());
    	setCostUnit(entry.getCostUnit());
    	setYear(entry.getYear());
    	setGroup(entry.getGroup());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBudgetCost() {
		return budgetCost;
	}

	public void setBudgetCost(Integer budgetCost) {
		this.budgetCost = budgetCost;
	}

	public Integer getCostUnit() {
		return costUnit;
	}

	public void setCostUnit(Integer costUnit) {
		this.costUnit = costUnit;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}
	
	public Query<InternalFinancialCategory> all() {
		return (Query<InternalFinancialCategory>) Model.all(this.getClass());
	}

	public Long getGroup() {
		return group;
	}

	public void setGroup(Long group) {
		this.group = group;
	}
	
	public List<InternalFinancialCategory> getAll() {
		return all().fetch();
	}
	
	public void remove() {
		Logger.info(MessageFormat.format(_("Removing model: {0}"), toString()));
		
		delete();

        /* Add event to the HistoryManager */
        HistoryEvent evt = new HistoryEvent();
        evt.id = getId();
        evt.status = HistoryEvent.Status.Removed;
        evt.type = getClass().getSimpleName();
        evt.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {
            HistoryManager.getInstance().addEvent(evt);
        } catch (HistoryException ex) {
            Logger.error(ex.getMessage());
        }
	}
	
	public void save() {
        boolean wasNew = getId() == null;

        super.save();

        /* Add event to the HistoryManager */
        HistoryEvent evt = new HistoryEvent();
        evt.id = getId();
        evt.status = wasNew ? HistoryEvent.Status.Added
                : HistoryEvent.Status.Changed;
        evt.type = getClass().getSimpleName();
        evt.date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        try {
            HistoryManager.getInstance().addEvent(evt);
        } catch (HistoryException ex) {
            Logger.error(ex.getMessage());
        }
	}
}
