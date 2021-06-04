package FindCasualNames.main;

import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class filterClique {

    public static String filterByNum(String str){
        String[] temp = str.split(" ");
        String res="";
        if(temp.length>=15){
           res = str;
        }
      return res;
    }

    public static String removeTxt(String name){
        int index = name.indexOf(".txt");
        return name.substring(0,index);
    }

    public static HashMap<String, ArrayList<String>> constructNameList(){
        File file = new File("./Data/FSE2021/NameList");

        HashMap<String, ArrayList<String>> result = new HashMap<>();

        for(File oneFile : file.listFiles()){

            String name = removeTxt(oneFile.getName());
            String tempPath = oneFile.getPath();
            ArrayList<String> context = Util.readFile(tempPath);
            result.put(name,context);
        }

//        for (Map.Entry<String, ArrayList<String>> entry : result.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
//        }
        return result;
    }

    public static String filterPrj4Clique(String eachLine, HashMap<String, ArrayList<String>> nameList, String name){

        String[] eachToken = eachLine.split(" ");
        String abbr = removeTxt(name);
        ArrayList<String> value = nameList.get(abbr);

        ArrayList<String> res = new ArrayList<>();

        String ret="";

        for(int i=0;i<eachToken.length;i++){
            String node = eachToken[i];
            for(int j=0;j< value.size();j++){

                String[] valueLine = value.get(j).split(",");
                String v_node = valueLine[0];
                String v_methodName = valueLine[1];

                if(node.equals(v_node) && (!res.contains(v_methodName))){
                    res.add(v_methodName);
                }
            }
        }
        if(res.size()>=15){
            ret = eachLine;
        }
        return ret;
    }

    public static void filterNum4Clique (String path ){

        File file = new File(path);
        ArrayList<String> originalData = new ArrayList<>();

        for (File oneFile : file.listFiles()) {
            originalData.clear();
            String tpath = oneFile.getPath();
            originalData = Util.readFile(tpath);
            String name = oneFile.getName();
            System.out.println(name);
            for(int i=0;i< originalData.size();i++){
                String eachLine = originalData.get(i);
                String filter1R = filterByNum(eachLine);

                if(filter1R.split(" ").length > 1){
                    String res = filterPrj4Clique(filter1R,constructNameList(),name);
                    if(res.length()!=0)
                    {
                        Util.appendFile(res, "./Data/clique/4/filter2R/" + name);
                        Util.appendFile("\n", "./Data/clique/4/filter2R/" + name);
                    }
                }
            }
        }
    }
//    public static void main(String[] args){
//        String path = "D:\\FSE2021\\clique\\0.4\\remaining";
//        filterNum4Clique(path);
//    }
}
