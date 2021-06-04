package FindCasualNames.main;

import util.Dic;
import util.Util;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class getAbbrPathVector {
    public static void main(String[] args) throws Exception{
       combine();
    }

    public static void combine() throws Exception{
        String path1 = "./Data/step2.csv";
        String path2 = "./Data/context/path2vector.csv";

        String combinePath = "./Data/context/abbrPathVector.txt";
        File file1 = new File(path1);
        File file2 = new File(path2);
        BufferedReader reader1;
        BufferedReader reader2;
        int t=0;
        try{
            reader1 = new BufferedReader(new FileReader(file1));
            reader2 = new BufferedReader(new FileReader(file2));
            String tempString1;
            String tempString2;

            while(((tempString1 = reader1.readLine()) != null)){
                    String[] eachLine1 = tempString1.split(",");
                    System.out.println(eachLine1[0] + "==");
                    String abbr = eachLine1[0];
                    System.out.println("====" + eachLine1[5] );
                    String abbrPath1 = eachLine1[5];

                    for(int i=0;i<434375;i++){
                        tempString2 =reader2.readLine();
                        String[] eachLine2 = tempString2.split(",");
                        String vector = eachLine2[0];
                        String abbrPath2 = eachLine2[1];

                        if(abbrPath1.equals(abbrPath2)){
                            Util.appendFile(abbr + "," + abbrPath1 + "," + vector + "\n", combinePath);
                        }
                    }
            }
            }catch(Exception e){
            e.printStackTrace();
        }
    }
}
