package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Cactus implements Cloneable {
	ImageView image;
	Image im;
	Rectangle hitBox;
	double posX;
	double posY;
	Pane root;
	int startX;
	int startY;
	int sizex;
	int sizey;
	public static final int hitboxOffset = 12;

	public Cactus(Image im, Pane root, int offsetX, int offsetY, int sizeX, int sizeY, boolean shown) {
		this.im = im;
		image = new ImageView(im);
		startX = offsetX;
		startY = offsetY;
		this.root = root;
		sizex = sizeX;
		sizey = sizeY;
		hitBox = new Rectangle(sizeX, sizeY-hitboxOffset);
		if (shown) {
//			root.getChildren().add(hitBox);
			root.getChildren().add(image);
		}
		move(offsetX, offsetY);
	}

	public void move(double dX, double dY) {
		moveTo(posX + dX, posY + dY);
	}

	public void moveTo(double X, double Y) {
		hitBox.setTranslateX(X);
		hitBox.setTranslateY(Y+hitboxOffset);
		image.setTranslateX(X);
		image.setTranslateY(Y);
		posX = X;
		posY = Y;
	}

	public Cactus copy() {
		return new Cactus(im, root, startX, startY+200, sizex, sizey,true);
	}
}
