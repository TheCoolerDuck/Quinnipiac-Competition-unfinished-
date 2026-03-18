package UI;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ButtonUIEntity extends ImageUIEntity {
	
	private String idleImage;
	private String hoverImage;
	private String clickedImage;
    
    public ButtonUIEntity(String idleImageName, String hoverImageName, String clickedImageName) {
        super();

		idleImage = idleImageName;
		hoverImage = hoverImageName;
		clickedImage = clickedImageName;
		
		this.setImage(idleImage);
    }
    
    protected void OnMouseDownOnThis(MouseEvent e) 
    {
    	this.setImage(clickedImage);
    }  
    protected void OnHoverEnter(MouseEvent e) 
    {
    	this.setImage(hoverImage);
    }
    protected void OnHoverExit(MouseEvent e) 
    {
    	this.setImage(idleImage);
    }
    
	@Override
	protected void OnDispose() {
		idleImage = null;
		hoverImage = null;
		clickedImage = null;
	}

}