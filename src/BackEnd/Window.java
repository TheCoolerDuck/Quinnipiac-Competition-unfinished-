package BackEnd;

import Source.Camera;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	private static final boolean halfScreen = false;
	public static Window main = null;
	public static final Color background = new Color(181, 217, 213);
	
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
		setUndecorated(true);
		
		panel = new Panel();
		panel.setBackground(background);
		panel.setLayout(null);
		this.add(panel);
		
		Mouse mouse = new Mouse();
		panel.addMouseListener(mouse);
		panel.addMouseMotionListener(mouse);
	}

}
