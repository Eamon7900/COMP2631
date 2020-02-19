import java.io.File;
import java.io.IOException;
//A thread that executes a given set of operations on an image, and saves a copy after each operation.
public class HackerThread extends Thread{
    private BitmapHacker hacker;
    private File image;
    private Operation[] operations;

    public enum Operation{
        FLIP,
        BLUR,
        ENHANCE_R,
        ENHANCE_G,
        ENHANCE_B,
        COPY,
        RESET
    }

    public HackerThread(File img, Operation[] op){
        image = img;
        operations = op;
    }


    @Override
    public void run(){
        try{
            hacker = new BitmapHacker(image);
            for(Operation op : operations){
                String operation = "";
                switch(op) {
                    case FLIP :
                        hacker.flip();
                        operation = "-flipped";
                        break;
                    case BLUR :
                        hacker.blur();
                        operation = "-blurred";
                        break;
                    case ENHANCE_R:
                        hacker.enhance(BitmapHacker.RED);
                        operation = "-enhanced-R";
                        break;
                    case ENHANCE_G:
                        hacker.enhance(BitmapHacker.GREEN);
                        operation = "-enhanced-G";
                        break;
                    case ENHANCE_B:
                        hacker.enhance(BitmapHacker.BLUE);
                        operation = "-enhanced-B";
                        break;
                    case COPY :
                        operation = "-copy";
                    case RESET:
                        hacker = new BitmapHacker(image);
                }
                String path = appendToBMPPath(operation, image.getPath());
                File output = new File(path);
                /*if(output.isFile()) {
                    path = appendToBMPPath(Integer.toString(++i), path);
                    output = new File(path);
                }*/
                hacker.writeImageToFile(output);
                System.out.println("finished hacking operation on: " + image.getPath());
            }
        } catch (IOException iOe){
            iOe.printStackTrace();
        }
    }

    // given the path of a bitmap file, returns the same path with String path appended to the filename before .bmp
    private static String appendToBMPPath(String append, String path){
        return (path.substring(0, path.length() - 4) + append + ".bmp");
    }
}
