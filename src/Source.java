
import java.awt.Font;

import UI.*;

public class Source {

	public static void main(String[] args) throws InterruptedException {

		new Window();
		
<<<<<<< Updated upstream
		TextUIEntity textA = new TextUIEntity("Wassup Peter?", new Font("Arial", Font.PLAIN, 25), 100, 20);
=======
		TextUIEntity title = new TextUIEntity("[Insert Name Here]");
		title.setFont(new Font("Arial", Font.PLAIN, 25));
		title.SetLocY(150);
		 
		TextUIEntity idk1 = new TextUIEntity("hello world");
		idk1.setFont(new Font("Arial", Font.PLAIN, 15));
		idk1.MoveToBottomOf(title, 10);
>>>>>>> Stashed changes
		
		ButtonUIEntity button1 = new ButtonUIEntity("Click Me :)");
		button1.SetLocY(idk1.GetLocY());
		button1.MoveToRightOf(idk1, 10);
		
		UIEntityGroup group = new UIEntityGroup();
		group.entities.add(title);
		group.entities.add(idk1);
		group.entities.add(button1);
		
		group.CenterX();
		
		Window.main.repaint();
	}

}
