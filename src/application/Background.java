package application;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Background {
	public static ArrayList<Cactus> obsticles = new ArrayList<>();
	public static ArrayList<Cactus> possibleObsticles = new ArrayList<>();

	public static double velX = -5;
	public static double accX = 0.001;
	public static final int floor = 235;
	
	
	public static Rectangle Floor = new Rectangle(800, 1);
	public static Rectangle FloorLight = new Rectangle(800, 3);
	public static Pane root;

	public static void setUp(Pane rt) {
		root = rt;
		Floor.setFill(Color.rgb(83, 83, 83));
		Floor.setTranslateY(floor+1);
		FloorLight.setFill(Color.rgb(200, 200, 200));
		FloorLight.setTranslateY(floor);
		root.getChildren().addAll(FloorLight,Floor);
		for (int i = 0; i < obsticles.size(); i++) {
			obsticles.get(i).move(rt.getWidth() + 100, 200);
		}
	}

	public static boolean collides(Rectangle dino) {
		for (int i = 0; i < obsticles.size(); i++) {
			if (dino.getBoundsInParent().intersects(obsticles.get(i).hitBox.getBoundsInParent())) {
				return true;
			}
		}
		return false;
	}

	public static void move() {
		velX -= accX;
		for (int i = 0; i < obsticles.size(); i++) {
			if (obsticles.get(i).hitBox.getTranslateX() < -100) {
				root.getChildren().remove(obsticles.get(i).image);
				obsticles.remove(obsticles.get(i));
				i--;
			} else {
				obsticles.get(i).move(velX, 0);
			}

		}
	}

	public static void addObsticle() {
		int o = (int) (Math.random() * possibleObsticles.size());
		Cactus temp = possibleObsticles.get(o).copy();
		obsticles.add(temp);
	}
	
	public static void reset(){
		velX=-5;
		for(Cactus a: obsticles){
			root.getChildren().remove(a.image);
		}
		obsticles.clear();
	}

}
