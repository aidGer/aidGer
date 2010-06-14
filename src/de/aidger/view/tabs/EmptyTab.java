package de.aidger.view.tabs;

import static de.aidger.utils.Translation._;

/**
 * An empty tab that will be created when adding a new tab with the "+" button.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class EmptyTab extends Tab {

    /**
     * Initializes a new empty tab.
     */
    public EmptyTab() {
    }

    /**
     * Don't add the empty tab into the saved tabs array.
     *
     * @return null
     */
    @Override
    public String toString() {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.aidger.view.tabs.Tab#getTabName()
     */
    @Override
    public String getTabName() {
        return _("(Unnamed)");
    }

}
