package UI;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ButtonUIEntity extends ImageUIEntity {
	
	private BufferedImage idleImage;
	private BufferedImage hoverImage;
	private BufferedImage clickedImage;
    
    public ButtonUIEntity(String idleImagePath, String hoverImagePath, String clickedImagePath) {
    	
        super();
    	
		try {
			idleImage = ImageIO.read(new File(idleImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			hoverImage = ImageIO.read(new File(hoverImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			clickedImage = ImageIO.read(new File(clickedImagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.setImage(idleImage);
    	

    }
    
    protected void OnMouseDownOnThis(MouseEvent e) 
    {
    	this.setImage(clickedImage);
    }
    protected void OnMouseUpOnThis(MouseEvent e) 
    {
    	this.setImage(hoverImage);
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