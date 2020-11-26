package application;

import java.util.List;

public class Herbivore extends Animal {

	
	public Herbivore(double x, double y, String species, String getImage, int energy, int thirstLevel) {
		super(x, y, species,  getImage, energy, "Herbivore", 0);
		}
	


	public Herbivore(double x, double y, String getImage, int thirstLevel) {
		super(x, y, getImage,0);
	}

	
	
	}
	
	

