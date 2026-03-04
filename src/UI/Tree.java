package UI;

import java.awt.Color;
import java.awt.Graphics;


public class Tree {

	private Color leaves, trunk;
	private int xRect, yRect, length, width;
	private int[] xCoords;
	private int[] yCoords;
	
	public Tree(Color l, Color t) {
		super();
		leaves = l;
		trunk = t;
		xRect = 180;
		yRect = 530-200;
		length = 40;
		width = 120;
		xCoords = new int[3];
		yCoords = new int[3];
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
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int[] getxCoords() {
		return xCoords;
	}

	public void setxCoords(int i) {
		xCoords[0] = i - 50;
		xCoords[1] = i;
		xCoords[2] = i + 50;
	}

	public int[] getyCoords() {
		return yCoords;
	}

	public void setyCoords(int i) {
		yCoords[0] = i + 100;
		yCoords[1] = i;
		yCoords[2] = i + 100;
	}
	
	public void drawTree(Graphics g) {
		g.setColor(trunk);
		g.fillRect(xRect, yRect, length, width);
		g.setColor(leaves);
		g.fillPolygon(xCoords, yCoords, 3);

		xCoords = new int[] { 130, 200, 270 };
		yCoords = new int[] { 410-200, 310-200, 410-200 };
		g.fillPolygon(xCoords, yCoords, 3);

		xCoords = new int[] { 110, 200, 290 };
		yCoords = new int[] { 450-200, 350-200, 450-200 };
		g.fillPolygon(xCoords, yCoords, 3);

		xCoords = new int[] { 90, 200, 310 };
		yCoords = new int[] { 490-200, 390-200, 490-200 };
		g.fillPolygon(xCoords, yCoords, 3);

		xCoords = new int[] { 90 - 20, 200, 310 + 20 };
		yCoords = new int[] { 530-200, 430-200, 530-200 };
		g.fillPolygon(xCoords, yCoords, 3);
	}
	
	
	
}
