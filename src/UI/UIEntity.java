package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public abstract class UIEntity extends JComponent {
	
    public boolean debug = true;
	
	private static ArrayList<UIEntity> entities = new ArrayList<UIEntity>();
	
	private boolean lastHoverState = false;
	
	public UIEntity()
	{
		Window.main.panel.add(this);
		entities.add(this);
	}
	

	//<<Getters/Setters

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract int getX();

    public abstract int getY();

    public abstract void setX(int x);

    public abstract void setY(int y);

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
    
    //>>
    
    //<<Managment Methods
    public static void Update()
    {
    	for (UIEntity entity : entities)
    	{
    		entity.setBounds(entity.getMinX(), entity.getMinY(), entity.getWidth(), entity.getHeight());
    		entity.OnUpdate();
    	}
    }
    
    public static void OnScreenClick(MouseEvent e)
    {
    	for (UIEntity entity : entities)
    		if (entity.getBounds().contains(e.getPoint()))
    			entity.OnClick(e);
    }
    
    public static void OnMouseDown(MouseEvent e)
    {
    	for (UIEntity entity : entities)
    		if (entity.getBounds().contains(e.getPoint()))
    			entity.OnMouseDownOnThis(e);;
    }
    
    public static void OnMouseUp(MouseEvent e)
    {
    	for (UIEntity entity : entities)
    		if (entity.getBounds().contains(e.getPoint()))
    			entity.OnMouseUpOnThis(e);;
    }
    
    public static void OnMouseMove(MouseEvent e)
    {
    	for (UIEntity entity : entities)
    	{
    		if (entity.getBounds().contains(e.getPoint()))
    		{
    			if (!entity.lastHoverState)
    				entity.OnHoverEnter(e);
    			
    			entity.lastHoverState = true;
    		}
    		else
    		{
    			if (!entity.lastHoverState)
    				entity.OnHoverExit(e);
    			
    			entity.lastHoverState = false;
    		}
    	}
    }
    
    //>>
    
    //<<Behavoir

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
    protected void OnMouseUpOnThis(MouseEvent e) {}
    protected void OnHoverEnter(MouseEvent e) {}
    protected void OnHoverExit(MouseEvent e) {}
    protected void OnUpdate() {}
    
    protected abstract void OnDispose();
    
    //>>
    
    //<<Positioning

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
    
    //>>
    
    
}