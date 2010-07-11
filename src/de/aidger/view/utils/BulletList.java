package de.aidger.view.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import de.aidger.controller.actions.BulletListAction;
import de.aidger.view.models.UIModel;

/**
 * This class represents a bullet list.
 * 
 * @author aidGer Team
 */
@SuppressWarnings("serial")
public class BulletList extends JPanel {

    /**
     * The bullet icon.
     */
    private final Icon bullet = new ImageIcon(getClass().getResource(
        "/de/aidger/view/icons/bullet.png"));

    /**
     * The elements of the bullet list.
     */
    private final List<LinkButton> elements = new ArrayList<LinkButton>();

    /**
     * Constructs a bullet list.
     */
    public BulletList() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setOpaque(false);

        refresh();
    }

    /**
     * Refreshes the list.
     */
    private void refresh() {
        removeAll();

        for (LinkButton element : elements) {
            add(element, BorderLayout.LINE_START);
            add(Box.createRigidArea(new Dimension(0, 10)));
        }

        revalidate();
        repaint();
    }

    /**
     * Adds an element to the list.
     * 
     * @param content
     *            the content of the element to be added
     */
    public void add(String content) {
        BulletListAction blAction = new BulletListAction(content, bullet);
        blAction.markNoLink();

        elements.add(new LinkButton(blAction));

        refresh();
    }

    /**
     * Adds an element as link to the list. The link will refer to the model.
     * 
     * @param content
     *            the content of the element to be added
     * @param model
     *            the UI model the link is refer to
     */
    public void add(String content, UIModel model) {
        if (model == null) {
            add(content);

            return;
        }

        elements.add(new LinkButton(
            new BulletListAction(content, bullet, model)));

        refresh();
    }

    /**
     * Clears the list.
     */
    public void clear() {
        elements.clear();

        refresh();
    }
}
