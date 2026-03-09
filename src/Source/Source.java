package Source;

import java.awt.Font;
import Animation.Animator;
import UI.*;


public class Source {
	
	public static double deltaTime = 0.01;
	public final static int FPS = 60;

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
		button1.scale = 0;
		button1.AlignXWith(title);
		button1.MoveToBottomOf(idk1, 10);

		UIEntityGroup group = new UIEntityGroup();
		group.entities.add(title);
		group.entities.add(idk1);
		group.entities.add(button1);	
		group.setX(-200);
		group.setY(-200);
		
		
		
		Animator animatorX = new Animator(x -> group.setCentralX((int)x), () -> group.getCentralX());
		Animator animatorY = new Animator(y -> group.setCentralY((int)y), () -> group.getCentralY());
		Animator animatorS = new Animator(s -> button1.setScale(s), () -> button1.getScale());
		animatorX.setGoal(Window.main.getWidth() / 2);
		animatorY.setGoal(150);
		animatorS.setGoal(1);
		
		animatorX.active = true;
		animatorY.active = true;
		animatorS.active = true;
		
		button1.onClick = () -> {
			animatorS.setGoal(-0.1);
			};
		
		long lastTime = System.currentTimeMillis();
		
		while (Window.main.isActive())
		{
			Thread.sleep(1000 / FPS);
			
			long currentTime = System.currentTimeMillis();
			
			deltaTime = (currentTime - lastTime) / 1000.0;
			
			lastTime = currentTime;
			
			UIEntity.Update();
			
			Animator.UpdateAll();
			
			button1.AlignXWith(title);
			
			Window.main.repaint();
		}
	}

}
