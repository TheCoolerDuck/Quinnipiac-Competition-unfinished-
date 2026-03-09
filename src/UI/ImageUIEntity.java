package UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUIEntity extends UIEntity{

	private BufferedImage image;

	public int X = 0;
	public int Y = 0;
	
	public double scale = 1;

	public ImageUIEntity(String imagePath) {
		super();
		setImage(imagePath);
	}
	
	public ImageUIEntity(BufferedImage image) {
		super();
		setImage(image);
	}
	
	public ImageUIEntity() {
		super();
	}

	@Override
	protected void paintComponent(Graphics g)
	{	
		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}

		super.paintComponent(g);
	}

	//<<Getters/Setters
	
	public void setImage(String imagePath)
	{
		try {
			image = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	@Override
	public int getWidth() {
		if (image == null)
			return 0;
		return (int)(image.getWidth() * scale);
	}

	@Override
	public int getHeight() {
		if (image == null)
			return 0;
		return (int)(image.getHeight() * scale);
	}

	@Override
	public int getX() {
		return X;
	}

	@Override
	public int getY() {
		return Y;
	}

	@Override
	public void setX(int x) {
		X = x;
	}

	@Override
	public void setY(int y) {
		Y = y;
	}
	
	//>>

	@Override
	protected void OnDispose() {
		image = null;
	}
}
