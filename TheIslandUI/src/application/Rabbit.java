package application;

import java.io.FileInputStream;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class Rabbit extends Herbivore {

	private static int counter = 0;
	private int id = counter++;



	public Rabbit(double x, double y, String species, int energy, String getImage, int id, int thirstLevel) {
		super(x, y, species, getImage, energy, 0);
		this.id = id;
		setSpecies("Rabbit");
		setEnergy(15);
	}

	public Rabbit(double x, double y, String getImage, int energy, int thirstLevel) {
		super(x, y, getImage, 0);
		setEnergy(15);
	}



	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Rabbit.counter = counter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
