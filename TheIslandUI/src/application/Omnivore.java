package application;

public class Omnivore extends Animal {

	public Omnivore(double x, double y, String species, String getImage, int energy, int thirstLevel) {
		super(x, y, species, getImage, energy, "Omnivore", 0);
		}
	


	public Omnivore(double x, double y, String getImage, int thirstLevel) {
		super(x, y, getImage,0);
	}

	
	
}
