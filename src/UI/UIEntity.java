package UI;

import java.awt.*;

/**
 * Represents a rectangular UI element positioned using center-based coordinates.
 *
 * The logical position (LocX, LocY) refers to the center of the entity.
 * Width and height define the bounding rectangle around that center.
 *
 * Provides convenience methods for alignment, relative positioning,
 * and optional debug-bound rendering.
 */
public interface UIEntity {

    /** Enables or disables debug bound rendering. */
    public boolean debug = true;

    /* =========================
       Core Dimension Accessors
       ========================= */

    /** @return The width of the UI entity in pixels. */
    public abstract int GetWidth();

    /** @return The height of the UI entity in pixels. */
    public abstract int GetHeight();


    /* =========================
       Center-Based Positioning
       ========================= */

    /** @return The X coordinate of the entity's center. */
    public abstract int GetLocX();

    /** @return The Y coordinate of the entity's center. */
    public abstract int GetLocY();

    /** Sets the X coordinate of the entity's center. */
    public abstract void SetLocX(int x);

    /** Sets the Y coordinate of the entity's center. */
    public abstract void SetLocY(int y);


    /* =========================
       Boundary Calculations
       ========================= */

    /** @return The right-most X coordinate of the entity. */
    public default int GetMaxX() {
        return GetLocX() + GetWidth() / 2;
    }

    /** @return The bottom-most Y coordinate of the entity. */
    public default int GetMaxY() {
        return GetLocY() + GetHeight() / 2;
    }

    /** @return The left-most X coordinate of the entity. */
    public default int GetMinX() {
        return GetLocX() - GetWidth() / 2;
    }

    /** @return The top-most Y coordinate of the entity. */
    public default int GetMinY() {
        return GetLocY() - GetHeight() / 2;
    }


    /* =========================
       Debug Rendering
       ========================= */

    /**
     * Draws a red bounding rectangle around the entity.
     * Only renders if the debug flag is enabled.
     */
    public default void DrawDebugBounds(Graphics2D g2) {
        if (!debug)
            return;

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.red);
        g2.drawRect(GetMinX(), GetMinY(), GetWidth(), GetHeight());
    }


    /* =========================
       Screen Centering Helpers
       ========================= */

    /** Horizontally centers this entity within the main window. */
    public default void CenterX() {
        SetLocX(Window.main.getContentPane().getWidth() / 2);
    }

    /** Vertically centers this entity within the main window. */
    public default void CenterY() {
        SetLocY(Window.main.getContentPane().getHeight() / 2);
    }


    /* =========================
       Relative Positioning
       ========================= */

    /** Positions this entity directly to the left of another. */
    public default void MoveToLeftOf(UIEntity other) {
        MoveToLeftOf(other, 0);
    }

    /**
     * Positions this entity to the left of another with optional spacing.
     *
     * @param other The reference entity.
     * @param separation Additional horizontal spacing in pixels.
     */
    public default void MoveToLeftOf(UIEntity other, int separation) {
        int newX = other.GetLocX()
                - (other.GetWidth() / 2)
                - (this.GetWidth() / 2)
                - separation;

        this.SetLocX(newX);
    }

    /** Positions this entity directly to the right of another. */
    public default void MoveToRightOf(UIEntity other) {
        MoveToRightOf(other, 0);
    }

    /**
     * Positions this entity to the right of another with optional spacing.
     *
     * @param other The reference entity.
     * @param separation Additional horizontal spacing in pixels.
     */
    public default void MoveToRightOf(UIEntity other, int separation) {
        int newX = other.GetLocX()
                + (other.GetWidth() / 2)
                + (this.GetWidth() / 2)
                + separation;

        this.SetLocX(newX);
    }

    /** Positions this entity directly above another. */
    public default void MoveToTopOf(UIEntity other) {
        MoveToTopOf(other, 0);
    }

    /**
     * Positions this entity above another with optional spacing.
     *
     * @param other The reference entity.
     * @param separation Additional vertical spacing in pixels.
     */
    public default void MoveToTopOf(UIEntity other, int separation) {
        int newY = other.GetLocY()
                - (other.GetHeight() / 2)
                - (this.GetHeight() / 2)
                - separation;

        this.SetLocY(newY);
    }

    /** Positions this entity directly below another. */
    public default void MoveToBottomOf(UIEntity other) {
        MoveToBottomOf(other, 0);
    }

    /**
     * Positions this entity below another with optional spacing.
     *
     * @param other The reference entity.
     * @param separation Additional vertical spacing in pixels.
     */
    public default void MoveToBottomOf(UIEntity other, int separation) {
        int newY = other.GetLocY()
                + (other.GetHeight() / 2)
                + (this.GetHeight() / 2)
                + separation;

        this.SetLocY(newY);
    }
}