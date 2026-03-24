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
        g.fillOval(getCenterOfMassX() - getWorldX() - 5, getCenterOfMassY() - getWorldY() - 5, 10, 10);
    }

    //region Getters/Setters

    @Override
    public int getWorldWidth() {
        if (children == null || children.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[1] - bounds[0];
    }

    @Override
    public int getWorldHeight() {
        if (children == null || children.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[1] - bounds[0];
    }

    @Override
    public int getWorldX() {
        if (children == null || children.isEmpty())
            return 0;

        int[] bounds = getHorizontalBounds();
        return bounds[0];
    }

    @Override
    public int getWorldY() {
        if (children.isEmpty())
            return 0;

        int[] bounds = getVerticalBounds();
        return bounds[0];
    }

    @Override
    public void setWorldX(int x) {
        int delta = x - getWorldX();
        for (UIEntity entity : children) {
            entity.setWorldX(entity.getWorldX() + delta);
        }
    }

    @Override
    public void setWorldY(int y) {
        int delta = y - getWorldY();
        for (UIEntity entity : children) {
            entity.setWorldY(entity.getWorldY() + delta);
        }
    }

    public int getCenterOfMassX() {
        int totalMass = 0;
        int runningCenter = 0;

        for (UIEntity entity : children)
        {
            totalMass += entity.getWorldWidth() * entity.getWorldHeight();
            runningCenter += entity.getCentralX() * entity.getWorldWidth() * entity.getWorldHeight();
        }

        return runningCenter / totalMass;
    }

    public int getCenterOfMassY() {
        int totalMass = 0;
        int runningCenter = 0;

        for (UIEntity entity : children)
        {
            totalMass += entity.getWorldWidth() * entity.getWorldHeight();
            runningCenter += entity.getCentralY() * entity.getWorldWidth() * entity.getWorldHeight();
        }

        return runningCenter / totalMass;
    }

    public void setCenterOfMassX(int x)
    {
        setWorldX(x + (getWorldX() - getCenterOfMassX()));
    }

    public void setCenterOfMassY(int y)
    {
        setWorldY(y + (getWorldY() - getCenterOfMassY()));
    }

    private int[] getHorizontalBounds() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (UIEntity entity : children) {
        	if (entity == this || entity.getWorldWidth() <= 0 || entity.getWorldHeight() <= 0)
        		continue;
        	
            if (entity.getWorldMinX() < min)
                min = entity.getWorldMinX();
            if (entity.getScreenMaxX() > max)
                max = entity.getScreenMaxX();
        }
        return new int[]{min, max};
    }

    private int[] getVerticalBounds() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (UIEntity entity : children) {
        	if (entity == this || entity.getWorldWidth() <= 0 || entity.getWorldHeight() <= 0)
        		continue;
        	
            if (entity.getWorldMinY() < min)
                min = entity.getWorldMinY();
            if (entity.getWorldMaxY() > max)
                max = entity.getWorldMaxY();
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