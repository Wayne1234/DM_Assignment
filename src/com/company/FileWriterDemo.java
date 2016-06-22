package com.company;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterDemo
{
    public static void main( String[] args )
    {
        try{
            String data = " This content will append to the end of the file";
            File file =new File("out.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file.getName(),false);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write(data);
            bufferWritter.close();
            System.out.println("Done");

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
