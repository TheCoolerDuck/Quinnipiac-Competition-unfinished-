package UI;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;



public class Panel extends JPanel {
	
	public ArrayList<UIEntity> entities = new ArrayList<UIEntity>();
	
	@Override
	protected void paintComponent(Graphics g)
	{
	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g;

	    for (UIEntity e : entities)
	    {
	        e.DrawDebugBounds(g2);
	    }
	}

}
