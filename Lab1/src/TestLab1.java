//Eamon McCarron
// COMP 2631, Lab 1
// Jan 16, 2020
import java.io.*;

//In order to execute, this requires all data files, as well as an empty text file named "empty.txt".
public class TestLab1{
    public static void main(String[] args){
        System.out.println("Does Object.clone() make an exact copy of an int array? " + testClone());

        File mask1 = new File("mask1.jpg");
        File mask2 = new File("mask2.jpg");
        File mask3 = new File("mask3.jpg");

        File mask1Hex = new File("mask1Hex.txt"); //Output of mask1 in hexadecimal
        File mask2Hex = new File("mask2Hex.txt"); //Output of mask2 in hexadecimal
        File mask3Hex = new File("mask3Hex.txt"); //Output of mask3 in hexadecimal

        File correctOutput = new File("mask1.jpg.txt");


        FileProcessor fp1, fp2;  //Two file processors for comparison
        try{
            fp1 = new FileProcessor(mask1);
            fp2 = new FileProcessor(mask2);
            System.out.println("Mask1.jpg = Mask2.jpg: " + fp1.compare(fp2));


            fp1.printHex(mask1Hex);
            fp2.printHex(mask2Hex);
            fp1.clear();
            fp2.clear();

            fp1 = new FileProcessor(mask1Hex);
            fp2 = new FileProcessor(mask2Hex);

            System.out.println("Mask1 in hexadecimal = Mask2 in hexadecimal: " + fp1.compare(fp2));
            fp2 = new FileProcessor(correctOutput);
            System.out.println("Mask1 hexadecimal parsed correctly? " + fp1.compare(fp2));
            //This gives false, and I assumed it was because the "correct output" actually has a trailing line break.  But even removing that it still gives false.
            //The files seem identical on inspection (same hex codes and no trailing spaces), and I know the compare function works, so I'm not really sure whats going on here.

            fp1 = new FileProcessor(mask2);
            fp2 = new FileProcessor(mask3);
            System.out.println("Mask2 = mask3: " + fp1.compare(fp2));

            fp2.printHex(mask3Hex);
            fp1.clear(); //Not sure if clearing before I reassign my fileProcessor instance is best practice.  I don't think it really matters
            fp2.clear(); //since the garbage collector will clear out all of the data anyway.

            fp1 = new FileProcessor(mask2Hex);
            fp2 = new FileProcessor(mask3Hex);
            System.out.println("Mask2 hex = Mask3 hex: " + fp1.compare(fp2));

            File empty = new File("empty.txt");
            File emptyHex = new File("emptyHex.txt");
            fp1 = new FileProcessor(empty);
            fp1.printHex(emptyHex);
            fp1 = new FileProcessor(emptyHex);
            System.out.println("Size of hex file generated from blank file: " + fp1.size());
        }catch(IOException iOe){
            iOe.printStackTrace();
        }

    }

    private static boolean testClone(){
        int[] test = {0,1,2,3,4};
        for(int i : test){
            if(i != test.clone()[i]) return false;
        }
        return true;
    }
}