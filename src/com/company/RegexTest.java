package com.company;

/**
 * Created by lenovo on 2016/6/22.
 */

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    //private final static Pattern HTML = Pattern.compile("<(!|/)?\\w+( ((.|\\n)*?\"\")?)? *>", Pattern.CASE_INSENSITIVE);
    //private final static Pattern HTML = Pattern.compile("</.+?>", Pattern.CASE_INSENSITIVE);//暂时使用只匹配</的形式
    //private final static Pattern AppendFile=Pattern.compile("\\.doc|\\.txt|\\.pdf|\\.xls",Pattern.CASE_INSENSITIVE);
    private final static Pattern test=Pattern.compile("\\.doc|\\.txt|\\.pdf|\\.xls",Pattern.CASE_INSENSITIVE);
    public static void main(String[] args) {
        String fileName = "test.txt";
        //String fileName="data/train/spam/102.txt";
        File file = new File(fileName);
        String s = txt2String.readFile(file);
        String result = null;
        Matcher ma = test.matcher(s);
        while (ma.find())
        {
            result =ma.group();
            System.out.println(result);
        }
    }
}