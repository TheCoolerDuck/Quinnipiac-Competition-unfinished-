package UI;

import java.util.ArrayList;

/**
 * Represents a group of UIEntity objects as a single logical entity.
 *
 * The group’s width, height, and center are automatically
 * calculated based on the bounding rectangle of all child entities.
 *
 * Moving the group moves all contained entities proportionally.
 */
public class UIEntityGroup implements UIEntity {

    /** List of child entities in this group. */
    public ArrayList<UIEntity> entities = new ArrayList<>();

    /**
     * Constructs a UIEntityGroup and registers it with the main panel's entity list.
     */
    public UIEntityGroup() {
        Window.main.panel.entities.add(this);
    }

    /* =========================
       Dimension Calculations
       ========================= */

    /**
     * Computes the total width of the group based on the bounding rectangle
     * of all child entities.
     */
    @Override
    public int GetWidth() {
        if (entities.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[1] - bounds[0];
    }

    /**
     * Computes the total height of the group based on the bounding rectangle
     * of all child entities.
     */
    @Override
    public int GetHeight() {
        if (entities.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[1] - bounds[0];
    }

    /* =========================
       Center-Based Positioning
       ========================= */

    /**
     * Returns the logical center X coordinate of the group.
     */
    @Override
    public int GetLocX() {
        if (entities.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return (bounds[0] + bounds[1]) / 2;
    }

    /**
     * Returns the logical center Y coordinate of the group.
     */
    @Override
    public int GetLocY() {
        if (entities.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return (bounds[0] + bounds[1]) / 2;
    }

    /* =========================
       Moving the Group
       ========================= */

    /**
     * Sets the center X coordinate of the group.
     * Moves all child entities by the same delta.
     */
    @Override
    public void SetLocX(int x) {
        int delta = x - GetLocX();
        for (UIEntity entity : entities) {
            entity.SetLocX(entity.GetLocX() + delta);
        }
    }

    /**
     * Sets the center Y coordinate of the group.
     * Moves all child entities by the same delta.
     */
    @Override
    public void SetLocY(int y) {
        int delta = y - GetLocY();
        for (UIEntity entity : entities) {
            entity.SetLocY(entity.GetLocY() + delta);
        }
    }

    /* =========================
       Private Helpers
       ========================= */

    /**
     * Computes the minimum and maximum X coordinates across all child entities.
     * @return An int array: [minX, maxX]
     */
    private int[] getHorizontalBounds() {
        int min = entities.get(0).GetMinX();
        int max = entities.get(0).GetMaxX();

        for (UIEntity entity : entities) {
            if (entity.GetMinX() < min)
                min = entity.GetMinX();
            if (entity.GetMaxX() > max)
                max = entity.GetMaxX();
        }
        return new int[]{min, max};
    }

    /**
     * Computes the minimum and maximum Y coordinates across all child entities.
     * @return An int array: [minY, maxY]
     */
    private int[] getVerticalBounds() {
        int min = entities.get(0).GetMinY();
        int max = entities.get(0).GetMaxY();

        for (UIEntity entity : entities) {
            if (entity.GetMinY() < min)
                min = entity.GetMinY();
            if (entity.GetMaxY() > max)
                max = entity.GetMaxY();
        }
        return new int[]{min, max};
    }
}