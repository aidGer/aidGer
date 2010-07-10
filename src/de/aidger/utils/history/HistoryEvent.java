package de.aidger.utils.history;

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
