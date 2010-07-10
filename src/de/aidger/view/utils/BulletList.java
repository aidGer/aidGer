package de.aidger.view.utils;

/**
 * This class represents a bullet list.
 * 
 * @author aidGer Team
 */
public class BulletList {

    /**
     * The rendered list as html.
     */
    private String list;

    /**
     * Constructs a bullet list.
     */
    public BulletList() {
        clear();
    }

    /**
     * Adds an element to the list.
     * 
     * @param element
     *            the element to be added
     */
    public void add(String element) {
        int i = list.indexOf("</ul></html>");

        list = list.substring(0, i) + "<li>" + element + "</li>"
                + list.substring(i, list.length());
    }

    /**
     * Clears the list.
     */
    public void clear() {
        list = "<html><style type=\"text/css\">ul { margin-left: 20px; list-style-type: square; } li { padding-left: 5px; margin-bottom: 10px; }</style><ul></ul></html>";
    }

    /**
     * Returns the rendered list as html.
     * 
     * @return the rendered list as html.
     * 
     */
    public String getList() {
        return list;
    }
}
