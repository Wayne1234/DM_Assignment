package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EnronFilter {
    private final static Pattern mailAddr = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
    private final static Pattern enronAddr=Pattern.compile("[\\w[.-]]+@enron.com",Pattern.CASE_INSENSITIVE);

    public static void main(String[] args){
        int [] count=new int[14926];
        for (int i=1;i<=14926;i++) {
            //System.out.println(i);
            String fileName="data/test/"+i+".txt";
            File file = new File(fileName);
            String s = txt2String.readFile(file);
            Matcher m = enronAddr.matcher(s);
            while (m.find()) {
                System.out.println(m.find());
                count[i-1]++;
            }
        }

        int [] result=new int[14926];
        for(int i=0;i<14926;i++){
            result[i]=1;
        }
        for(int i=0;i<14926;i++){
            if(count[i]>0){
                result[i]=-1;//注意，spam被标记成1，而ham标记为-1
            }
        }
        for(int i=0;i<14926;i++) {
            //System.out.println((i + 1) + ".txt\t" + result[i]);
            try {
                File file = new File("131220022.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWritter = new FileWriter(file.getName(), true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write((i + 1) + ".txt\t"+result[i]+"\n");
                bufferWritter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done");
    }
}