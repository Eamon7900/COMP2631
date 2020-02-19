import java.io.File;
import java.io.IOException;

public class TestA1 {
	private static final String WORKING_DIR = "./";
	private static final String KITTEN_PATH = WORKING_DIR + "kitten-350x234.bmp";
	private static final String FRACTAL_PATH = WORKING_DIR + "fractal-500x375.bmp";
	private static final String SW_PATH = WORKING_DIR + "starwars-640x480.bmp";
	private static final String SUNRISE_PATH = WORKING_DIR + "sunrise-640x480.bmp";
	private static final String ROVER_PATH = WORKING_DIR + "rovers-803x535.bmp";
	private static final String PUPPIES_PATH = WORKING_DIR + "puppies-640x480.bmp";
	private static final String OWL_PATH = WORKING_DIR + "baby-owl-640x480.bmp";
	private static final String OWL_MOD_PATH = WORKING_DIR + "baby-owl-mod.bmp";
	private static final String OWL_HIDDEN_PATH = WORKING_DIR + "owlHidden.pdf";
	private static final String CAT_HIDDEN_PATH = WORKING_DIR + "catHidden.pdf";
	private static final String DRAG_HIDDEN_PATH = WORKING_DIR + "dragonHidden.jpg";
	private static final String CAT_MOD_PATH = WORKING_DIR + "cat-mod.bmp";
	private static final String DRAGON_MOD_PATH = WORKING_DIR + "dragon-mod.bmp";


	private static final HackerThread.Operation[] ALL_OPERATIONS = {HackerThread.Operation.BLUR, HackerThread.Operation.RESET,
																HackerThread.Operation.ENHANCE_G, HackerThread.Operation.RESET,
																HackerThread.Operation.FLIP}; //This is a little cumbersome but I think an enum is the best way to specify operations.
	private static final HackerThread.Operation[] BLUR = {HackerThread.Operation.BLUR, HackerThread.Operation.BLUR, HackerThread.Operation.BLUR, HackerThread.Operation.BLUR};

	public static void main(String[] args) {
		Pixel pi = new Pixel(255,0,255);
		System.out.printf("%d %d %d\n", pi.getRed(), pi.getGreen(), pi.getBlue());

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
		File puppiesImg = new File(PUPPIES_PATH);
		File owlModImg = new File(OWL_MOD_PATH);
		File catModImg = new File(CAT_MOD_PATH);
		File dragModImg = new File(DRAGON_MOD_PATH);

		HackerThread fractalHacker, kittenHacker, swHacker, sunriseHacker, roverHacker, owlHacker, puppiesHacker;


		fractalHacker = new HackerThread(fractalImg, ALL_OPERATIONS);
		fractalHacker.start();

		kittenHacker = new HackerThread(kittenImg, ALL_OPERATIONS);
		kittenHacker.start();

		swHacker = new HackerThread(swImg, ALL_OPERATIONS);
		swHacker.start();

		sunriseHacker = new HackerThread(sunriseImg, ALL_OPERATIONS);
		sunriseHacker.start();

		roverHacker = new HackerThread(roverImg, ALL_OPERATIONS);
		roverHacker.start();

		owlHacker = new HackerThread(owlImg, ALL_OPERATIONS);
		owlHacker.start();

		puppiesHacker = new HackerThread(puppiesImg, ALL_OPERATIONS);
		puppiesHacker.start();

		Runnable hideOwl = () -> {
			try {
				BitmapHacker owlModHacker = new BitmapHacker(owlModImg);
				owlModHacker.unhide(new File(OWL_HIDDEN_PATH));
				owlModHacker = new BitmapHacker(owlImg);
				owlModHacker.hide(new File(OWL_HIDDEN_PATH));
				owlModHacker.unhide(new File("owlHiddenTest.pdf"));
			} catch (IOException iOe){
				iOe.printStackTrace();
			}
		};
		Thread owlHider = new Thread(hideOwl);
		owlHider.start();

		Runnable hideCat = () -> {
			try {
				BitmapHacker catModHacker = new BitmapHacker(catModImg);
				catModHacker.unhide(new File(CAT_HIDDEN_PATH));
				catModHacker = new BitmapHacker(owlImg);
				catModHacker.hide(new File(CAT_HIDDEN_PATH));
				catModHacker.unhide(new File("catHiddenTest.pdf"));
			} catch (IOException iOe){
				iOe.printStackTrace();
			}
		};
		Thread catHider = new Thread(hideCat);
		catHider.start();

		Runnable hideDrag = () -> {
			try {
				BitmapHacker dragModHacker = new BitmapHacker(dragModImg);
				dragModHacker.hide(new File(DRAG_HIDDEN_PATH));
				dragModHacker.unhide(new File("dragHiddenTest.jpg"));
			} catch (IOException iOe){
				iOe.printStackTrace();
			}
		};
		Thread dragHider = new Thread(hideDrag);
		dragHider.start();

		while(kittenHacker.getState() != Thread.State.TERMINATED) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException iE){
				iE.printStackTrace();
				break;
			}
		}

		kittenHacker = new HackerThread(kittenImg, BLUR);
		kittenHacker.start();
	}


}
