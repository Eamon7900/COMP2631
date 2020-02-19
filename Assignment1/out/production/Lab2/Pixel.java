//Eamon McCarron
//COMP 2631, Assignment 1
//Feb. 17, 2020

public class Pixel {
	private static final int MAX_RGB = 255;
	private static final int MIN_RGB = 0;

	private int red;
	private int green;
	private int blue;

	/**
	 * Constructs a 24-bit color depth pixel with RGB value specified by parameters.
	 * @param r The red value of the pixel between 0 and 255.
	 * @param g The green value of the pixel between 0 and 255.
	 * @param b The blue value of the pixel between 0 and 255.
	 */
	public Pixel(int r, int g, int b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}

	/**
	 * Sets the red value of the pixel.
	 * @param r the red value of the pixel between 0 and 255.
	 */
	public void setRed(int r) {
		if(r < MIN_RGB)
			r = MIN_RGB;
		if(r > MAX_RGB)
			r = MAX_RGB;
		red = r;
	}

	/**
	 * Sets the green value of the pixel.
	 * @param g The green value of the pixel, between 0 and 255.
	 */
	public void setGreen(int g) {
		if(g < MIN_RGB)
			g = MIN_RGB;
		if(g > MAX_RGB)
			g = MAX_RGB;
		green = g;
	}

	/**
	 * Sets the blue value of the pixel.
	 * @param b The blue value of the pixel, between 0 and 255.
	 */
	public void setBlue(int b) {
		if(b < MIN_RGB)
			b = MIN_RGB;
		if(b > MAX_RGB)
			b = MAX_RGB;
		blue = b;
	}

	/**
	 * Gets the red RGB value of the pixel.
	 * @return The red RGB value of the pixel, between 0 and 255.
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Gets the green RGB value of the pixel.
	 * @return The green RGB value of the pixel, between 0 and 255.
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Gets the blue RGB value of the pixel.
	 * @return The blue RGB value of the pixel, between 0 and 255.
	 */
	public int getBlue() {
		return blue;
	}
}
