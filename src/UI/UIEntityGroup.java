package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class UIEntityGroup extends UIEntity {

    public ArrayList<UIEntity> children = new ArrayList<>();

    public UIEntityGroup() {
        super();
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        if (!debug)
            return;
        
        g.setColor(Color.green);
        g.fillOval(getCenterOfMassX() - getX() - 5, getCenterOfMassY() - getY() - 5, 10, 10);
    }

    //region Getters/Setters

    @Override
    public int getWidth() {
        if (children == null || children.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[1] - bounds[0];
    }

    @Override
    public int getHeight() {
        if (children == null || children.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[1] - bounds[0];
    }

    @Override
    public int getX() {
        if (children == null || children.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[0];
    }

    @Override
    public int getY() {
        if (children.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[0];
    }

    @Override
    public void setX(int x) {
        int delta = x - getX();
        for (UIEntity entity : children) {
            entity.setX(entity.getX() + delta);
        }
    }

    @Override
    public void setY(int y) {
        int delta = y - getY();
        for (UIEntity entity : children) {
            entity.setY(entity.getY() + delta);
        }
    }

    public int getCenterOfMassX() {
        int totalMass = 0;
        int runningCenter = 0;

        for (UIEntity entity : children)
        {
            totalMass += entity.getWidth() * entity.getHeight();
            runningCenter += entity.getCentralX() * entity.getWidth() * entity.getHeight();
        }

        return runningCenter / totalMass;
    }

    public int getCenterOfMassY() {
        int totalMass = 0;
        int runningCenter = 0;

        for (UIEntity entity : children)
        {
            totalMass += entity.getWidth() * entity.getHeight();
            runningCenter += entity.getCentralY() * entity.getWidth() * entity.getHeight();
        }

        return runningCenter / totalMass;
    }

    public void setCenterOfMassX(int x)
    {
        setX(x + (getX() - getCenterOfMassX()));
    }

    public void setCenterOfMassY(int y)
    {
        setY(y + (getY() - getCenterOfMassY()));
    }

    private int[] getHorizontalBounds() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (UIEntity entity : children) {
        	if (entity == this || entity.getWidth() <= 0 || entity.getHeight() <= 0)
        		continue;
        	
            if (entity.getMinX() < min)
                min = entity.getMinX();
            if (entity.getMaxX() > max)
                max = entity.getMaxX();
        }
        return new int[]{min, max};
    }

    private int[] getVerticalBounds() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (UIEntity entity : children) {
        	if (entity == this || entity.getWidth() <= 0 || entity.getHeight() <= 0)
        		continue;
        	
            if (entity.getMinY() < min)
                min = entity.getMinY();
            if (entity.getMaxY() > max)
                max = entity.getMaxY();
        }
        return new int[]{min, max};
    }
    
    //endregion

	@Override
	protected void OnDispose() {
		children.clear();
		children = null;
	}
}