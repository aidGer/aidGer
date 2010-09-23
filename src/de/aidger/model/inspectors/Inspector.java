package de.aidger.model.inspectors;

import java.util.List;

import de.aidger.model.AbstractModel;

/**
 * Basic inspector class for checks before saving models.
 * 
 * @author aidGer Team
 */
public abstract class Inspector {
    protected String result = null;

    @SuppressWarnings( { "unchecked", "unused" })
    private List<AbstractModel> models;

    public abstract void check();

    public boolean isFail() {
        return result != null;
    }

    public String getResult() {
        return result;
    }

    @SuppressWarnings("unchecked")
    public void setModels(List<AbstractModel> models) {
        this.models = models;
    }
}
