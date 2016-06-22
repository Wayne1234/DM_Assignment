package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {
    public static void main(String[] args){
        try {
            readF1("Test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static final void readF1(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath)));

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            //System.out.println( line.split("\t")[0]+"\t"+line.split("\t")[1]+"\t"+line.split("\t")[2]);
            int a=new Integer(line.split("\t")[0]);
            System.out.println(a);
        }
        br.close();
    }

    public static final void readF2(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader bufferedreader = new BufferedReader(fr);
        String instring;
        while ((instring = bufferedreader.readLine().trim()) != null) {
            if (0 != instring.length()) {
                System.out.println(instring);
            }
        }
        fr.close();
    }
}

