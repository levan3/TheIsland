package application;

import javafx.animation.PathTransition;


import javafx.scene.image.ImageView;
import javafx.scene.shape.Polyline;
import javafx.util.Duration;

public class Fox extends Omnivore {
	
	private static int counter = 0;
	private int id = counter++;
	
	public Fox(double x, double y, String getImage,int energy, int thirstLevel) {
	super(x, y, getImage, 0);
	setEnergy(15);
	
	
	
	
	}
}
