package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;

public class getAttribute {
    private final static Pattern HTML = Pattern.compile("</.+?>", Pattern.CASE_INSENSITIVE);
    private final static Pattern enronKey = Pattern.compile("enron", Pattern.CASE_INSENSITIVE);
    private final static Pattern AppendFile = Pattern.compile("\\.doc|\\.txt|\\.pdf|\\.xls|\\.zip|\\.rar", Pattern.CASE_INSENSITIVE);
    private final static int LINES = 14926;
    private final static int hamLINES=7636;
    private final static int spamLINES=2332;
    private final static int AttrNum = 3;
    public static Instances s_datasetHeader = datasetHeader();

    public static void main(String[] args) throws IOException {
        //writeTestData();
        File out = new File("Train.arff");
        if(out.exists()){
            out.delete();
        }
        if (!out.exists()) {
            out.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(out.getName(), true);
        fileWritter.write(s_datasetHeader.toString());
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        for (int i = 0; i < hamLINES; i++) {
            int[] record = new int[AttrNum];
            String fileName = "data/train/ham/" + (i + 1) + ".txt";
            File file = new File(fileName);
            String s = txt2String.readFile(file);
            Matcher html = HTML.matcher(s);
            while (html.find()) {
                record[0]++;//record[0]记录了HTML标签数
            }
            Matcher enron = enronKey.matcher(s);
            while (enron.find()) {
                record[1]++;//enron关键字出现次数
            }
            Matcher Append = AppendFile.matcher(s);
            while (Append.find()) {
                record[2]++;//附件个数
            }
            //bufferWritter.write((i + 1) + "\t" + record[0] + "\t" + record[1] + "\t" + record[2] + "\n");
            bufferWritter.write(record[0] + "\t" + record[1] + "\t" + record[2] +"\t"+"-1"+"\n");
        }
        for (int i = 0; i < spamLINES; i++) {
            int[] record = new int[AttrNum];
            String fileName = "data/train/spam/" + (i + 1) + ".txt";
            File file = new File(fileName);
            String s = txt2String.readFile(file);
            Matcher html = HTML.matcher(s);
            while (html.find()) {
                record[0]++;//record[0]记录了HTML标签数
            }
            Matcher enron = enronKey.matcher(s);
            while (enron.find()) {
                record[1]++;//enron关键字出现次数
            }
            Matcher Append = AppendFile.matcher(s);
            while (Append.find()) {
                record[2]++;//附件个数
            }
            bufferWritter.write(record[0] + "\t" + record[1] + "\t" + record[2] +"\t"+"1"+"\n");
        }
        bufferWritter.close();
    }

    public static void writeTestData() throws IOException {
        File out = new File("Test.txt");
        if(out.exists()){
            out.delete();
        }
        if (!out.exists()) {
            out.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(out.getName(), true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        for (int i = 0; i < LINES; i++) {
            int[] record = new int[AttrNum];
            String fileName = "data/test/" + (i + 1) + ".txt";
            File file = new File(fileName);
            String s = txt2String.readFile(file);
            Matcher html = HTML.matcher(s);
            while (html.find()) {
                record[0]++;//record[0]记录了HTML标签数
            }
            Matcher enron = enronKey.matcher(s);
            while (enron.find()) {
                record[1]++;//enron关键字出现次数
            }
            Matcher Append = AppendFile.matcher(s);
            while (Append.find()) {
                record[2]++;//附件个数
            }
            bufferWritter.write(record[0] + "\t" + record[1] + "\t" + record[2] +"\n");
        }
        bufferWritter.close();
    }

    public static Instances datasetHeader(){
        FastVector attInfo = new FastVector();
        Attribute att = new Attribute("HTMLNum");attInfo.addElement(att);
        att=new Attribute("enronNum");attInfo.addElement(att);
        att=new Attribute("AppendNum");attInfo.addElement(att);
        FastVector classes = new FastVector();
        classes.addElement("1");
        classes.addElement("-1");
        att = new Attribute("class", classes);
        attInfo.addElement(att);

        Instances instances = new Instances("MailAttr", attInfo, 0);
        instances.setClassIndex( instances.numAttributes() - 1);
        return instances;
    }
}