package FindCasualNames.main;

import java.io.*;
import java.util.ArrayList;

public class jyjutil {
    public static boolean isEnglishChar(char c) {
        if (c < 127) {
            return true;
        }
        return false;
    }

    public static boolean isLetter(char c) {
        if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNum(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        } else {
            return false;
        }
    }

    // str only consists of letters
    public static ArrayList<String> splitBigLetter(String str) {
        char[] list = str.toCharArray();
        str = "A" + str + "A";
        char[] tempList = str.toCharArray();

        for (int i = 1; i < tempList.length - 1; i++) {
            if (tempList[i] >= 'A' && tempList[i] <= 'Z' &&
                    tempList[i - 1] >= 'A' && tempList[i - 1] <= 'Z' &&
                    tempList[i + 1] >= 'A' && tempList[i + 1] <= 'Z') {
                list[i - 1] = (char) (tempList[i] - 'A' + 'a');
            } else {
                list[i - 1] = tempList[i];
            }
        }

        str = new String(list);
        ArrayList<String> result = new ArrayList<String>();
        int startPositionOfSubstring = 0;
        str = str + 'A';
        for (int endPositionOfSubstring = 0; endPositionOfSubstring < str.length(); endPositionOfSubstring++) {
            if (str.charAt(endPositionOfSubstring) >= 'A' && str.charAt(endPositionOfSubstring) <= 'Z') {
                // to exclude initial up case letter
                if (str.substring(startPositionOfSubstring, endPositionOfSubstring).length() > 0) {
                    // to lower case
                    result.add(str.substring(startPositionOfSubstring, endPositionOfSubstring).toLowerCase());
                    startPositionOfSubstring = endPositionOfSubstring;
                }
            }
        }
        return result;
    }

    // elements consist of
    // letters
    // numbers
    // _
    // $
    // not start with numbers
    // the size of return value may be 0 (e.g., $)
    public static ArrayList<String> split(String str) {
        if (str.length() == 0) {
            return null;
        }
         int temp1 = str.indexOf("<");
        int temp2 = str.lastIndexOf(">");

        if (temp1 != -1 && temp2 != -1) {
            str = str.substring(0, temp1) + str.substring(temp2 + 1);
        }
        temp1 = str.indexOf("[");
        temp2 = str.lastIndexOf("]");
        if (temp1 != -1 && temp2 != -1) {
            str = str.substring(0, temp1) + str.substring(temp2 + 1);
        }

        ArrayList<String> result = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            System.err.println("error: split");
            return null;
        }
        // delete $, _, numbers at the beginning of str

        while (str.length() > 0 && (str.charAt(0) == '$' || str.charAt(0) == '_' || isNum(str.charAt(0)))) {
            str = str.substring(1);

        }
        if (str.length() == 0) return result;
        // replace $, _, numbers with the separator #
        str = str.replaceAll("\\d", "#");
        str = str.replaceAll("\\_", "#");
        str = str.replaceAll("\\$", "#");

        for (String string : str.split("#")) {
            if (string.length() > 0) {
                result.addAll(splitBigLetter(string));
            }
        }
        return result;
    }

    // ori: index; sequence: idx
    public static boolean isSequence(String ori, String sequence) {
        int j = 0;
        for (int i = 0; i < ori.length(); i++) {
            if (j < sequence.length() && ori.charAt(i) == sequence.charAt(j)) {
                j++;
            }
        }
        if (j == sequence.length()) {
            return true;
        }
        return false;
    }

    // convert text file to ArrayList<String> line by line
    public static ArrayList<String> readFile(String fileName) {
        ArrayList<String> result = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (!tempString.equals("")) {
                    result.add(tempString);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static boolean equalOfWord(String str1, String str2) {
        str1 = str1.trim();
        str2 = str2.trim();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if (str1.length() == 0 || str2.length() == 0) {
            return false;
        }

        if (str1.equals(str2)) {
            return true;
        } else {
            String str1Single = str1;
            String str2Single = str2;
            if (str1.charAt(str1.length()-1) == 's' &&
                    str1.length()>1) {
                str1Single = str1.substring(0, str1.length()-1);
            }
            if (str2.charAt(str2.length()-1) == 's' &&
                    str2.length()>1) {
                str2Single = str2.substring(0, str2.length()-1);
            }
            return str1Single.equals(str2) ||
                    str2Single.equals(str1) ||
                    str1Single.equals(str2Single);
        }
    }
    public static void deleteIfExist(File outFile) throws Exception {
        if (outFile.exists()) {
            outFile.delete();
        }
        else {
            outFile.createNewFile();
        }
    }

    public static void appendFile(String line, String path) {

        FileWriter fw = null;
        try {
            File f=new File(path);

            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.print(line);
        pw.flush();
        try {
            fw.flush();
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}		

