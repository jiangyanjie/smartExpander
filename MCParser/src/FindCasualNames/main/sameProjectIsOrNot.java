package FindCasualNames.main;

import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class sameProjectIsOrNot {

    public static void getFilewithProgress(String path){

        int i = 1;
        File file = new File(path);
        ArrayList<String> paths = new ArrayList<>();
        for (File oneFile : file.listFiles()) {
            paths.add(oneFile.getPath());
            i++;
        }
        Collections.reverse(paths);
        for (String tempPath : paths) {
            getEachFile(tempPath);
        }
    }
    public static String getProjectName(String path){
        int beginIndex = path.lastIndexOf("1000PROJECTS\\");
        String tempString = path.substring(beginIndex,path.lastIndexOf(".java"));
        int firstP = tempString.indexOf("\\");
        String projectName = tempString.substring(firstP+1,tempString.lastIndexOf("\\"));
        String getProjectName ="";
        if(projectName.contains("\\")){
            int secondP = projectName.indexOf("\\");
            getProjectName = projectName.substring(0,secondP);
        }else{
            getProjectName = projectName;
        }
        return getProjectName;
    }

    public static void getEachFile(String path){

        ArrayList<String> result = Util.readFile(path);

        for(int i=0; i<result.size(); i++){
            String[] eachLine = result.get(i).split(",");
            String node = eachLine[0];
            String abbr = eachLine[1];
            String apath = eachLine[2];
            String prjName = getProjectName(apath);
            Util.appendFile(node + "," + prjName, "./Data/NameList/"+abbr + ".txt");
            Util.appendFile("\n", "./Data/NameList/"+abbr + ".txt");
        }
    }
}
