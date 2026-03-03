package UI;

import java.awt.Font;

import javax.swing.JLabel;

public class TextUIEntity extends JLabel implements UIEntity {
	
	
	public TextUIEntity(String text)
	{
		this(text, new Font("Arial", Font.PLAIN, 10), 0, 0);
	}
	
	public TextUIEntity(String text, Font font)
	{
		this(text, font, 0, 0);
	}
	
	public TextUIEntity(String text, int locX, int locY)
	{
		this(text, new Font("Arial", Font.PLAIN, 10), locX, locY);
	}
	
	public TextUIEntity(String text, Font font, int locX, int locY)
	{	
		super(text);
		setFont(font);
		
		SetLocX(locX);
		SetLocY(locY);
		
		Window.main.add(this);
	}

	public int GetWidth() {
		return getPreferredSize().width;
	}

	public int GetHeight() {
		return getPreferredSize().height;
	}
	
	//Location ajusted for central positioning
	public int GetLocX() {
		return getLocation().x + (GetWidth() / 2);
	}

	public int GetLocY() {
		return getLocation().y + (GetHeight() / 2);
	}

	public void SetLocX(int x) {
		setBounds(x - (GetWidth() / 2), GetLocY() - (GetHeight() / 2), GetWidth(), GetHeight());
	}

	public void SetLocY(int y) {
		setBounds(GetLocX() - (GetWidth() / 2), y - (GetHeight() / 2), GetWidth(), GetHeight());
	}

}
