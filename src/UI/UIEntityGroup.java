package UI;

import java.util.ArrayList;

public class UIEntityGroup extends UIEntity {

    public ArrayList<UIEntity> entities = new ArrayList<>();

    public UIEntityGroup() {
        super();
    }

    @Override
    public int getWidth() {
        if (entities == null || entities.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[1] - bounds[0];
    }

    @Override
    public int getHeight() {
        if (entities == null || entities.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[1] - bounds[0];
    }

    @Override
    public int getX() {
        if (entities == null || entities.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[0];
    }

    @Override
    public int getY() {
        if (entities.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[0];
    }

    @Override
    public void setX(int x) {
        int delta = x - getX();
        for (UIEntity entity : entities) {
            entity.setX(entity.getX() + delta);
        }
    }

    @Override
    public void setY(int y) {
        int delta = y - getY();
        for (UIEntity entity : entities) {
            entity.setY(entity.getY() + delta);
        }
    }

    private int[] getHorizontalBounds() {
        int min = entities.get(0).getMinX();
        int max = entities.get(0).getMaxX();

        for (UIEntity entity : entities) {
            if (entity.getMinX() < min)
                min = entity.getMinX();
            if (entity.getMaxX() > max)
                max = entity.getMaxX();
        }
        return new int[]{min, max};
    }

    private int[] getVerticalBounds() {
        int min = entities.get(0).getMinY();
        int max = entities.get(0).getMaxY();

        for (UIEntity entity : entities) {
            if (entity.getMinY() < min)
                min = entity.getMinY();
            if (entity.getMaxY() > max)
                max = entity.getMaxY();
        }
        return new int[]{min, max};
    }
}