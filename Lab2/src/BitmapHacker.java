import java.io.*;

public class BitmapHacker{
	private static final int BYTES_PER_PIXEL = 3;
	private static final int BMP_HEADER_SIZE_OFFSET = 0x000A;
	private static final int BMP_WIDTH_OFFSET = 0x0012;
	private static final int BMP_HEIGHT_OFFSET = 0x0016;
	private static final int BMP_DATA_OFFSET = 0x0036;
	private static final int BITS_PER_PI_OFFSET = 0x001C;
	private static final int DWORD_BYTES = 4;
	
	private int trailingZeros;  // the number of trailing zeros on each line
	private int headerSize;
	private int[] header;

	private int width;
	private int height;
	private Pixel[][] pixels;
	
	public BitmapHacker(File file) throws IOException{
		if(file == null)
			throw new IllegalArgumentException("Attempted to create BitmapHacker with null file reference.");
		if(!file.isFile())
			throw new IllegalArgumentException("Attempted to create BitmapHacker with file which does not refer to file on disk");

		RandomAccessFile reader = new RandomAccessFile(file, "r");

		//First check that bitmap has 24-bit color depth (ie 3 bytes per pixel):
		reader.seek(BITS_PER_PI_OFFSET);
		int colorDepth = reader.read() + reader.read()*256; //reading a single word in little endian.
		if(colorDepth != BYTES_PER_PIXEL * 8){
			String err = String.format("Attempted to read bitmap with %d-bit color depth.  Can only read bitmap of %d-bit color depth.", colorDepth, BYTES_PER_PIXEL * 8);
			throw new IllegalArgumentException(err);
		}

		reader.seek(BMP_HEADER_SIZE_OFFSET);
		headerSize = 0;
		for(int i = 0; i < DWORD_BYTES; i++) {
			headerSize += reader.read()*Math.pow(256, i);
		}
		header = new int[headerSize];
		
		reader.seek(0);
		for(int i = 0; i < headerSize; i++) {
			header[i] = reader.read();
		}
		
		reader.seek(BMP_WIDTH_OFFSET);
		width = 0;
		for(int i = 0; i < DWORD_BYTES; i++) {
			width += reader.read()*Math.pow(256, i);
		}
		trailingZeros = (width*BYTES_PER_PIXEL) % 4;
		
		reader.seek(BMP_HEIGHT_OFFSET);
		height = 0;
		for(int i = 0; i < DWORD_BYTES; i++) {
			height += reader.read()*Math.pow(256, i);
		}
		pixels = new Pixel[height][width];

		reader.seek(BMP_DATA_OFFSET);
		for(int i = height - 1; i >= 0; i--){
			for(int j = width - 1; j >= 0; j--){
				int B = reader.read();
				int G = reader.read();
				int R = reader.read();
				pixels[i][j] = new Pixel(R,G,B);
			}
			for(int n = 0; n < trailingZeros; n++){
				reader.read();  //Skip the trailing zeros since they aren't actually rendered and storing them would be cumbersome
			}
		}
		reader.close();
	}

	public void writeImageToFile(File img) throws IOException{
		if(img == null)
			throw new IllegalArgumentException("Attempted to write image to null file reference.");
		RandomAccessFile writer = new RandomAccessFile(img, "rw");
		writer.seek(0);
		for(int i = 0; i < headerSize; i++){
			writer.write(header[i]);
		}
		for(int i = height - 1; i >= 0; i--){
			for(int j = width - 1; j >= 0; j--) {
				writer.write(pixels[i][j].getBlue());
				writer.write(pixels[i][j].getGreen());
				writer.write(pixels[i][j].getRed());
			}
			for(int n = 0; n < trailingZeros; n++)  //At the end of every row, write the trailing zeros.
				writer.write(0);
		}
		writer.close();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void flip(){

	}

	public void blur(){

	}

	public void enhance(){

	}
}
