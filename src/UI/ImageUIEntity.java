package UI;

import BackEnd.Window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUIEntity extends UIEntity{

	private BufferedImage image;

	public double scaleX = 1;
	public double scaleY = 1;
	//so you dont have to constantly change both x and y
	public double scaleGlobal = 1;

	//region Constructors

	public ImageUIEntity(String imagePath) throws IOException {
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

	//endregion

	//region behavoir

	@Override
	protected void paintComponent(Graphics g)
	{
		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}

		super.paintComponent(g);
	}

	//endregion

	//region Getters/Setters

	public double getScaleX() {
		return scaleX;
	}

	public void setScaleX(double scale) {
		this.scaleX = scale;
	}

	public double getScaleY() {
		return scaleY;
	}

	public void setScaleY(double scale) {
		this.scaleY = scale;
	}

	public double getScaleGlobal() {
		return scaleGlobal;
	}

	public void setScaleGlobal(double scale) {
		this.scaleGlobal = scale;
	}
	
	public void setImage(String imagePath) throws IOException { image = ImageIO.read(new File(imagePath)); }
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
	}

	@Override
	public int getWidth() {
		if (image == null)
			return 0;
		return (int)(image.getWidth() * scaleX * (Window.halfScreen ? 0.5 : 1) * scaleGlobal);
	}

	@Override
	public int getHeight() {
		if (image == null)
			return 0;
		return (int)(image.getHeight() * scaleY * (Window.halfScreen ? 0.5 : 1) * scaleGlobal);
	}
	
	//endregion

	@Override
	protected void OnDispose() {
		image = null;
	}
}
