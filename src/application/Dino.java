package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Dino {

	static double gravity = 0.4;
	static double jumpSpeed = -5;
	static final int ground = 195;
	static boolean onFloor = true;

	static double yvel = 0;
	static double xvel = 0;

	static ImageView BackFoot, FrontFoot, Start;
	static Rectangle hitBox = new Rectangle(40-8, 43-8);
	static Pane root;
	static boolean leftFoot = false;
	static double posX = 0;
	static double posY = 0;

	public static void setUp(Pane root) {
		Dino.root = root;
		BackFoot = Main.BackFoot;
		FrontFoot = Main.FrontFoot;
		Start = Main.Start;
//		root.getChildren().add(hitBox);
		root.getChildren().addAll(BackFoot, FrontFoot, Start);

		BackFoot.setVisible(false);
		FrontFoot.setVisible(false);
		Start.setVisible(true);
		hitBox.setFill(Color.BLACK);

		moveTo(200, ground);
	}

	public static void changeImage() {
		if (leftFoot) {
			BackFoot.setVisible(true);
			FrontFoot.setVisible(false);
			Start.setVisible(false);
		} else {
			BackFoot.setVisible(false);
			FrontFoot.setVisible(true);
			Start.setVisible(false);
		}
		leftFoot = !leftFoot;
	}

	public static void moveTo(double X, double Y) {
		Y = Math.min(Y, ground);
		if(Y==ground){
			onFloor=true;
		}
		FrontFoot.setTranslateX(X);
		BackFoot.setTranslateX(X);
		Start.setTranslateX(X);
		FrontFoot.setTranslateY(Y);
		BackFoot.setTranslateY(Y);
		Start.setTranslateY(Y);
		hitBox.setTranslateX(X);
		hitBox.setTranslateY(Y);
		posX = X;
		posY = Y;
	}

	public static void move(double dX, double dY) {
		moveTo(posX + dX, posY + dY);
	}

	public static void jump() {
		if (posY == ground) {
			yvel = jumpSpeed;
			onFloor=false;
		}

	}

	public static void update() {
		yvel += gravity;
		if (posY == ground) {
			yvel = Math.min(yvel, 0);
		}
		
		move(xvel, yvel);
	}
}
