
import java.awt.Font;
import UI.*;

public class Source {

	public static void main(String[] args) throws InterruptedException {

		new Window();

		TextUIEntity title = new TextUIEntity();
		title.setText("[Insert Name Here]");
		title.setFont(new Font("Arial", Font.PLAIN, 25));
		title.setY(150);
		 
		TextUIEntity idk1 = new TextUIEntity();
		idk1.setText("hello world");
		idk1.setFont(new Font("Arial", Font.PLAIN, 15));
		idk1.AlignXWith(title);
		idk1.MoveToBottomOf(title, 10);
		
		ButtonUIEntity button1 = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");
		button1.scale = 0.75;
		button1.AlignXWith(title);
		button1.MoveToBottomOf(idk1, 10);

		
		UIEntityGroup group = new UIEntityGroup();
		group.entities.add(title);
		group.entities.add(idk1);
		group.entities.add(button1);
		
		group.CenterX();
		
		while (Window.main.isActive())
		{
		
			UIEntity.Update();
			
			Window.main.repaint();
			
			Thread.sleep(1000 / 25);
		}
	}

}
