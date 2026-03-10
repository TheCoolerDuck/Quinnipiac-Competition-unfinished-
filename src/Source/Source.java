package Source;

import java.awt.Font;
import java.io.IOException;

import Animation.Animator;
import UI.*;


public class Source {
	
	public static double deltaTime = 0.01;
	public final static int FPS = 60;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Creates Window and all other backend components
		new Window();

		TextUIEntity textA = new TextUIEntity();
		textA.setText("[Insert Name Here]");
		textA.setFont(new Font("Arial", Font.PLAIN, 50 / (Window.halfScreen ? 2 : 1)));
		textA.setY(150);

		TextUIEntity textB = new TextUIEntity();
		textB.setText("hello world");
		textB.setFont(new Font("Arial", Font.PLAIN, 30 / (Window.halfScreen ? 2 : 1)));
		textB.AlignXWith(textA);
		textB.MoveToBottomOf(textA, 10);
		
		ButtonUIEntity buttonA = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");
		buttonA.scaleGlobal = 0;
		buttonA.onUpdate = () -> buttonA.AlignXWith(textA);
		buttonA.MoveToBottomOf(textB, 10);

		UIEntityGroup groupA = new UIEntityGroup();
		groupA.entities.add(textA);
		groupA.entities.add(textB);
		groupA.entities.add(buttonA);
		groupA.setX(-300);
		groupA.setY(-300);
		
		Lock lockA = new Lock("src/Assets/UI/DevArtE.png");
		lockA.scaleGlobal = 1.5;
		lockA.CenterX();
		lockA.CenterY();

		Key keyA = new Key("src/Assets/UI/DevArtD.png", lockA);
		keyA.setX(500);
		keyA.setY(500);
		//have to set goals or else it runs to 0,0
		keyA.setGoalX(500);
		keyA.setGoalY(500);
		keyA.scaleGlobal = 1;
		keyA.setDrawOrder(0);
		
		//Animators use runnables which can be passed in as a normal function or a lambda function (seen below)
		//Lambda function structure: (params) -> {(code)};   can be shortend in some cases
		Animator animatorX = new Animator(x -> groupA.setCenterOfMassX((int)x), groupA::getCenterOfMassX);
		Animator animatorY = new Animator(y -> groupA.setCenterOfMassY((int)y), groupA::getCenterOfMassY);
		Animator animatorS = new Animator(buttonA::setScaleGlobal, buttonA::getScaleGlobal);

		buttonA.onClick = () -> {
			//increases speed
			animatorS.setNaturalFrequency(2);
			//no occilation with damp-ceoff >= 1
			animatorS.setDampingCoefficient(1);
			animatorS.setGoal(0);
			//when goal is reached flys off
			animatorS.onGoalReached = () -> {
				animatorX.setNaturalFrequency(0.75);
				animatorX.setGoal(-300);
			};};

		//this gives the button the growth/strinkage effect
		buttonA.onHoverEnter = () -> {
			if (animatorS.getGoal() == 2)
				animatorS.setGoal(2.2);
		};
		buttonA.onHoverExit = () -> {
			if (animatorS.getGoal() == 2.2)
				animatorS.setGoal(2);
		};
		
		animatorX.setGoal((double) Window.main.getWidth() / 2);
		animatorY.setGoal(150);
		animatorS.setGoal(2);
		
		long lastTime = System.currentTimeMillis();
		
		while (Window.main.isActive())
		{
			Thread.sleep(1000 / FPS);
			
			long currentTime = System.currentTimeMillis();
			
			//delta time is time between frames, nice for animation/physics
			deltaTime = (currentTime - lastTime) / 1000.0;
			
			lastTime = currentTime;
			
			UIEntity.UpdateAll();
			
			Animator.UpdateAll();
			
			Window.main.repaint();
		}
	}
}
