package UI;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

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
	
	private Animator animatorX = new Animator(x-> this.setCentralX((int)x), () -> this.getCentralX());
	private Animator animatorY = new Animator(y-> this.setCentralY((int)y), () -> this.getCentralY());
	
	private boolean dragged = false;
	private boolean locked = false;
	
	public int lockDistance = 20;
	
	public Key(String imagePath, Lock lock) {
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
    	
    	animatorX.setDampingCoefficient(0.5);
    	animatorY.setDampingCoefficient(0.5);
    	
    	this.lock = lock;
	}
	
	
    protected void OnMouseDownOnThis(MouseEvent e) 
    {
    	dragged = true;
    }
    
    protected void OnUpdate()
    {
    	if (!Mouse.isDown() || locked)
    		dragged = false;
    	
    	if (dragged)
    	{
        	animatorX.setGoal(Mouse.getX());
        	animatorY.setGoal(Mouse.getY());
    	}
    	
    	if (locked == false && 
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
