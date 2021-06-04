package FindCasualNames.main;

import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class minusFile {

    public static ArrayList<String> getFilewithProgress(String path){

        ArrayList<String> fileName = new ArrayList<>();

        File file = new File(path);

        for (File oneFile : file.listFiles()) {
            fileName.add(oneFile.getName());
        }
        return fileName;
    }


    public static void main(String[] args){
        ArrayList<String> inputData = getFilewithProgress("./Data/clique/inputData");
        ArrayList<String> outputData = getFilewithProgress("./Data/clique/outputData/download/cliques");

        for(int i=0;i< inputData.size();i++){
            String temp = inputData.get(i);
            String abbr = temp.substring(0,temp.indexOf(".txt"));
            System.out.println(temp);
            System.out.println(abbr);
            Util.appendFile(abbr, "./Data/clique/list.txt");
            Util.appendFile("\n", "./Data/clique/list.txt");
        }
    }
}
