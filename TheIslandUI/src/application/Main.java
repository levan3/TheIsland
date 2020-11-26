package application;

import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	private ArrayList<Thing> things = new ArrayList<>();

	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;

	private static final int IMAGE_WIDTH = 40;
	private static final int IMAGE_HEIGHT = 40;

	int r;
	double dy = 1;
	double dx = 1;

	GridPane grid;

	Timeline timeline;

	public Main() {

	}

	public void start(Stage primaryStage) {

		primaryStage.setTitle("The Island");

		// create border pane container
		
		BorderPane border = new BorderPane();

		//create a horizontal box 
		
		HBox hbox = new HBox();

		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #ADFF2F;");

		//set hbox as top pane
		border.setTop(hbox);

		//set a grid as center
		this.grid = addGridPane();

		border.setCenter(grid);

		//set another hbox as Bottom Pane
		border.setBottom(addBottomPane());

		Scene scene = new Scene(border, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		// background image

		try {

			FileInputStream input = new FileInputStream("Resources/background.jpg");

			Image imagebg = new Image(input);

			BackgroundImage backgroundimage = new BackgroundImage(imagebg, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

			// create Background
			Background background = new Background(backgroundimage);
			grid.setBackground(background);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			timeline = new Timeline();

			for (Thing thing : things) {

				// add animals, plants to your view groups

				ImageView image = getImage(thing);
				
				grid.getChildren().add(image);

				// Build the animation for animals

				if (thing instanceof Animal) {
					KeyFrame frame = getFrame(image, (Animal) thing);
					//create KeyFrame using getFrame helper method
					// cast Animal to object so only Animal move
					timeline.getKeyFrames().add(frame); // add all keyframe to timeline
				}

				// Moving plants so newly added plant do not sit on top of one another
				else if(thing instanceof Plant) {
					KeyFrame frame = getFrame(image, (Plant) thing); 
					timeline.getKeyFrames().add(frame);
				}
				
			}

			timeline.setCycleCount(javafx.animation.Animation.INDEFINITE);
			
			//adding buttons

			Button buttonMove = new Button("Start Moving");
			buttonMove.setPrefSize(100, 20);
			buttonMove.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> timeline.play());

			Button btnStop = new Button("Stop Moving");
			btnStop.setPrefSize(100, 20);
			btnStop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> timeline.pause());

			Button addRabbit = new Button("Add Rabbit");
			addRabbit.setPrefSize(100, 20);
			addRabbit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addRabbit());

			Button addFox = new Button("Add Fox");
			addFox.setPrefSize(100, 20);
			addFox.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addFox());

			Button addGrass = new Button("Add Grass");
			addGrass.setPrefSize(100, 20);
			addGrass.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> addGrass());
			
			//Add buttons onto hbox on top pane
			hbox.getChildren().addAll(buttonMove, btnStop, addRabbit, addFox, addGrass);
			
			//style center grid
			grid.setStyle("-fx-background-image:url(\"file:Resources/background.png\");");

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	//creating helper method to view image
	
	private ImageView getImage(Thing thing) { 
		try {

			FileInputStream input = new FileInputStream(thing.getGetImage()); //call image path from super class
			
			Image image = new Image(input); //create new image
			
			ImageView imageView = new ImageView(image); // create imageview to view image
			imageView.setX(thing.getX());
			imageView.setY(thing.getY()); // get X Y location

			imageView.setFitHeight(IMAGE_HEIGHT); //set img size
			imageView.setFitWidth(IMAGE_WIDTH);

			if (thing instanceof Fox) { //if img is a fox, set size = 60
				int IMAGE_WIDTH = 60;
				int IMAGE_HEIGHT = 60;

				imageView.setFitHeight(IMAGE_HEIGHT);
				imageView.setFitWidth(IMAGE_WIDTH);
			}
			
			thing.setImageView(imageView); //set Image View for each object created

			return imageView;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private KeyFrame getFrame(ImageView iv, Thing thing) { //getframe method to create frame

		KeyFrame frame = new KeyFrame(Duration.millis(30), e -> {

			Bounds boundsInScene = iv.localToScene(iv.getBoundsInLocal()); //get bound, location of each item

			int dy = 0;

			int dx = 0;

			int r = ((Animal) thing).getRandomDir(); //move animal in random direction 

			if (r == 1) {  //north

				dy = -1;

				dx = 0;

			} else if (r == 2) { //north east

				dy = -1;

				dx = 1;

			} else if (r == 3) { //south

				dx = 1;

				dy = 0;

			} else if (r == 4) { //south east

				dy = 1;

				dx = 1;

			} else if (r == 5) { //south

				dx = 0;

				dy = 1;

			} else if (r == 6) { //south west

				dx = -1;

				dy = 1;

			} else if (r == 7) { //west

				dy = 0;

				dx = -1;

			} else if (r == 8) { //north west

				dy = -1;

				dx = -1;

			}

			if (boundsInScene.getMinY() <= 50 && (r == 8 || r == 1 || r == 2)) {

				((Animal) thing).setRandomDir((int) (Math.random() * 7) + 1); //animal cannot go over top pane

			} else if (boundsInScene.getMinX() >= 550 && (r == 2 || r == 3 || r == 4)) {

				((Animal) thing).setRandomDir((int) (Math.random() * 7) + 1); // animal cannot go over right pane

			} else if (boundsInScene.getMinY() >= 510 && (r == 4 || r == 5 || r == 6)) {

				((Animal) thing).setRandomDir((int) (Math.random() * 7) + 1); //cannot go top pane

			} else if (boundsInScene.getMinX() < 50 && (r == 6 || r == 7 || r == 8)) {

				((Animal) thing).setRandomDir((int) (Math.random() * 7) + 1); //cannot go left pane

			}

			thing.setY(boundsInScene.getMinY());
			thing.setX(boundsInScene.getMinX()); 

			//Check collision
			
			Bounds b1 = iv.getBoundsInParent();
			
			//for (Thing t : things) {
			

			for (int i = 0; i < things.size(); i++) {
				
				Bounds b2 = things.get(i).getImageView().getBoundsInParent();
				
				// if 2 images intersects, 1 is a rabbit & 1 is a Fox
				if (b1.intersects(b2) && thing instanceof Rabbit && things.get(i) instanceof Fox) {
					
					grid.getChildren().remove(thing.getImageView()); //delete rabbit
					things.remove(thing);
				}
				
				else if (b1.intersects(b2) && thing instanceof Rabbit && things.get(i) instanceof Plant) {
					
					// if 1 is rabbit & 1 is plant
					grid.getChildren().remove(things.get(i).getImageView()); //delete plant
					things.remove(things.get(i));
					
				}
			}

//			for (int i = 0; i < things.size(); i++) {
//
//				if (things.get(i) instanceof Rabbit & animal instanceof Fox) {
//					if (things.get(i).getX() < boundsInScene.getMinX()
//							&& things.get(i).getX() + 40 > boundsInScene.getMinX()
//							&& things.get(i).getY() < boundsInScene.getMinY()
//							&& things.get(i).getY() + 40 > boundsInScene.getMinY()) {
//
//						System.out.print("Collision Detected");
//						
//						ImageView image = getImage(things.get(i));
//						grid.getChildren().remove(image);
//						things.remove(i);
//				
//					}
//				}
//
//			}
			
			iv.setTranslateX(iv.getTranslateX() + dx);
			iv.setTranslateY(iv.getTranslateY() + dy);
		}

//           	for(Thing thing2 : things)
//           		if (thing2 instanceof Rabbit)
//			{
//				if(thing2.getX() < boundsInScene.getMinX() &&  thing2.getX()+40 > boundsInScene.getMinX() &&
//						thing2.getY() < boundsInScene.getMinY() && thing2.getY()+40 > boundsInScene.getMinY())
//				{
//				
//				System.out.print("Collision Detected");
//				
//			}
//			}

		);

		return frame;

	}

	private void checkCollision() {
		System.out.println("Size = " + things.size());

		for (int i = 0; i < things.size(); i++) {

			for (int j = i + 1; j < things.size(); j++) {

				ImageView image1 = getImage(things.get(i));
				ImageView image2 = getImage(things.get(j));

				if (image1.getBoundsInParent().intersects(image2.getBoundsInParent())) {

					if (things.get(i) instanceof Rabbit && things.get(j) instanceof Fox) {
						things.remove(i);
						System.out.println("i ate j");

					} else if (things.get(j) instanceof Rabbit && things.get(i) instanceof Fox) {
						things.remove(j);
						System.out.println("j ate i");

					} else
						System.out.println("Just rabbits");

				}

			}

		}
	}

	public void addIsland() {

		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * WINDOW_WIDTH);
			int y = (int) (Math.random() * WINDOW_HEIGHT);
			int energy = 15;
			int thirstLevel = 0;
			String getImage = "Resources/rabbit.png";
			if (Math.random() < 0.5) {

				things.add(new Rabbit(x, y, getImage, energy, thirstLevel));

			}
		}

		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * WINDOW_WIDTH);
			int y = (int) (Math.random() * WINDOW_HEIGHT);
			int size = (int) (Math.random() * 10);
			String getImage = "Resources/grass.png";
			if (Math.random() < 0.5) {

				things.add(new Grass(x, y, getImage, size));

			}
		}

		for (int i = 0; i < 10; i++) {
			int x = (int) (Math.random() * WINDOW_WIDTH);
			int y = (int) (Math.random() * WINDOW_HEIGHT);
			int energy = 30;
			int thirstLevel = 0;
			String getImage = "Resources/fox.png";
			if (Math.random() < 0.5) {

				things.add(new Fox(x, y, getImage, energy, thirstLevel));

			}
		}
	}

	public void addRabbit() {

		int x = (int) (Math.random() * WINDOW_WIDTH);
		int y = (int) (Math.random() * WINDOW_HEIGHT);
		int energy = 15;
		int thirstLevel = 0;
		String getImage = "Resources/rabbit.png";

		Rabbit rabbit = new Rabbit(x, y, getImage, energy, thirstLevel);

		ImageView image = getImage(rabbit);
		grid.getChildren().add(image);

//		grid.add(image,x,y);
		things.add(rabbit);

		// Build the animation for animals

		KeyFrame frame = getFrame(image, rabbit); // cast Rabbit to object so only Rabbit move
		timeline.getKeyFrames().add(frame); // add all keyframe to timeline
	}

	public void addFox() {

		int x = (int) (Math.random() * WINDOW_WIDTH);
		int y = (int) (Math.random() * WINDOW_HEIGHT);
		int energy = 30;
		int thirstLevel = 0;
		String getImage = "Resources/fox.png";

		Fox fox = new Fox(x, y, getImage, energy, thirstLevel);

		ImageView image = getImage(fox);
		grid.getChildren().add(image);

		things.add(fox);
//		grid.add(image,3,4);
		// Build the animation for animals

		KeyFrame frame = getFrame(image, fox); // cast Rabbit to object so only Rabbit move
		timeline.getKeyFrames().add(frame); // add all keyframe to timeline
	}

	public void addGrass() {

		//int x = (int) (Math.random() * WINDOW_WIDTH);
		//int y = (int) (Math.random() * WINDOW_HEIGHT);
	    int x = (int) (Math.random() * 20) + 1;
		int y = (int) (Math.random() * 20) + 1;
		int size = (int) (Math.random() * 10);
		String getImage = "Resources/grass.png";

		Grass grass = new Grass(x, y, getImage, size);

		ImageView image = getImage(grass);
		//grid.add(image,x,y);

		grid.getChildren().add(image);
		things.add(grass);
		
		dx = (int) (Math.random() * WINDOW_WIDTH);
		dy = (int) (Math.random() * WINDOW_HEIGHT);
		
		image.setTranslateX(image.getTranslateX() + dx);
		image.setTranslateY(image.getTranslateY() + dy);
		

	}

	private HBox addBottomPane() {
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(15);
		hbox.setStyle("-fx-background-color: #ADFF2F;");

		return hbox;
	}

	public GridPane addGridPane() {
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: #20B2AA;");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		return grid;
	}

	public static void main(String[] args) {
		launch(args);
	}
}