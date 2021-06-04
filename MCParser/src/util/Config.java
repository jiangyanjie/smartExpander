package util;

import java.io.*;

import edu.mit.jwi.IDictionary;
import edu.mit.jwi.morph.WordnetStemmer;

public class Config {
    public static String projectName;
    public static String outFile = "D:\\FSE2021\\evaluation\\RQ5\\test\\project1.csv";
    public static String fengefu = ",";

    public static String outFileName = "/Users/huyamin/Desktop/RecomMethodName/RecomMethodName/ParseResult/MethodComment.tsv";
    public static String newOutFileName = "/Users/huyamin/Desktop/RecomMethodName/RecomMethodName/ParseResult/NewMethodComment.tsv";
    public static String englishDicFile = "./Dic/EnglishDic.txt";
    //    private static String wordnetPath = "/Users/huyamin/Desktop/RecomMethodName/RecomMethodName/MCParser/wordnet/dict";
    public static IDictionary dict;
    public static WordnetStemmer stemmer;

    static {

//		try {
//			URL url = new URL("file", null, wordnetPath);
//		    dict = new Dictionary(url);
//		    dict.open();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		stemmer = new WordnetStemmer(dict);
    }

}
