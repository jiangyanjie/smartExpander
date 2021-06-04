package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Dic {
    public static String computerAbbrDelimiter = ";";

    public static HashSet<String> englishDicHashSet = new HashSet<String>();
    public static HashMap<String, String> abbrDicHashMap = new HashMap<String, String>();
    public static HashMap<String, String> computerAbbrDicHashMap = new HashMap<>();
    static {
        // init english dic
        try {
            BufferedReader reader = new BufferedReader(new FileReader(GlobleVariable.englishDicFile));
            String temp;
            while ((temp = reader.readLine()) != null) {
                // convert to lower case
                englishDicHashSet.add(temp.toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // init abbr dic
        ArrayList<String> file = Util.readFile(GlobleVariable.abbrDicFile);
        for (String str : file) {
            String[] temp = str.split(":=");
            String key = temp[0];
            String value = temp[1];
            abbrDicHashMap.put(key.toLowerCase(), value.toLowerCase());
        }

        // init computer abbr dic
        ArrayList<String> file2 = Util.readFile(GlobleVariable.computerAbbrDicFile);
        for (String str : file2) {
            String[] temp = str.split(":=");
            String key = temp[0];
            String value = temp[1];
            computerAbbrDicHashMap.put(key.toLowerCase(), value.toLowerCase());
        }
    }

    public static boolean isInDict(String str){
        if (str.length() == 1) {
            return false;
        }
        if (englishDicHashSet.contains(str.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}
