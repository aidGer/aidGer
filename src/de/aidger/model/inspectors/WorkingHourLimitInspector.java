package de.aidger.model.inspectors;

import static de.aidger.utils.Translation._;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.aidger.model.models.Assistant;
import de.aidger.model.models.Employment;
import de.aidger.view.models.UIAssistant;
import de.unistuttgart.iste.se.adohive.exceptions.AdoHiveException;

/**
 * An inspector for the working hour limit of 85h per month in a period of time
 * for assistants.
 * 
 * @author aidGer Team
 */
public class WorkingHourLimitInspector extends Inspector {

    /**
     * The assistant to be checked.
     */
    private final Assistant assistant;

    /**
     * The period of time to be checked.
     */
    private final List<Date> dates;

    /**
     * Creates the working hour limit inspector.
     * 
     * @param assistant
     *            the assistant to be checked
     * @param dates
     *            the period of time to be checked
     */
    public WorkingHourLimitInspector(Assistant assistant, List<Date> dates) {
        this.assistant = assistant;
        this.dates = dates;

        updatedDBRequired = true;
    }

    /*
     * Checks the working hour limit of 85h per month in the given period of
     * time for an assistant.
     */
    @Override
    public void check() {
        int limit = 85;

        try {
            Map<Date, Double> hourCounts = new HashMap<Date, Double>();

            for (Date date : dates) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                byte month = (byte) (cal.get(Calendar.MONTH) + 1);
                short year = (short) cal.get(Calendar.YEAR);

                List<Employment> employments = (new Employment())
                    .getEmployments(year, month, year, month);

                for (Employment employment : employments) {
                    if (employment.getAssistantId() == assistant.getId()) {
                        if (hourCounts.get(date) == null) {
                            hourCounts.put(date, employment.getHourCount());
                        } else {
                            hourCounts.put(date, hourCounts.get(date)
                                    + employment.getHourCount());
                        }
                    }
                }
            }

            boolean exceed = false;

            Set<Date> set = hourCounts.keySet();

            String limitMsg = MessageFormat
                .format(
                    _("The working hour limit of {0}h for the assistant {1} is exceeded in the following dates:"),
                    new Object[] { limit, new UIAssistant(assistant).toString() })
                    + " ";

            for (Date date : set) {
                if (hourCounts.get(date) > limit) {
                    limitMsg += (new SimpleDateFormat("MM.yyyy")).format(date)
                            + " ";

                    exceed = true;
                }
            }

            if (exceed) {
                result = limitMsg;
            }
        } catch (AdoHiveException e) {
        }
    }

}
