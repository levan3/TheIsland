package application;

import javafx.scene.image.ImageView;

public class Thing {

	private double x; // x coordination
	private double y; // y coordination
	public String getImage;
	private ImageView imageView;

	public Thing(double x, double y, String getImage) {
		super();
		this.x = x;
		this.y = y;

		this.getImage = getImage;

	}

	public Thing(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setGetImage(String getImage) {
		this.getImage = getImage;
	}

	public String getGetImage() {
		return getImage;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

}
