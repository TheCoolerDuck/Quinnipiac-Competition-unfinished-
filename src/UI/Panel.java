package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	Color background;
	//here is where all of the things we are drawing will go
	private Tree tree;
	
	public Panel(Color bg, Color l, Color t) {
		background = bg;
		tree = new Tree(l, t);
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}

	public Tree getTree() {
		return tree;
	}

	public void setTree(Tree tree) {
		this.tree = tree;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//drawing all the things here
		g.setColor(background);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		g.fillRect(0, screenSize.width, 0, screenSize.height);
				
		tree.drawTree(g);
		
	}
	
}
