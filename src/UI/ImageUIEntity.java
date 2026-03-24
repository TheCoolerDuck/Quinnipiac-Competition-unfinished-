package UI;

import BackEnd.ImageManager;
import BackEnd.Window;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUIEntity extends UIEntity{

	private String image;

	public double scaleX = 1;
	public double scaleY = 1;
	//so you dont have to constantly change both x and y
	public double scaleGlobal = 1;

	public boolean flipX;
	public boolean flipY;

	//region Constructors

	public ImageUIEntity(String imageName) {
		super();
		setImage(imageName);
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
			int x = flipX ? getScreenWidth() : 0;
			int y = flipY ? getScreenHeight() : 0;
			int w = flipX ? -getScreenWidth() : getScreenWidth();
			int h = flipY ? -getScreenHeight() : getScreenHeight();
			g.drawImage(ImageManager.get(image), x, y, w, h, null);
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
	
	public void setImage(String imageName) { image = imageName; }


	@Override
	public int getWorldWidth() {
		if (image == null)
			return 0;
		return (int)(ImageManager.get(image).getWidth() * scaleX * (Window.halfScreen ? 0.5 : 1) * scaleGlobal);
	}

	@Override
	public int getWorldHeight() {
		if (image == null)
			return 0;
		return (int)(ImageManager.get(image).getHeight() * scaleY * (Window.halfScreen ? 0.5 : 1) * scaleGlobal);
	}
	
	//endregion

	@Override
	protected void OnDispose() {
		image = null;
	}
}
