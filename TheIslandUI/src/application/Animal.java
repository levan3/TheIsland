package application;

import java.util.List;

public class Animal extends Thing {

	private String species;
	private int energy;
	private String foodStrategy;
	private int thirstLevel;
	private int randomDir;

	public Animal(double x, double y, String species, String getImage, int energy, String foodStrategy, int thirstLevel) {
		super(x, y,  getImage);
		this.species = species;
		this.energy = energy;
		this.foodStrategy = foodStrategy;
		this.thirstLevel = thirstLevel;
		this.randomDir = (int) (Math.random()*7)+1;
	}

	

	public Animal(double x, double y, String getImage, int thirstLevel) {
		super(x, y, getImage);
		this.thirstLevel = thirstLevel;
		this.randomDir = (int) (Math.random()*7)+1;
	}


	public String getSpecies() {
		return species;
	}


	public void setSpecies(String species) {
		this.species = species;
	}


	public int getEnergy() {
		return energy;
	}


	public void setEnergy(int energy) {
		this.energy = energy;
	}


	public String getFoodStrategy() {
		return foodStrategy;
	}


	public void setFoodStrategy(String foodStrategy) {
		this.foodStrategy = foodStrategy;
	}


	public int getThirstLevel() {
		return thirstLevel;
	}


	public void setThirstLevel(int thirstLevel) {
		this.thirstLevel = thirstLevel;
	}



	public int getRandomDir() {
		return randomDir;
	}



	public void setRandomDir(int randomDir) {
		this.randomDir = randomDir;
	}

	
}
