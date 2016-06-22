package com.company;

import weka.classifiers.Classifier;
import weka.core.Instance;

import java.io.*;
import java.util.regex.Matcher;

public class Predict {
    private static final String filePath="Test.txt";
    private static final int LINES=14926;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int[] result=new int[LINES];
        Classifier m_model;
        ObjectInputStream input = new ObjectInputStream(new FileInputStream("test.model"));
        m_model = (Classifier) input.readObject();
        input.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        int count=0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            double []features=new double[4];
            //System.out.println( line.split("\t")[0]+"\t"+line.split("\t")[1]+"\t"+line.split("\t")[2]);
            features[0]=new Double(line.split("\t")[0]);
            features[1]=new Double(line.split("\t")[1]);
            features[2]=new Double(line.split("\t")[2]);
            Instance ins=new Instance(1,features);
            ins.setDataset(getAttribute.s_datasetHeader);
            try {
                double[] predictionDistribution = m_model.distributionForInstance(ins);
                if(predictionDistribution[0]>predictionDistribution[1])
                    result[count]=1;
                else
                    result[count]=-1;
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        br.close();


        File out = new File("131220022.txt");
        if(out.exists()){
            out.delete();
        }
        if (!out.exists()) {
            out.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(out.getName(), true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        for (int i = 0; i < LINES; i++) {
            bufferWritter.write((i+1)+".txt"+"\t"+result[i]+"\n");
        }
        bufferWritter.close();
    }

}
