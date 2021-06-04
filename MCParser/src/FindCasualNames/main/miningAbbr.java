package FindCasualNames.main;

import FindCasualNames.relation.Identifier;
import util.Dic;
import util.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class miningAbbr  {

    public static void main(String[] args) throws Exception{
        HashMap<String, ArrayList<String>> result = getAbbrInDiffProject();
        for(String key:result.keySet()){
            ArrayList<String> value = result.get(key);
            System.out.println(key + "==" + value.toString());
        }

    }

    public static String getProjectName(String path){
            int beginIndex = path.lastIndexOf("1000PROJECTS\\");
            String tempString = path.substring(beginIndex,path.lastIndexOf(".java"));
            int firstP = tempString.indexOf("\\");
            String projectName = tempString.substring(firstP+1,tempString.lastIndexOf("\\"));
            //extract project name
           int secondP = projectName.indexOf("\\");
           String getProjectName = projectName.substring(0,secondP);
           return getProjectName;
    }

    //------------------------------------------------------------------------------------------------------------------
    // based on the path, get the corresponding vector
    public static String getVector(String filepath) throws Exception{
        // based on file to get the vector
        String path2vectorPath = "./Data/context/path2vector.csv";
        String returnValue="";
        File file = new File(path2vectorPath);
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (!tempString.equals("")) {
                    String[] eachLine = tempString.split(",");
                    String vector = eachLine[0];
                    String path = eachLine[1];
                    if(filepath.equals(path)){
                        returnValue = vector;
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public static HashMap<String, ArrayList<String>> getAbbrInDiffProject() throws Exception{
        HashMap<String, ArrayList<String>> result = new HashMap<>();
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------
    public static boolean isInSameProject(String path1, String path2){
        if(path1.equals(path2)){
            return true;
        }else{
            return false;
        }
    }

    public static double[] changString2Double(String vector){
        String[] myvector = vector.split(" ");
        double[] finalVector = readVector(myvector);
        return finalVector;
    }

    public static double[] readVector(String[] myvecotr){
        double[] getVector = new double[300];
        for(int j=0;j<myvecotr.length;j++){
            getVector[j] = Double.parseDouble(myvecotr[j]);
        }
        return getVector;
    }

    //------------------------------------------------------------------------------------------------------------------
    // calculate similarity between two vectors
    public static double cosineSimilarity(double[] vectorA, double[] vectorB){
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
