package application;

import javafx.animation.AnimationTimer;
//Dino Chrome Game	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application {
	static Image BackFootImage, FrontFootImage, StartImage,SmallImage,BigImage,DoubleSmallImage,QuadWideImage;
	static ImageView BackFoot, FrontFoot, Start,Small,Big,DoubleSmall,QuadWide;
	static long timerCounter = 0;
	static int nextCactus;
	static int points = 0;
	static int highScore = 0;
	static boolean alive = true;
	AnimationTimer timer;

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root, 800, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			BackFootImage = new Image(getClass().getResource("BackFoot.png").toExternalForm(), 40, 43, false, false);
			FrontFootImage = new Image(getClass().getResource("FrontFoot.png").toExternalForm(), 40, 43, false, false);
			StartImage = new Image(getClass().getResource("Start.png").toExternalForm(), 40, 43, false, false);
			SmallImage= new Image(getClass().getResource("Small.png").toExternalForm(), 23, 47, false, false);
			BigImage= new Image(getClass().getResource("Big.png").toExternalForm(), 33, 69, false, false);
			DoubleSmallImage= new Image(getClass().getResource("DoubleSmall.png").toExternalForm(), 46, 47, false, false);
			QuadWideImage= new Image(getClass().getResource("QuadWide.png").toExternalForm(), 103, 68, false, false);
			
			Background.possibleObsticles.add(new Cactus(SmallImage,root,(int) (root.getWidth()+100),0,23,47,false));
			Background.possibleObsticles.add(new Cactus(BigImage,root,(int) (root.getWidth()+100),-21,33,69,false));
			Background.possibleObsticles.add(new Cactus(DoubleSmallImage,root,(int) (root.getWidth()+100),0,46,47,false));
			Background.possibleObsticles.add(new Cactus(QuadWideImage,root,(int) (root.getWidth()+100),-21,103,68,false));
			BackFoot = new ImageView(BackFootImage);
			FrontFoot = new ImageView(FrontFootImage);
			Start = new ImageView(StartImage);
			Label score = new Label();
			Label highscore = new Label();
			root.getChildren().addAll(score, highscore);
			highscore.setTranslateY(20);
			
			
			Dino.setUp(root);
			Background.setUp(root);
			long start = System.nanoTime();

			timer = new AnimationTimer() {// what constantly runs
				@Override
				public void handle(long now) {
					timerCounter++;
					Dino.gravity=-0.08*Background.velX;
					Dino.jumpSpeed=-Math.sqrt(Dino.gravity*225);
					long elapsedTime = now - start;
					double seconds = elapsedTime / 1000000000.0;
					Background.move();
					if (timerCounter % 10 == 0 && Dino.onFloor) {
						Dino.changeImage();
					}else if(!Dino.onFloor){
						Dino.BackFoot.setVisible(false);
						Dino.FrontFoot.setVisible(false);
						Dino.Start.setVisible(true);
					}
					if(Background.collides(Dino.hitBox)){
						timer.stop();
					}
					Dino.update();
					if(nextCactus<=0){
						nextCactus+=Math.random()*300+350;
						Background.addObsticle();
					}
					points-=Background.velX;
					score.setText("score: "+ points);
					highScore = Math.max(points, highScore);
					highscore.setText("highscore: "+ Math.max(points, highScore));
					nextCactus+=Background.velX;

				}
			};
			timer.start();
			
			scene.setOnKeyPressed(e -> {
				switch (e.getCode()) {
				case UP:
					Dino.jump();
					break;
				
				case R:
					points = 0;
					Dino.yvel=0;
					Background.reset();
					timer.start();
				}
			});
			

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
