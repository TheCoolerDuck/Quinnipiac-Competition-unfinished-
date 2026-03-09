package UI;

import java.awt.*;

public class TextUIEntity extends UIEntity {

	private String text = "";
    private Font font = null;
    private FontMetrics fontMetrics = null;
	private Color color = Color.black;
    
    public int X = 0;
    public int Y = 0;
    
    
    public TextUIEntity() {
        super();
        this.setFont(new Font("Arial", Font.PLAIN, 10));
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	
    	g.setFont(font);
    	g.setColor(color);
    	g.drawString(text, 0, fontMetrics.getAscent());
    }
    
    //<<Getters/Setters

    @Override
    public int getWidth() {
    	if (fontMetrics == null)
    		return 0;
    	return fontMetrics.stringWidth(text);
    }

    @Override
    public int getHeight() {
    	if (fontMetrics == null)
    		return 0;
    	return fontMetrics.getHeight();
    }

    @Override
    public int getX() {
    	return X;
    }

    @Override
    public int getY() {
    	return Y;
    }

    @Override
    public void setX(int x) {
    	X = x;
    }

    @Override
    public void setY(int y) {
    	Y = y;
    }
    
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		Window.main.repaint();
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		this.fontMetrics = Window.main.getGraphics().getFontMetrics(font);
		Window.main.repaint();
	}
	
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		Window.main.repaint();
	}
	//>>

	@Override
	protected void OnDispose() {
		text = null;
		font = null;
		fontMetrics = null;
		color = null;
	}
}