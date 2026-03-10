package BackEnd;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.UIEntity;

public class Mouse implements MouseListener, MouseMotionListener {

	private static int x = 0;
	private static int y = 0;
	private static boolean isDown;

	//region Getters

	public static int getX() {
		return x;
	}
	public static int getY() {
		return y;
	}
	public static boolean isDown()
	{
		return isDown;
	}

	//endregion

	@Override
	public void mouseClicked(MouseEvent e) {
		UIEntity.OnScreenClick(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		UIEntity.OnMouseDown(e);
		isDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		UIEntity.OnMouseMove(e);
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		UIEntity.OnMouseMove(e);
		x = e.getX();
		y = e.getY();
	}

}
