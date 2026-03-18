package UI;

import java.util.ArrayList;

public class StackUIEntity extends UIEntity {

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    public ArrayList<UIEntity> children = new ArrayList<>();
    public int orientation = VERTICAL;

    //this is done so objects with 0 area have no spacing added, also looks better imo
    public double spacingPerArea = 0;

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
        int mainAxisStart = isVertical ? getMinY() : getMinX();
        int cursor = 0;

        for (UIEntity child : children) {
            int halfSpacing = (int) Math.abs(spacingPerArea * child.getArea() / 2);

            if (isVertical) {
                child.setCentralX(crossAxisCenter);
                if (cursor > 0) cursor += halfSpacing;
                child.setY(mainAxisStart + (int) cursor);
                cursor +=  Math.abs(child.getHeight()) + halfSpacing;
            } else {
                child.setCentralY(crossAxisCenter);
                if (cursor > 0) cursor += halfSpacing;
                child.setX(mainAxisStart + (int) cursor);
                cursor += Math.abs(child.getWidth()) + halfSpacing;
            }
        }
    }

    //region Getters/Setters

    @Override
    public int getWidth() {
        if (orientation == VERTICAL) {
            return children.stream()
                    .mapToInt(UIEntity::getWidth)
                    .max()
                    .orElse(0);
        }

        int width = 0;
        for (int i = 0; i < children.size(); i++) {
            UIEntity child = children.get(i);
            if (i > 0)
                width += (int) Math.abs(spacingPerArea * child.getArea()) / 2;
            width += Math.abs(child.getWidth());
            if (i < children.size() - 1)
                width += (int)  Math.abs(spacingPerArea * child.getArea()) / 2;
        }
        return width;
    }

    @Override
    public int getHeight() {
        if (orientation == HORIZONTAL) {
            return children.stream()
                    .mapToInt(UIEntity::getHeight)
                    .max()
                    .orElse(0);
        }

        int height = 0;
        for (int i = 0; i < children.size(); i++) {
            UIEntity child = children.get(i);
            if (i > 0)
                height += (int) Math.abs(spacingPerArea * child.getArea()) / 2;
            height +=  Math.abs(child.getHeight());
            if (i < children.size() - 1)
                height += (int) Math.abs(spacingPerArea * child.getArea()) / 2;
        }
        return height;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public double getSpacingPerArea() {
        return spacingPerArea;
    }

    public void setSpacingPerArea(double spacingPerArea) {
        this.spacingPerArea = spacingPerArea;
    }

    //endregion

    @Override
    protected void OnDispose() {
        children.clear();
        children = null;
    }
}