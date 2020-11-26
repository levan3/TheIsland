package application;


public class Plant extends Thing {
	private int size;
	private int randomDir;
	

	public Plant(double x, double y, String getImage, int size) {
		super(x, y, getImage);
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRandomDir() {
		return randomDir;
	}

	public void setRandomDir(int randomDir) {
		this.randomDir = randomDir;
	}
	
	
}


