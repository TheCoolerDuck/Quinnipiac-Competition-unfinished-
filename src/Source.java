import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import UI.*;

public class Source {

	public static void main(String[] args) throws InterruptedException {

		Window win = new Window();
		
		TextUIEntity textA = new TextUIEntity("Hello Ayoni :)", new Font("Arial", Font.PLAIN, 25), 100, 20);
		
		int speedX = 5;
		int speedY = 5;
		
		
		while (win.isActive())
		{
			if (textA.GetLocX() + (textA.GetWidth() / 2) > win.getContentPane().getWidth() || textA.GetLocX() < textA.GetWidth() / 2)
			{
				speedX *= -1;
				textA.setForeground(getRandomColor());
			}
			
			if (textA.GetLocY() + (textA.GetHeight() / 2) > win.getContentPane().getHeight() || textA.GetLocY() < textA.GetHeight() / 2)
			{
				speedY *= -1;
				textA.setForeground(getRandomColor());
			}
			
			textA.SetLocX(textA.GetLocX() + speedX);
			textA.SetLocY(textA.GetLocY() + speedY);
			
			win.repaint();
			Thread.sleep(1000 / 50);
		}
	}
	
    public static Color getRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256); // Red component (0-255)
        int g = rand.nextInt(256); // Green component (0-255)
        int b = rand.nextInt(256); // Blue component (0-255)
        return new Color(r, g, b);
    }

}
