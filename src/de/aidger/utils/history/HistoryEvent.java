package de.aidger.utils.history;

import static de.aidger.utils.Translation._;
import de.aidger.utils.Logger;
import de.aidger.model.AbstractModel;
import de.aidger.view.models.UIModel;
import java.io.Serializable;
import java.sql.Date;

/**
 * A single database related event.
 *
 * @author aidGer Team
 */
public class HistoryEvent implements Serializable {

    /**
     * The id of the model.
     */
    public Integer id;

    /**
     * The type of the model.
     */
    public String type;

    /**
     * The status of the event.
     */
    public Status status;

    /**
     * The timestamp of the event.
     */
    public Date date;

    /**
     * Get the model referenced by the event.
     *
     * @return The model
     */
    public UIModel getModel() {
        if (status.equals(Status.Removed) || type.equals("HourlyWage")) {
            return null;
        }

        try {
            Class obj = Class.forName("de.aidger.model.models." + type);
            AbstractModel a = (AbstractModel) obj.newInstance();
            Object o = a.getById(id);

            Class classUI = Class.forName("de.aidger.view.models.UI" + type);
            Class classInterface = Class
                    .forName("de.unistuttgart.iste.se.adohive.model.I" + type);
            return (UIModel) classUI.getConstructor(classInterface)
                    .newInstance(classInterface.cast(o));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * The status of the HistoryEvent.
     */
    public enum Status {
        Added(0),
	Changed(1),
	Removed(2);

	private final int id;

        /**
         * Initializes the Status enum.
         *
         * @param id
         *          The id of the status
         */
	Status(int id) {
            this.id = id;
	}

        /**
         * Get the id of the status.
         *
         * @return The id of the status
         */
	public int getId() {
            return id;
	}

        /**
         * Get the correct Status from an int
         *
         * @param id
         *          The id of the searched Status
         * @return The correct Status or null
         */
	public Status fromId(int id) {
            for (Status e : Status.values()) {
                if (e.id == id) {
                    return e;
                }
            }
            return null;
	}
    }
}
