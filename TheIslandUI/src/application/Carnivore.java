package application;

public class Carnivore extends Animal{

	public Carnivore(double x, double y, String species,String getImage, int energy, int thirstLevel) {
		super(x, y, species, getImage, energy, "Carnivore", 0);
		}
	


	public Carnivore(double x, double y, char symbol, String getImage, int thirstLevel) {
		super(x, y,  getImage,0);
	}

	

}

