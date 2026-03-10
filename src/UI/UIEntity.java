package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public abstract class UIEntity extends JComponent {

    private final static ArrayList<UIEntity> instances = new ArrayList<UIEntity>();

    public boolean debug = true;
	private boolean lastHoverState = false;

    private int X;
    private int Y;

    //region Runnables
	public Runnable onUpdate = null;
	public Runnable onClick = null;
	public Runnable onMouseDownOnThis = null;
	public Runnable onHoverEnter = null;
	public Runnable onHoverExit = null;
	public Runnable onDispose = null;
	//endregion
	
	public UIEntity()
	{
		Window.main.panel.add(this);
		instances.add(this);
	}

	//region Getters/Setters

	/**
	 * Sets weather this components will be rendered on top of others
	 * @param z the render priority (less is higher priority)
	 */
	public void setDrawOrder(int z) { Window.main.panel.setComponentZOrder(this, z); }

    public abstract int getWidth();

    public abstract int getHeight();

    public int getX() { return X; }

    public int getY() { return Y; }

    public void setX(int x) { X = x; }

    public void setY(int y) { Y = y; }

    public int getMaxX() {
        return getX() + getWidth();
    }

    public int getMaxY() {
        return getY() + getHeight();
    }

    public int getMinX() {
        return getX();
    }

    public int getMinY() {
        return getY();
    }
    
    public int getCentralX()
    {
    	return getX() + getWidth() / 2;
    }
    
    public int getCentralY()
    {
    	return getY() + getHeight() / 2;
    }
    
    public void setCentralX(int cx)
    {
    	setX(cx - getWidth() / 2);
    }
    
    public void setCentralY(int cy)
    {
    	setY(cy - getHeight() / 2);
    }
    
    //endregion

    //region Managment Methods
    public static void UpdateAll()
    {
    	for (UIEntity entity : instances)
    	{
    		entity.setBounds(entity.getMinX(), entity.getMinY(), entity.getWidth(), entity.getHeight());
    		entity.Update();
    		
    		if (entity.onUpdate != null)
    			entity.onUpdate.run();
    	}
    }
    
    public static void OnScreenClick(MouseEvent e)
    {
    	for (UIEntity entity : instances)
    	{
    		if (entity.getBounds().contains(e.getPoint()))
    		{
    			entity.OnClick(e);
        		
        		if (entity.onClick != null)
        			entity.onClick.run();
    		}
    	}
    }
    
    public static void OnMouseDown(MouseEvent e)
    {
    	for (UIEntity entity : instances)
    	{
    		if (entity.getBounds().contains(e.getPoint()))
    		{
    			entity.OnMouseDownOnThis(e);
    			
        		if (entity.onMouseDownOnThis != null)
        			entity.onMouseDownOnThis.run();
    		}
    	}
    }
    
    public static void OnMouseMove(MouseEvent e)
    {
    	for (UIEntity entity : instances)
    	{
    		if (entity.getBounds().contains(e.getPoint()))
    		{
    			if (!entity.lastHoverState)
    			{
    				entity.OnHoverEnter(e);
            		if (entity.onHoverEnter != null)
            			entity.onHoverEnter.run();
    			}
    			
    			entity.lastHoverState = true;
    		}
    		else
    		{
    			if (!entity.lastHoverState)
    			{
    				entity.OnHoverExit(e);
            		if (entity.onHoverEnter != null)
            			entity.onHoverExit.run();
    			}
    			
    			entity.lastHoverState = false;
    		}
    	}
    }
    
    //endregion
    
    //region Behavoir

    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
        if (!debug)
            return;
        
        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.red);
        g2.drawRect(0, 0, getWidth(), getHeight());
    }
    
    protected void OnClick(MouseEvent e) {}
    protected void OnMouseDownOnThis(MouseEvent e) {}
    protected void OnHoverEnter(MouseEvent e) {}
    protected void OnHoverExit(MouseEvent e) {}
    protected void Update() {}
    
    protected abstract void OnDispose();
    
    //endregion
    
    //region Positioning

    public void CenterX() {
        setCentralX(Window.main.getContentPane().getWidth() / 2);
    }

    public void CenterY() {
        setCentralY(Window.main.getContentPane().getHeight() / 2);
    }
    
    public void AlignXWith(UIEntity other)
    {
    	setCentralX(other.getCentralX());
    }
    
    public void AlignYWith(UIEntity other)
    {
    	setCentralY(other.getCentralY());
    }

    public void MoveToLeftOf(UIEntity other) {
        MoveToLeftOf(other, 0);
    }

    public void MoveToLeftOf(UIEntity other, int separation) {
        this.setX(other.getMaxX() + separation);
    }

    public void MoveToRightOf(UIEntity other) {
        MoveToRightOf(other, 0);
    }

    public void MoveToRightOf(UIEntity other, int separation) {
        this.setX(other.getX() - separation - this.getWidth());
    }

    public void MoveToTopOf(UIEntity other) {
        MoveToTopOf(other, 0);
    }

    public void MoveToTopOf(UIEntity other, int separation) {
        this.setY(other.getMinY() + separation + getHeight());
    }

    public void MoveToBottomOf(UIEntity other) {
        MoveToBottomOf(other, 0);
    }

    public void MoveToBottomOf(UIEntity other, int separation) {
        this.setY(other.getMaxY() + separation);
    }
    
    //endregion
}