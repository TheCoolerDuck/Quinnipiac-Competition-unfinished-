package UI;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Animation.Animator;
import BackEnd.Mouse;

public class KeyUIEntity extends ImageUIEntity {
	
	public UIEntity getLock() {
		return lock;
	}

	public void setLock(UIEntity lock) {
		this.lock = lock;
	}

	private UIEntity lock;

	public Runnable onLock = null;
	
	private final Animator animatorX = new Animator(x-> this.setCentralX((int)x), this.getCentralX());
	private final Animator animatorY = new Animator(y-> this.setCentralY((int)y), this.getCentralY());
	
	private boolean dragged = false;
	private boolean locked = false;
	
	public int lockDistance = 20;
	
	public KeyUIEntity(String imageName, UIEntity lock) {
		super(imageName);
		this.lock = lock;
	}

    protected void OnMouseDownOnThis(MouseEvent e) 
    {
    	dragged = true;
    }
    
    protected void Update()
    {
    	if (!Mouse.isDown() || locked)
    		dragged = false;
    	
    	if (dragged)
    	{
        	animatorX.setGoal(Mouse.getX());
        	animatorY.setGoal(Mouse.getY());
    	}
    	
    	if (!locked &&
				//distance check
    			(this.getCentralX() - lock.getCentralX()) * (this.getCentralX() - lock.getCentralX()) + 
    			(this.getCentralY() - lock.getCentralY()) * (this.getCentralY() - lock.getCentralY())
    			< this.lockDistance * this.lockDistance) 
		{
    		locked = true;
			if (onLock != null)
				onLock.run();
    	}

		if (locked){
			animatorX.setGoal(lock.getCentralX());
			animatorY.setGoal(lock.getCentralY());
		}
    }
    
    public void setGoalX(int x)
    {
    	animatorX.setGoal(x);
    }
    
    public void setGoalY(int y)
    {
    	animatorY.setGoal(y);
    }
}
