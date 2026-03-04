package UI;

import javax.swing.JButton;

/**
 * A UIEntity implementation backed by a JButton.
 *
 * Logical positioning is center-based, while Swing
 * internally uses top-left coordinates. This class
 * performs the required coordinate translation.
 */
public class ButtonUIEntity extends JButton implements UIEntity {

    /**
     * Constructs a button UI entity and registers it
     * with the main window panel and entity list.
     *
     * @param text The button label text.
     */
    public ButtonUIEntity(String text) {
        super(text);

        Window.main.panel.add(this);
        Window.main.panel.entities.add(this);
    }

    /* =========================
       Dimension Accessors
       ========================= */

    /**
     * Returns the preferred width of the button.
     * Uses preferred size to ensure correct sizing
     * before layout resolution.
     */
    @Override
    public int GetWidth() {
        return getPreferredSize().width;
    }

    /**
     * Returns the preferred height of the button.
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