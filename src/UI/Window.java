package UI;

import javax.swing.*;
import java.awt.*;
import BackEnd.*;

public class Window extends JFrame {

	public static final boolean halfScreen = false;
	public static Window main = null;
	
	public Panel panel;
	
	public Window()
	{
		super();
		
		if (main == null)
			main = this;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		if (halfScreen)
		{
			setLocation(screenSize.width / 4, screenSize.height / 4);
			setSize(screenSize.width / 2, screenSize.height / 2);
		}
		else
		{
			setSize(screenSize);
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		panel = new Panel();
		panel.setLayout(null);
		this.add(panel);
		
		Mouse mouse = new Mouse();
		panel.addMouseListener(mouse);
		panel.addMouseMotionListener(mouse);
	}

}
