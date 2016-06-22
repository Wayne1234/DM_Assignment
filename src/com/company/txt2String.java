package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class txt2String {
    public static String readFile(File file){
        String result = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                result = result + "\n" +s;
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
