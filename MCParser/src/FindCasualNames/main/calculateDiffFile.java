package FindCasualNames.main;

import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class calculateDiffFile {


    public static void getFilewithProgress(String path){

        File file = new File(path);
        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
        for (File oneFile : file.listFiles()) {
            result.clear();
            String name = oneFile.getName();
            String tpath = oneFile.getPath();
            ArrayList<String> eachFile = Util.readFile(tpath);
            for(int k =0;k< eachFile.size();k++){
                String prjName = eachFile.get(k).split(",")[1];
                if(!result.contains(prjName)){
                    result.add(prjName);
                }
            }

            System.out.println(name + "===="+result.size());

            if(result.size()>=13){
                for(int i=0;i< result.size();i++){
                    Util.appendFile(result.get(i), "./Data/diffNameList/new15/" + name );
                    Util.appendFile("\n", "./diffNameLisData/new15/" + name );
                }
            }else{
                for(int j=0;j< result.size();j++){
                    Util.appendFile(result.get(j), "./Data/diffNameList/" + name );
                    Util.appendFile("\n", "./Data/diffNameList/" + name );
                }
            }
        }
    }

//    public static void main(String[] args){
//        System.out.println("accepted in fse2021");
//        System.out.println("===================");
//
//        String path = "D:\\FSE2021\\NameList";
//        getFilewithProgress(path);
//    }
}
