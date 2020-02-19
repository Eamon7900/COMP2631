//Eamon McCarron
// COMP 2631, Lab 1
// Jan 16, 2020

import java.io.*;
import java.lang.IllegalArgumentException;

public class FileProcessor {
    public static final int EMPTY = -1; //I decided to make this public since it might be important for another class to know that the value -1 corresponds to the "empty" state.
    private static final int LINE_LENGTH_FOR_HEX = 10;
    private int size;
    private int data[];

    public FileProcessor(File file) throws IOException{
        if(file == null){
            throw new IllegalArgumentException("File Processor instantiated with null file reference.");
        }
        if(!file.isFile()){
            throw new IllegalArgumentException("File Processor instantiated with file reference which does not specify a file on the disk.");
        }
        if(file.length() > Integer.MAX_VALUE){
            throw new IllegalArgumentException("File Processor instantiated with file reference that is too large to read into integer array");
        }

        size = (int)file.length(); //We have already asserted that the value of file.getTotalSpace will not overflow an integer.
        data = new int[size];
        RandomAccessFile reader = new RandomAccessFile(file, "r");
        

        for(int i = 0; i < size; i++){
            data[i] = reader.read();
        }
        reader.close();
    }

    /**
    @return either null if the data is empty or and array of bytes representing the data.
    */
    public int[] getData(){
        return data.clone();
    }

    /**
    @return either -1 if the file is empty or an integer representing the number of bytes stored in the file.
    */
    public int size(){
        return size;
    }

    public void clear(){
        size = EMPTY;
        data = null;
    }

    public void printHex(File file) throws IOException{
        if(file == null){
            throw new IllegalArgumentException();
        }
        if(size == -1){
            throw new IllegalStateException();
        }

        PrintWriter writer = new PrintWriter(file);
        int byteCounter = 1;
        for(int i : data){
            int digit1 = i / 16; //The first hexadecimal digit
            int digit2 = i % 16; //The second hexadeciaml digit

            StringBuilder result = new StringBuilder();
            result.append(intToHexDigit(digit1));
            result.append(intToHexDigit(digit2));
            

            if(byteCounter % LINE_LENGTH_FOR_HEX == 0){
                result.append("\n");
            }else if(byteCounter != this.size){
                result.append(' ');
            }

            writer.print(result);  //This is to truncate either the space or linebreak which will always follow the last character.
            byteCounter++;
        }
        writer.close();
    }
    
    private char intToHexDigit(int i){
        if(i < 10) return (char)('0' + i);
        switch(i){
            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
            case 13:
                return 'd';
            case 14:
                return 'e';
            case 15:
                return 'f';
            default:
                throw new IllegalArgumentException();
        }
    }

    public boolean compare(FileProcessor fp){
        if(fp == null)
            throw new IllegalArgumentException("Attempting to compare with a null file");

        if(this.size != fp.size()) return false;
        int curByte = 0;
        for(int b : data){
            if(b != fp.getData()[curByte]) return false;
            curByte++;
        }
        return true;
    }
}
