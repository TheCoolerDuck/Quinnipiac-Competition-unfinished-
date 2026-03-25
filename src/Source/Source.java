package Source;

import java.awt.Font;
import java.io.IOException;

import Animation.Animator;
import BackEnd.ImageManager;
import BackEnd.Window;
import UI.*;


public class Source {
	
	public static double deltaTime = 0.01;
	public static double timeScale = 1;
	public final static int FPS = 60;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Creates Window and all other backend components
		new Window();

		LoadBuildWorld();

		long lastTime = System.currentTimeMillis();
		
		while (Window.main.isActive())
		{
			Thread.sleep(1000 / FPS);
			
			long currentTime = System.currentTimeMillis();
			
			//delta time is time between frames, nice for animation/physics
			deltaTime = (currentTime - lastTime) / 1000.0 * timeScale;
			
			lastTime = currentTime;
			
			UIEntity.UpdateAll();
			
			Animator.UpdateAll();
			
			Window.main.repaint();
		}
	}

	private static void LoadBuildWorld() throws IOException {
		LoadImages();

		ImageUIEntity tree = new ImageUIEntity("Tree");

		ButtonUIEntity resume = new ButtonUIEntity("F", "F", "F");
		ButtonUIEntity settings = new ButtonUIEntity("H", "H", "H");
		ButtonUIEntity quit = new ButtonUIEntity("G", "G", "G");

		StackUIEntity masterPanel = new StackUIEntity();;
		Animator masterPanel_X = new Animator(x -> masterPanel.setCentralX((int) x), -500);
		Animator masterPanel_Y = new Animator(y -> masterPanel.setCentralY((int) y), -500);

		BackBoardUIEntity masterPanelBoard = new BackBoardUIEntity(masterPanel, "PanelACorner", "PanelATop", "PanelASide", "PanelACenter");

		ButtonUIEntity exitButton = new ButtonUIEntity("X", "X", "X");
		Animator exitButton_Scale = new Animator(exitButton::setScaleGlobal);

		//Tree
		{
			tree.scaleGlobal = 0.790123457;
		}

		//Resume
		{
			double scale = 2;
			Animator animator = new Animator(resume::setScaleGlobal, scale);

			resume.onClick = () -> {
				exitButton_Scale.setGoal(0.5);
				masterPanel_X.setGoal(-500);
				masterPanel_Y.setGoal(-500);
			};

			resume.onHoverEnter = () -> {
				if (animator.getGoal() == scale)
					animator.setGoal(scale * 1.1);
			};
			resume.onHoverExit = () -> {
				if (animator.getGoal() == scale * 1.1)
					animator.setGoal(scale);
			};
		}

		//Settings
		{
			double scale = 2;

			Animator animator = new Animator(settings::setScaleGlobal, scale);

			settings.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);

				System.out.println("Not Implemented LOL");
			};

			settings.onHoverEnter = () -> {
				if (animator.getGoal() == scale)
					animator.setGoal(scale * 1.1);
			};
			settings.onHoverExit = () -> {
				if (animator.getGoal() == scale * 1.1)
					animator.setGoal(scale);
			};
		}

		//Quit
		{
			double scale = 2;

			Animator animator = new Animator(quit::setScaleGlobal, scale);

			quit.onClick = () -> {
				Window.main.dispose();
			};

			quit.onHoverEnter = () -> {
				if (animator.getGoal() == scale)
					animator.setGoal(scale * 1.1);
			};
			quit.onHoverExit = () -> {
				if (animator.getGoal() == scale * 1.1)
					animator.setGoal(scale);
			};
		}

		//Master Panel
		{
			masterPanel.children.add(resume);
			masterPanel.children.add(settings);
			masterPanel.children.add(quit);
			masterPanel.spacingFactor = 5;
		}

		//Master Panel Board
		{
			masterPanelBoard.boarder = 100;
			masterPanelBoard.pieceScale = 0.1;
			masterPanelBoard.scaleType = BackBoardUIEntity.CONSTANT_N_REPEAT;
			masterPanelBoard.topNumberPieces = 3;
			masterPanelBoard.sideNumberPieces = 3;
			masterPanelBoard.backTopNumberPieces = 1;
			masterPanelBoard.backSideNumberPieces = 1;
		}

		//Exit Button
		{
			double scale = 0.5;
			exitButton.scaleGlobal = 0;

			exitButton.onUpdate = () ->
			{
				exitButton.setCentralX(50);
				exitButton.setCentralY(50);
			};

			exitButton_Scale.setGoal(scale);
			exitButton_Scale.setNaturalFrequency(3);

			exitButton.onClick = () -> {
				masterPanel_X.setGoal(Window.main.getWidth() / 2.0);
				masterPanel_Y.setGoal(Window.main.getHeight() / 2.0);
				exitButton_Scale.setGoal(0);
			};

			exitButton.onHoverEnter = () -> {
				if (exitButton_Scale.getGoal() == scale)
					exitButton_Scale.setGoal(scale * 1.1);
			};
			exitButton.onHoverExit = () -> {
				if (exitButton_Scale.getGoal() == scale * 1.1)
					exitButton_Scale.setGoal(scale);
			};
		}

		exitButton.drawBefore(masterPanelBoard);
		tree.drawLast();

		Window.main.setVisible(true);
	}

	private static void LoadImages() throws IOException {
		ImageManager.load("src/Assets/UI/TreeBackground/Tree.png", "Tree");

		ImageManager.load("src/Assets/UI/DevArtA.png", "A");
		ImageManager.load("src/Assets/UI/DevArtB.png", "B");
		ImageManager.load("src/Assets/UI/DevArtC.png", "C");
		ImageManager.load("src/Assets/UI/X.png", "X");
		ImageManager.load("src/Assets/UI/DevArtF.png", "F");
		ImageManager.load("src/Assets/UI/DevArtG.png", "G");
		ImageManager.load("src/Assets/UI/DevArtH.png", "H");

		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelACenter.jpg", "PanelACenter");
		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelACorner.png", "PanelACorner");
		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelASide.png", "PanelASide");
		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelATop.png", "PanelATop");
	}
}
