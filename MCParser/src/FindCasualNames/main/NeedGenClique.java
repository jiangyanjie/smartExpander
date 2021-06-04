package FindCasualNames.main;

import util.Util;

import java.io.File;
import java.util.ArrayList;
public class NeedGenClique {

    public static ArrayList<String> getNameList(String path){

        File file = new File(path);
        ArrayList<String> nameList = new ArrayList<>();
        for (File oneFile : file.listFiles()) {
            nameList.add(oneFile.getName());
        }
        return nameList;
    }

    public static void filteringF(ArrayList<String> nameList){
         File file = new File("./Data/abbr/filterAbbr");
         ArrayList<String> eachFile = new ArrayList<>();

         for(File oneFile: file.listFiles()){
             eachFile.clear();
             String tempPath = oneFile.getPath();
             String tempName = oneFile.getName();
             if(nameList.contains(tempName)){
                 eachFile = Util.readFile(tempPath);
                 for(int i=0;i< eachFile.size();i++){
                     Util.appendFile(eachFile.get(i), "./Data/abbr/jan5_filtering/"+ tempName );
                     Util.appendFile("\n", "./Data/abbr/jan5_filtering/"+ tempName );
                 }
             }
         }
    }

//    public static void main(String[] args){
//        String path = "D:\\FSE2021\\diffNameList\\new15";
//        ArrayList<String> res = getNameList(path);
//        filteringF(res);
//    }

}
