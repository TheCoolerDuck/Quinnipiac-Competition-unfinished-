package BackEnd;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.UIEntity;

public class Mouse implements MouseListener, MouseMotionListener {
	

	@Override
	public void mouseClicked(MouseEvent e) {
		UIEntity.OnScreenClick(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		UIEntity.OnMouseDown(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		UIEntity.OnMouseUp(e);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		UIEntity.OnMouseMove(e);
		
	}

}
