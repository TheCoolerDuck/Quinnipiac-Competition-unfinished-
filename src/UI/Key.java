package UI;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Animation.Animator;
import BackEnd.Mouse;

public class Key extends ImageUIEntity {
	
	public Lock getLock() {
		return lock;
	}

	public void setLock(Lock lock) {
		this.lock = lock;
	}

	private Lock lock;
	
	private final Animator animatorX = new Animator(x-> this.setCentralX((int)x), this::getCentralX);
	private final Animator animatorY = new Animator(y-> this.setCentralY((int)y), this::getCentralY);
	
	private boolean dragged = false;
	private boolean locked = false;
	
	public int lockDistance = 20;
	
	public Key(String imagePath, Lock lock) throws IOException {
		super(imagePath);
		init(lock);
	}
	
	public Key(BufferedImage image, Lock lock) {
		super(image);
		init(lock);
	}
	
	private void init(Lock lock)
	{	  	
    	animatorX.setNaturalFrequency(2);
    	animatorY.setNaturalFrequency(2);
    	
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
