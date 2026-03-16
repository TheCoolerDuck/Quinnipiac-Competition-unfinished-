package UI;

import BackEnd.Window;

import java.awt.*;

public class TextUIEntity extends UIEntity {

	private String text = "";
    private Font font = null;
    private FontMetrics fontMetrics = null;
	private Color color = Color.black;

    public TextUIEntity() {
        super();
        this.setFont(new Font("Arial", Font.PLAIN, 20 / (BackEnd.Window.halfScreen ? 2 : 1)));
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
    	super.paintComponent(g);

    	g.setFont(font);
    	g.setColor(color);
    	g.drawString(text, 0, fontMetrics.getAscent());
    }
    
    //region Getters/Setters

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
    
    public String getText() {
		return text;
	}

	public void setText(String text) { this.text = text; }

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) { this.font = font; this.fontMetrics = Window.main.getGraphics().getFontMetrics(font); }
	
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) { this.color = color; }
	//endregion

	@Override
	protected void OnDispose() {
		text = null;
		font = null;
		fontMetrics = null;
		color = null;
	}
}