package UI;

import java.awt.Color;
import java.awt.Graphics;


public class Tree {

	private Color leaves, trunk;
	private int xRect, yRect, width, len;
	
	public Tree(Color l, Color t) {
		super();
		leaves = l;
		trunk = t;
		xRect = 80;
		yRect = 70;
		width = 40;
		len = 220;
	}

	public Color getLeaves() {
		return leaves;
	}

	public void setLeaves(Color leaves) {
		this.leaves = leaves;
	}

	public Color getTrunk() {
		return trunk;
	}

	public void setTrunk(Color trunk) {
		this.trunk = trunk;
	}

	public int getxRect() {
		return xRect;
	}

	public void setxRect(int xRect) {
		this.xRect = xRect;
	}

	public int getyRect() {
		return yRect;
	}

	public void setyRect(int yRect) {
		this.yRect = yRect;
	}

	public int getLength() {
		return width;
	}

	public void setLength(int length) {
		this.width = length;
	}

	public int getWidth() {
		return len;
	}

	public void setWidth(int width) {
		this.len = width;
	}
	
	public void drawTree(Graphics g) {
		//drawing out the trunk
		g.setColor(trunk);
		g.fillRect(xRect, yRect, width, len);
		yRect += len;
		len = width;
		width = 200;
		g.fillRect(xRect, yRect, width, len);
		
		/*xRect = 270;
		yRect = 60;
		length = 40;
		width = 320;*/
		
		
		//drawing out the leaves
		g.setColor(leaves);
		int x = 10;
		int y = 100;
		int w = 100;
		drawLeaves(g, leaves, x, y, w);
		
		x = 200;
		y = 50;
		drawLeaves(g, leaves, x, y, w);
		
		x = 400;
		y = 100;
		drawLeaves(g, leaves, x, y, w);
		
	}
	
	public void drawLeaves(Graphics g, Color l, int x, int y, int w) {
		g.setColor(l);
		g.fillOval(x, y, w, w);
		x+=20;
		y-=50;
		g.fillOval(x, y, w, w);
		x+=50;
		y+=40;
		g.fillOval(x, y, w, w);
		x-=10;
		y+=40;
		g.fillOval(x, y, w, w);
		x-=40;
		y+=15;
		g.fillOval(x, y, w, w);
	}
	
}
