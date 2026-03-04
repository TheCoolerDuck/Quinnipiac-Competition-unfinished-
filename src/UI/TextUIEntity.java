package UI;

import javax.swing.JLabel;

/**
 * A UIEntity implementation backed by a JLabel.
 *
 * Positioning is center-based logically, but Swing uses
 * top-left corner coordinates. This class handles the
 * coordinate translation internally.
 */
public class TextUIEntity extends JLabel implements UIEntity {

    /**
     * Constructs a text UI element and registers it
     * with the main window panel and entity list.
     *
     * @param text The label text.
     */
    public TextUIEntity(String text) {
        super(text);

        Window.main.panel.add(this);
        Window.main.panel.entities.add(this);
    }

    /* =========================
       Dimension Accessors
       ========================= */

    /** 
     * Returns the preferred width of the label.
     * Uses preferred size to avoid zero-width issues
     * before layout pass.
     */
    @Override
    public int GetWidth() {
        return getPreferredSize().width;
    }

    /** 
     * Returns the preferred height of the label.
     */
    @Override
    public int GetHeight() {
        return getPreferredSize().height;
    }

    /* =========================
       Center-Based Positioning
       ========================= */

    /**
     * Returns the logical center X coordinate.
     * Converts Swing's top-left X into center-based X.
     */
    @Override
    public int GetLocX() {
        return getX() + (GetWidth() / 2);
    }

    /**
     * Returns the logical center Y coordinate.
     * Converts Swing's top-left Y into center-based Y.
     */
    @Override
    public int GetLocY() {
        return getY() + (GetHeight() / 2);
    }

    /**
     * Sets the logical center X coordinate.
     * Translates center-based X into top-left bounds.
     */
    @Override
    public void SetLocX(int x) {
        setBounds(
            x - (GetWidth() / 2),
            GetLocY() - (GetHeight() / 2),
            GetWidth(),
            GetHeight()
        );
    }

    /**
     * Sets the logical center Y coordinate.
     * Translates center-based Y into top-left bounds.
     */
    @Override
    public void SetLocY(int y) {
        setBounds(
            GetLocX() - (GetWidth() / 2),
            y - (GetHeight() / 2),
            GetWidth(),
            GetHeight()
        );
    }
}