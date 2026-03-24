package Source;

import java.awt.Font;
import java.io.IOException;

import Animation.Animator;
import BackEnd.ImageManager;
import BackEnd.Window;
import UI.*;


public class Source {
	
	public static double deltaTime = 0.01;
	public final static int FPS = 60;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Creates Window and all other backend components
		new Window();

		//LoadTestWorldB();
		LoadBuildWorld();

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

	private static void LoadTestWorldA() throws IOException {
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

		String buttonImageA = ImageManager.load("src/Assets/UI/DevArtA.png", "A");
		String buttonImageB = ImageManager.load("src/Assets/UI/DevArtB.png", "B");
		String buttonImageC = ImageManager.load("src/Assets/UI/DevArtC.png", "C");

		{
			textA = new TextUIEntity();
			textA.setText("[Insert Name Here]");
			textA.setFont(new Font("Arial", Font.PLAIN, 50 / (Window.halfScreen ? 2 : 1)));
			textA.setWorldY(150);
		}

		{
			textB = new TextUIEntity();
			textB.setText("hello world");
			textB.setFont(new Font("Arial", Font.PLAIN, 30 / (Window.halfScreen ? 2 : 1)));
			textB.AlignXWith(textA);
			textB.MoveToBottomOf(textA, 10);
		}

		{
			buttonA = new ButtonUIEntity("A", "B", "C");
			buttonA.scaleGlobal = 0;
			buttonA.onUpdate = () -> buttonA.AlignXWith(textA);
			buttonA.MoveToBottomOf(textB, 10);

			Animator animator = new Animator(buttonA::setScaleGlobal);
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
			groupA.setWorldX(-300);
			groupA.setWorldY(-300);

			//Animators use runnables which can be passed in as a normal function or a lambda function (seen below)
			//Lambda function structure: (params) -> {(code)};   can be shortend in some cases
			Animator animatorX = new Animator(x -> groupA.setCenterOfMassX((int)x), groupA.getCenterOfMassX());
			Animator animatorY = new Animator(y -> groupA.setCenterOfMassY((int)y), groupA.getCenterOfMassY());

			animatorX.setGoal((double) Window.main.getWidth() / 2);
			animatorY.setGoal(200);

			String boardImageA = ImageManager.load("src/Assets/UI/BoardA.png", "BA");
			String boardImageB = ImageManager.load("src/Assets/UI/BoardB.png", "BB");
			String boardImageC = ImageManager.load("src/Assets/UI/BoardC.png", "BC");
			String boardImageD = ImageManager.load("src/Assets/UI/BoardD.png", "BD");
			BackBoardUIEntity board = new BackBoardUIEntity(groupA, boardImageA, boardImageC, boardImageB, boardImageD);
			board.boarder = 30;
			board.pieceScale = 0.5;
			board.scaleType = BackBoardUIEntity.FLUID_N_REPEAT;
		}


		{
			buttonB = new ButtonUIEntity("A", "B", "C");

			Animator animator = new Animator(buttonB::setScaleGlobal, 0);
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
			buttonC = new ButtonUIEntity("A", "B", "C");

			Animator animator = new Animator(buttonC::setScaleGlobal, 0);
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
			buttonD = new ButtonUIEntity("A", "B", "C");

			Animator animator = new Animator(buttonD::setScaleGlobal, 0);
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
			buttonE = new ButtonUIEntity("A", "B", "C");

			Animator animator = new Animator(buttonE::setScaleGlobal, 0);
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
			buttonF = new ButtonUIEntity("A", "B", "C");

			Animator animator = new Animator(buttonF::setScaleGlobal, 0);
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
			String lockImage = ImageManager.load("src/Assets/UI/DevArtE.png", "lock");
			lockA = new ImageUIEntity("lock");
		}

		{
			String keyImage = ImageManager.load("src/Assets/UI/DevArtD.png", "key");
			keyA = new KeyUIEntity("key", lockA);
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
			stackA.spacingFactor = 0.001;
			stackA.orientation = StackUIEntity.HORIZONTAL;

			String boardImageA = ImageManager.load("src/Assets/UI/BoardA.png", "BA");
			String boardImageB = ImageManager.load("src/Assets/UI/BoardB.png", "BB");
			String boardImageC = ImageManager.load("src/Assets/UI/BoardC.png", "BC");
			String boardImageD = ImageManager.load("src/Assets/UI/BoardD.png", "BD");
			BackBoardUIEntity board = new BackBoardUIEntity(stackA, boardImageA, boardImageC, boardImageB, boardImageD);
			board.boarder = 20;
			board.pieceScale = 0.5;
			board.scaleType = BackBoardUIEntity.CONSTANT_N_REPEAT;
			board.topNumberPieces = 40;
			board.sideNumberPieces = 2;
			board.backTopNumberPieces = 40;
			board.backSideNumberPieces = 2;
		}
	}

	private static void LoadTestWorldB() throws IOException {

		ButtonUIEntity buttonA;
		ButtonUIEntity buttonB;
		ButtonUIEntity buttonC;

		StackUIEntity stackA;

		ImageManager.load("src/Assets/UI/DevArtA.png", "A");
		ImageManager.load("src/Assets/UI/DevArtB.png", "B");
		ImageManager.load("src/Assets/UI/DevArtC.png", "C");

		ImageManager.load("src/Assets/UI/BoardA.png", "BA");
		ImageManager.load("src/Assets/UI/BoardB.png", "BB");
		ImageManager.load("src/Assets/UI/BoardC.png", "BC");
		ImageManager.load("src/Assets/UI/BoardD.png", "BD");

		{
			double scale = 2;
			buttonA = new ButtonUIEntity("A", "B", "C");
			buttonA.scaleGlobal = 0;

			Animator animator = new Animator(buttonA::setScaleGlobal);
			animator.setGoal(scale);

			buttonA.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonA.onHoverEnter = () -> {
				if (animator.getGoal() == scale)
					animator.setGoal(scale * 1.1);
			};
			buttonA.onHoverExit = () -> {
				if (animator.getGoal() == scale * 1.1)
					animator.setGoal(scale);
			};
		}

		{
			double scale = 2;
			buttonB = new ButtonUIEntity("A", "B", "C");
			buttonB.scaleGlobal = 0;

			Animator animator = new Animator(buttonB::setScaleGlobal);
			animator.setGoal(scale);

			buttonB.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonB.onHoverEnter = () -> {
				if (animator.getGoal() == scale)
					animator.setGoal(scale * 1.1);
			};
			buttonB.onHoverExit = () -> {
				if (animator.getGoal() == scale * 1.1)
					animator.setGoal(scale);
			};
		}

		{
			double scale = 2;
			buttonC = new ButtonUIEntity("A", "B", "C");
			buttonC.scaleGlobal = 0;

			Animator animator = new Animator(buttonC::setScaleGlobal);
			animator.setGoal(scale);

			buttonC.onClick = () -> {
				animator.setNaturalFrequency(2);
				animator.setDampingCoefficient(1);
				animator.setGoal(0);
			};

			buttonC.onHoverEnter = () -> {
				if (animator.getGoal() == scale)
					animator.setGoal(scale * 1.1);
			};
			buttonC.onHoverExit = () -> {
				if (animator.getGoal() == scale * 1.1)
					animator.setGoal(scale);
			};
		}

		{
			stackA = new StackUIEntity();
			stackA.children.add(buttonA);
			stackA.children.add(buttonB);
			stackA.children.add(buttonC);
			stackA.spacingFactor = 5;
			stackA.onUpdate = () -> {
				stackA.CenterX();
				stackA.CenterY();
			};

			BackBoardUIEntity board = new BackBoardUIEntity(stackA, "BA", "BC", "BB", "BD");
			board.boarder = 30;
			board.pieceScale = 0.5;
			board.scaleType = BackBoardUIEntity.CONSTANT_N_REPEAT;
			board.topNumberPieces = 13;
			board.sideNumberPieces = 13;
			board.backTopNumberPieces = 13;
			board.backSideNumberPieces = 13;
		}
	}

	private static void LoadBuildWorld() throws IOException {
		ImageManager.load("src/Assets/UI/TreeBackground/Tree.png", "Tree");

		ImageManager.load("src/Assets/UI/DevArtA.png", "A");
		ImageManager.load("src/Assets/UI/DevArtB.png", "B");
		ImageManager.load("src/Assets/UI/DevArtC.png", "C");

		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelACenter.jpg", "PanelACenter");
		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelACorner.png", "PanelACorner");
		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelASide.png", "PanelASide");
		ImageManager.load("src/Assets/UI/BackPanelA/BackPanelATop.png", "PanelATop");

		ImageUIEntity tree;

		ButtonUIEntity buttonA;
		ButtonUIEntity buttonB;
		ButtonUIEntity buttonC;

		StackUIEntity stackA;

		{
			tree = new ImageUIEntity("Tree");

			tree.scaleGlobal = 0.790123457;
		}

		{

			{
				double scale = 2;
				buttonA = new ButtonUIEntity("A", "B", "C");
				buttonA.scaleGlobal = 0;

				Animator animator = new Animator(buttonA::setScaleGlobal);
				animator.setGoal(scale);

				buttonA.onClick = () -> {
					animator.setNaturalFrequency(2);
					animator.setDampingCoefficient(1);
					animator.setGoal(0);
				};

				buttonA.onHoverEnter = () -> {
					if (animator.getGoal() == scale)
						animator.setGoal(scale * 1.1);
				};
				buttonA.onHoverExit = () -> {
					if (animator.getGoal() == scale * 1.1)
						animator.setGoal(scale);
				};
			}

			{
				double scale = 2;
				buttonB = new ButtonUIEntity("A", "B", "C");
				buttonB.scaleGlobal = 0;

				Animator animator = new Animator(buttonB::setScaleGlobal);
				animator.setGoal(scale);

				buttonB.onClick = () -> {
					animator.setNaturalFrequency(2);
					animator.setDampingCoefficient(1);
					animator.setGoal(0);
				};

				buttonB.onHoverEnter = () -> {
					if (animator.getGoal() == scale)
						animator.setGoal(scale * 1.1);
				};
				buttonB.onHoverExit = () -> {
					if (animator.getGoal() == scale * 1.1)
						animator.setGoal(scale);
				};
			}

			{
				double scale = 2;
				buttonC = new ButtonUIEntity("A", "B", "C");
				buttonC.scaleGlobal = 0;

				Animator animator = new Animator(buttonC::setScaleGlobal);
				animator.setGoal(scale);

				buttonC.onClick = () -> {
					animator.setNaturalFrequency(2);
					animator.setDampingCoefficient(1);
					animator.setGoal(0);
				};

				buttonC.onHoverEnter = () -> {
					if (animator.getGoal() == scale)
						animator.setGoal(scale * 1.1);
				};
				buttonC.onHoverExit = () -> {
					if (animator.getGoal() == scale * 1.1)
						animator.setGoal(scale);
				};
			}

			{
				stackA = new StackUIEntity();
				stackA.children.add(buttonA);
				stackA.children.add(buttonB);
				stackA.children.add(buttonC);
				stackA.spacingFactor = 5;
				stackA.onUpdate = () -> {
					stackA.CenterX();
					stackA.CenterY();
				};

				BackBoardUIEntity board = new BackBoardUIEntity(stackA, "PanelACorner", "PanelATop", "PanelASide", "PanelACenter");
				board.boarder = 100;
				board.pieceScale = 0.1;
				board.scaleType = BackBoardUIEntity.CONSTANT_N_REPEAT;
				board.topNumberPieces = 3;
				board.sideNumberPieces = 3;
				board.backTopNumberPieces = 1;
				board.backSideNumberPieces = 1;
			}
		}

		tree.setDrawOrder(Window.main.panel.getComponentCount() - 1);
	}
}
