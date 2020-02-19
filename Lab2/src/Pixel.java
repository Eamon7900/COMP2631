
public class Pixel {
	private static final int MAX_RGB = 255;
	private static final int MIN_RGB = 0;

	private int red;
	private int green;
	private int blue;
	
	public Pixel(int r, int g, int b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}
	
	public void setRed(int r) {
		if(r < MIN_RGB)
			r = MIN_RGB;
		if(r > MAX_RGB)
			r = MAX_RGB;
		red = r;
	}
	
	public void setGreen(int g) {
		if(g < MIN_RGB)
			g = MIN_RGB;
		if(g > MAX_RGB)
			g = MAX_RGB;
		green = g;
	}
	
	public void setBlue(int b) {
		if(b < MIN_RGB)
			b = MIN_RGB;
		if(b > MAX_RGB)
			b = MAX_RGB;
		blue = b;
	}
	
	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}
}
