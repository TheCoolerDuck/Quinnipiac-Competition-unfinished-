package UI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	
	public static Window main = null;
	
	public Window()
	{
		super();
		
		if (main == null)
			main = this;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//setSize(screenSize);
		setLocation(screenSize.width / 4, screenSize.height / 4);
		setSize(screenSize.width / 2, screenSize.height / 2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}

}
