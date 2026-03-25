package UI;

import BackEnd.Window;
import Source.Camera;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public abstract class UIEntity extends JComponent {

    private final static ArrayList<UIEntity> instances = new ArrayList<UIEntity>();

    public boolean debug = false;
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
		BackEnd.Window.main.panel.add(this);
		instances.add(this);
	}

	//region Getters/Setters

	/**
	 * Sets weather this components will be rendered on top of others
	 * @param z the render priority (less is higher priority)
	 */
	public void setDrawOrder(int z) { BackEnd.Window.main.panel.setComponentZOrder(this, z); }

    public void drawBefore(UIEntity o) { BackEnd.Window.main.panel.setComponentZOrder(this, Window.main.panel.getComponentZOrder(o) + 1); }

    public void drawAfter(UIEntity o) { BackEnd.Window.main.panel.setComponentZOrder(this, Window.main.panel.getComponentZOrder(o) - 1); }

    public void drawFirst() { BackEnd.Window.main.panel.setComponentZOrder(this, 0); }

    public void drawLast() { BackEnd.Window.main.panel.setComponentZOrder(this, Window.main.panel.getComponentCount() - 1); }

    public abstract int getWorldWidth();

    public abstract int getWorldHeight();

    public int getWorldArea(){
        return getWorldWidth() * getWorldHeight();
    }

    public int getScreenArea(){
        return getScreenWidth() * getScreenHeight();
    }

    public int getWorldX() { return X; }

    public int getWorldY() { return Y; }

    public void setWorldX(int x) { X = x; }

    public void setWorldY(int y) { Y = y; }

    public int getScreenMaxX() {
        return getScreenX() + getScreenWidth();
    }

    public int getScreenMaxY() {
        return getScreenY() + getScreenHeight();
    }

    public int getScreenMinX() {
        return getScreenX();
    }

    public int getScreenMinY() {
        return getScreenY();
    }

    public int getWorldMaxX() {
        return getWorldX() + getWorldWidth();
    }

    public int getWorldMaxY() {
        return getWorldY() + getWorldHeight();
    }

    public int getWorldMinX() {
        return getWorldX();
    }

    public int getWorldMinY() {
        return getWorldY();
    }
    
    public int getCentralX()
    {
    	return getWorldX() + getWorldWidth() / 2;
    }
    
    public int getCentralY()
    {
    	return getWorldY() + getWorldHeight() / 2;
    }
    
    public void setCentralX(int cx)
    {
    	setWorldX(cx - getWorldWidth() / 2);
    }
    
    public void setCentralY(int cy)
    {
    	setWorldY(cy - getWorldHeight() / 2);
    }

    public int getScreenX()
    {
        return (int) ((getCentralX() - Camera.X - (double) (getWorldWidth() + Window.main.getWidth()) / 2) / Camera.scale) + Window.main.getWidth() / 2;
    }

    public int getScreenY()
    {
        return (int) ((getCentralY() - Camera.Y - (double) (getWorldHeight() + Window.main.getHeight()) / 2) / Camera.scale) + Window.main.getHeight() / 2;
    }

    public int getScreenWidth()
    {
        return (int)(getWorldWidth() / Camera.scale);
    }

    public int getScreenHeight()
    {
        return (int)(getWorldHeight() / Camera.scale);
    }


    //endregion

    //region Managment Methods
    public static void UpdateAll()
    {
    	for (UIEntity entity : instances)
    	{
    		entity.setBounds(entity.getScreenX(), entity.getScreenY(), entity.getScreenWidth(), entity.getScreenHeight());
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
            		if (entity.onHoverExit != null)
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
        g2.drawRect(0, 0, getScreenWidth(), getScreenHeight());

        g2.setColor(Color.blue);
        g2.drawLine(0, getScreenHeight() / 2, getScreenWidth(), getScreenHeight() / 2);
        g2.drawLine(getScreenWidth() / 2, 0, getScreenWidth() / 2, getScreenHeight());
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
        setCentralX(BackEnd.Window.main.getContentPane().getWidth() / 2);
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
        this.setWorldX(other.getScreenMaxX() + separation);
    }

    public void MoveToRightOf(UIEntity other) {
        MoveToRightOf(other, 0);
    }

    public void MoveToRightOf(UIEntity other, int separation) {
        this.setWorldX(other.getWorldX() - separation - this.getWorldWidth());
    }

    public void MoveToTopOf(UIEntity other) {
        MoveToTopOf(other, 0);
    }

    public void MoveToTopOf(UIEntity other, int separation) {
        this.setWorldY(other.getWorldMinY() + separation + getWorldHeight());
    }

    public void MoveToBottomOf(UIEntity other) {
        MoveToBottomOf(other, 0);
    }

    public void MoveToBottomOf(UIEntity other, int separation) {
        this.setWorldY(other.getWorldMaxY() + separation);
    }
    
    //endregion
}