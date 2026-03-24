package UI;

import Source.Camera;

import java.util.ArrayList;

public class StackUIEntity extends UIEntity {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public ArrayList<UIEntity> children = new ArrayList<>();
    public int orientation = VERTICAL;

    //this is done so objects with 0 area have no spacing added, also looks better imo
    public double spacingFactor = 0;

    public StackUIEntity(int orientation) {
        super();
        this.orientation = orientation;
    }

    public StackUIEntity() {
        super();
    }

    @Override
    protected void Update() {
        super.Update();

        boolean isVertical = orientation == VERTICAL;
        int crossAxisCenter = isVertical ? getCentralX() : getCentralY();
        int mainAxisStart = isVertical ? getWorldMinY() : getWorldMinX();
        int cursor = 0;

        for (UIEntity child : children) {

            if (child.getWorldArea() == 0)
                continue;

            cursor += (int) (child.getWorldArea() * spacingFactor / 2e4);

            if (isVertical) {
                child.setCentralX(crossAxisCenter);
                child.setWorldY(mainAxisStart + cursor);
            } else {
                child.setCentralY(crossAxisCenter);
                child.setWorldX(mainAxisStart + cursor);
            }

            cursor += isVertical ? child.getWorldHeight() : child.getWorldWidth();

            cursor += (int) (child.getWorldArea() * spacingFactor / 2e4);
        }
    }

    //region Getters/Setters

    @Override
    public int getWorldWidth() {
        if (children.isEmpty())
            return 0;

        if (orientation == VERTICAL) {
            return children.stream()
                    .mapToInt(x -> x.getWorldWidth() + (int) (x.getWorldArea() * spacingFactor / 1e4))
                    .max()
                    .orElse(0);
        }

        int width = 0;
        UIEntity last = null;
        for (UIEntity child : children) {

            if (child.getWorldArea() == 0)
                continue;

            width += child.getWorldWidth();

            width += (int) (child.getWorldArea() * spacingFactor / 1e4);
        }

        return width;
    }

    @Override
    public int getWorldHeight() {
        if (children.isEmpty())
            return 0;

        if (orientation == HORIZONTAL) {
            return children.stream()
                    .mapToInt(x -> x.getWorldHeight() + (int) (x.getWorldArea() * spacingFactor / 1e4))
                    .max()
                    .orElse(0);
        }

        int height = 0;
        for (UIEntity child : children) {

            if (child.getWorldArea() == 0)
                continue;

            height += child.getWorldHeight();

            height += (int) (child.getWorldArea() * spacingFactor / 1e4);
        }

        return height;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public double getSpacingFactor() {
        return spacingFactor;
    }

    public void setSpacingFactor(double spacingFactor) {
        this.spacingFactor = spacingFactor;
    }

    //endregion

    @Override
    protected void OnDispose() {
        children.clear();
        children = null;
    }
}