package UI;

import java.awt.*;
import javax.swing.*;


public class Panel extends JPanel {
	
	public boolean debug = false;
	
	@Override
	protected void paintComponent(Graphics g)
	{
	    super.paintComponent(g);

	    if (debug)
	    {
	    	g.setColor(Color.blue);
	    	g.drawLine(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
	    	g.drawLine(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
	    }
	}

}
