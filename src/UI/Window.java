package UI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	
	public Window()
	{
		super();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		//setSize(screenSize);
		setSize(screenSize.width / 2, screenSize.height / 2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Hello, JFrame!");
		add(label);

		setVisible(true);
	}

}
