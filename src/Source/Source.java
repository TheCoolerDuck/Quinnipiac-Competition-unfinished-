package Source;

import java.awt.Font;
import java.io.IOException;

import Animation.Animator;
import BackEnd.Window;
import UI.*;


public class Source {
	
	public static double deltaTime = 0.01;
	public final static int FPS = 60;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Creates Window and all other backend components
		new Window();

		TextUIEntity textA;
		TextUIEntity textB;

		ButtonUIEntity buttonA;

		UIEntityGroup groupA;

		ButtonUIEntity buttonB;
		ButtonUIEntity buttonC;
		ButtonUIEntity buttonD;
		ButtonUIEntity buttonE;
		ButtonUIEntity buttonF;

		TextUIEntity textC;
		TextUIEntity textD;
		TextUIEntity textE;

		ImageUIEntity lockA;
		KeyUIEntity keyA;

		StackUIEntity stackA;



		{
			textA = new TextUIEntity();
			textA.setText("[Insert Name Here]");
			textA.setFont(new Font("Arial", Font.PLAIN, 50 / (Window.halfScreen ? 2 : 1)));
			textA.setY(150);
		}

		{
			textB = new TextUIEntity();
			textB.setText("hello world");
			textB.setFont(new Font("Arial", Font.PLAIN, 30 / (Window.halfScreen ? 2 : 1)));
			textB.AlignXWith(textA);
			textB.MoveToBottomOf(textA, 10);
		}

		{
			buttonA = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");
			buttonA.scaleGlobal = 0;
			buttonA.onUpdate = () -> buttonA.AlignXWith(textA);
			buttonA.MoveToBottomOf(textB, 10);

			Animator animator = new Animator(buttonA::setScaleGlobal, buttonA::getScaleGlobal);
			animator.setGoal(2);

			buttonA.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonA.onHoverEnter = () -> {
				if (animator.getGoal() == 2)
					animator.setGoal(2.2);
			};
			buttonA.onHoverExit = () -> {
				if (animator.getGoal() == 2.2)
					animator.setGoal(2);
			};
		}

		{
			groupA = new UIEntityGroup();
			groupA.children.add(textA);
			groupA.children.add(textB);
			groupA.children.add(buttonA);
			groupA.setX(-300);
			groupA.setY(-300);

			//Animators use runnables which can be passed in as a normal function or a lambda function (seen below)
			//Lambda function structure: (params) -> {(code)};   can be shortend in some cases
			Animator animatorX = new Animator(x -> groupA.setCenterOfMassX((int)x), groupA::getCenterOfMassX);
			Animator animatorY = new Animator(y -> groupA.setCenterOfMassY((int)y), groupA::getCenterOfMassY);

			animatorX.setGoal((double) Window.main.getWidth() / 2);
			animatorY.setGoal(150);
		}

		{
			buttonB = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");

			Animator animator = new Animator(buttonB::setScaleGlobal, buttonB::getScaleGlobal);
			animator.setGoal(1);

			buttonB.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonB.onHoverEnter = () -> {
				if (animator.getGoal() == 1)
					animator.setGoal(1.1);
			};
			buttonB.onHoverExit = () -> {
				if (animator.getGoal() == 1.1)
					animator.setGoal(1);
			};
		}

		{
			buttonC = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");

			Animator animator = new Animator(buttonC::setScaleGlobal, buttonC::getScaleGlobal);
			animator.setGoal(1);

			buttonC.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonC.onHoverEnter = () -> {
				if (animator.getGoal() == 1)
					animator.setGoal(1.1);
			};
			buttonC.onHoverExit = () -> {
				if (animator.getGoal() == 1.1)
					animator.setGoal(1);
			};
		}

		{
			buttonD = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");

			Animator animator = new Animator(buttonD::setScaleGlobal, buttonD::getScaleGlobal);
			animator.setGoal(1);

			buttonD.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonD.onHoverEnter = () -> {
				if (animator.getGoal() == 1)
					animator.setGoal(1.1);
			};
			buttonD.onHoverExit = () -> {
				if (animator.getGoal() == 1.1)
					animator.setGoal(1);
			};
		}

		{
			buttonE = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");

			Animator animator = new Animator(buttonE::setScaleGlobal, buttonE::getScaleGlobal);
			animator.setGoal(1);

			buttonE.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonE.onHoverEnter = () -> {
				if (animator.getGoal() == 1)
					animator.setGoal(1.1);
			};
			buttonE.onHoverExit = () -> {
				if (animator.getGoal() == 1.1)
					animator.setGoal(1);
			};
		}

		{
			buttonF = new ButtonUIEntity("src/Assets/UI/DevArtA.png", "src/Assets/UI/DevArtB.png", "src/Assets/UI/DevArtC.png");

			Animator animator = new Animator(buttonF::setScaleGlobal, buttonF::getScaleGlobal);
			animator.setGoal(1);

			buttonF.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonF.onHoverEnter = () -> {
				if (animator.getGoal() == 1)
					animator.setGoal(1.1);
			};
			buttonF.onHoverExit = () -> {
				if (animator.getGoal() == 1.1)
					animator.setGoal(1);
			};
		}

		{
			textC = new TextUIEntity();
			textC.setText("I say things :)");
			textC.setFont(new Font("Arial", Font.PLAIN, 30 / (Window.halfScreen ? 2 : 1)));
		}

		{
			textD = new TextUIEntity();
			textD.setText("I am big");
			textD.setFont(new Font("Arial", Font.PLAIN, 70 / (Window.halfScreen ? 2 : 1)));
		}

		{
			textE = new TextUIEntity();
			textE.setText("I am small");
			textE.setFont(new Font("Arial", Font.PLAIN, 10 / (Window.halfScreen ? 2 : 1)));
		}

		{
			lockA = new ImageUIEntity("src/Assets/UI/DevArtE.png");
		}

		{
			keyA = new KeyUIEntity("src/Assets/UI/DevArtD.png", lockA);
			keyA.scaleGlobal = 0.75;
			keyA.setDrawOrder(0);
			//keyA.onLock = () ->
		}

		{
			stackA = new StackUIEntity();
			stackA.children.add(buttonB);
			stackA.children.add(buttonC);
			stackA.children.add(textC);
			stackA.children.add(buttonD);
			stackA.children.add(textD);
			stackA.children.add(buttonE);
			stackA.children.add(lockA);
			stackA.children.add(textE);
			stackA.children.add(buttonF);
			stackA.onUpdate = () -> {
				stackA.CenterX();
				stackA.CenterY();
			};
			stackA.spacingPerArea = 0.001;
			stackA.orientation = StackUIEntity.HORIZONTAL;
		}

		
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
