package FindCasualNames.main;

import util.Dic;
import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;

public class Step2_getAbbreviation {

    public static void main(String[] args) throws Exception{
        Util.deleteIfExist(new File(Setting.filteredAbbrName));
        String path = Setting.outFile;
        String abbrPath = Setting.filteredAbbrName;

        File file = new File(path);
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while((tempString = reader.readLine()) != null){
                if(!tempString.equals("")){
                   String[] eachLine = tempString.split(",");
                   String oneIdentifier =eachLine[3];
                   ArrayList<String> tokens = Util.split(oneIdentifier);

                   for(String token: tokens){
                       if(!(Dic.isInDictToken(token))){
                        Util.appendFile(token + "," + tempString + "\n" , abbrPath);
                       }
                   }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
