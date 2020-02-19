import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TestA1 {
	public static final String WORKING_DIR = System.getProperty("user.home") + "/desktop/2631A1/";
	private static final String KITTEN_PATH = WORKING_DIR + "kitten-350x234.bmp";
	private static final String FRACTAL_PATH = WORKING_DIR + "fractal-500x375.bmp";
	private static final String SW_PATH = WORKING_DIR + "starwars-640x480.bmp";
	private static final String SUNRISE_PATH = WORKING_DIR + "sunrise-640x480.bmp";
	private static final String ROVER_PATH = WORKING_DIR + "rovers-803x535.bmp";
	private static final String OWL_PATH = WORKING_DIR + "baby-owl-640x480.bmp";
	private static final HackerThread.Operation[] OPERATIONS = {HackerThread.Operation.BLUR, HackerThread.Operation.RESET,
																HackerThread.Operation.ENHANCE, HackerThread.Operation.RESET,
																HackerThread.Operation.FLIP};

	//I decided to multi-thread my testing since there is no reason that each image can't run its hacking operations concurrently.
	//Since most modern systems have at least 4 logical threads, this should be a significant performance boost.  Especially since we need to run 30, expensive hacking operations.
	public static void main(String[] args) {
		Pixel pi = new Pixel(255,0,255);
		System.out.printf("%s\n", WORKING_DIR);

		pi.setRed(125);
		pi.setGreen(23);
		pi.setBlue(245);

		System.out.printf("%d %d %d\n", pi.getRed(), pi.getGreen(), pi.getBlue());

		File fractalImg = new File(FRACTAL_PATH);
		File kittenImg = new File(KITTEN_PATH);
		File swImg = new File(SW_PATH);
		File sunriseImg = new File(SUNRISE_PATH);
		File owlImg = new File(OWL_PATH);
		File roverImg = new File(ROVER_PATH);

		HackerThread fractalHacker, kittenHacker, swHacker, sunriseHacker, roverHacker, owlHacker;

		fractalHacker = new HackerThread(fractalImg, OPERATIONS);
		fractalHacker.start();

		kittenHacker = new HackerThread(kittenImg, OPERATIONS);
		kittenHacker.start();

		swHacker = new HackerThread(swImg, OPERATIONS);
		swHacker.start();

		sunriseHacker = new HackerThread(sunriseImg, OPERATIONS);
		sunriseHacker.start();

		roverHacker = new HackerThread(roverImg, OPERATIONS);
		roverHacker.start();

		owlHacker = new HackerThread(owlImg, OPERATIONS);
		owlHacker.start();
	}


}
