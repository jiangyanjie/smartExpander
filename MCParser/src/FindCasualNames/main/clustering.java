package FindCasualNames.main;

import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class clustering {


//---------------------get the abbreviation list after filtering----------------------------
    public static ArrayList<String> candidateAbbrList(String path){
        ArrayList<String> result = Util.readFile(path);
        ArrayList<String> abbrList = new ArrayList<>();

        for(int i=0;i< result.size();i++){
            String eachAbbr = result.get(i).split("\t")[0];
            abbrList.add(eachAbbr);
        }
        return abbrList;
    }

//----------------------filter the abbreviation whose total number is greater than 100--------------

    public static HashMap<String, ArrayList<String>> filterAbbr(ArrayList<String> abbrList) throws Exception{

        Connection connection = connectDatabase();
        String sql = "select * from filter where abbr = ?";

        ArrayList<String> result = new ArrayList<>();
        HashMap<String, ArrayList<String>> filteringR = new HashMap<>();
        HashMap<String, String> filterlingPath = new HashMap<>();


        for(int i=0;i < abbrList.size();i++){
            int n=0;
            result.clear();
//            filteringR.clear();
            filterlingPath.clear();
            String abbr = abbrList.get(i);
            System.out.println(abbr);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, abbr);
            System.out.println("==="+ps);
            System.out.println("=+++==" + ps.toString().split(":")[1].replace("\"", ""));
            ResultSet rs = ps.executeQuery(ps.toString().split(":")[1].replace("\"", ""));

            while(rs.next()){
                String temp1 = rs.getString(1);
                String temp2 = rs.getString(2);
                String temp3 = rs.getString(3);
                result.add(temp1 + "," + temp2 + "," + temp3);
//                Util.appendFile(temp1 + "," + temp2 + "," + temp3+ "\n", "D:\\FSE2021\\abbr\\"+temp1+".txt");
            }

            for(int j=0;j<result.size();j++){
                String tempPath = result.get(j).split(",")[1];
                System.out.println("path===== " + tempPath);
                String tempAbbr = result.get(j).split(",")[0];
                String tempVector = result.get(j).split(",")[2];
                if(!filterlingPath.containsKey(tempPath)){ // 当同一个java文件中，相同的缩写词出现多次只保留一个
                    filterlingPath.put(tempPath,tempAbbr);
                   Util.appendFile(n + "," + tempAbbr + "," + tempPath + "," + tempVector + "\n", "D:\\FSE2021\\abbr\\filterAbbr\\"+tempAbbr+".txt");
                   n++;
                }

            }
        }
        return filteringR;
    }

//----------------------connect the database----------------------

    public static Connection connectDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/fse2021"; // URL
        String user = "root"; //User
        String password = "root"; //Password

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if(!connection.isClosed())
            {
                System.out.println("Succeeded connecting to the Database!");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;

    }

    //------------------------------------------------------------------------------------------------------------------
    //string to double[] 向量从一个string 转化为double[]
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
    //based on the path, get the enclosing project name
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

    public static boolean isInSameProject(String path1, String path2){
//        String projectName1 = getProjectName(path1);
//        String projectName2 = getProjectName(path2);

        if(path1.equals(path2)){
            return true;
        }else{
            return false;
        }
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


    public static HashMap<String, ArrayList<String>> miningAbbr(ArrayList<String> filteredAbbr){

        HashMap<String, ArrayList<String>> result = new HashMap<>();

        for(int i=0;i< filteredAbbr.size();i++){
            String eachLine = filteredAbbr.get(i);
            String[] splitEachLine = eachLine.split(",");
            String abbr = splitEachLine[0];
            String path = splitEachLine[1];
            String vector = splitEachLine[2];

            double[] myvector = changString2Double(vector);
            String projectName1 = getProjectName(path);

            if(!result.containsKey(abbr)){
                ArrayList<String> temp = new ArrayList<>();
                temp.add(path+"##"+vector);
                result.put(abbr,temp);
            }else{
                // calculate similarity between different vectors
                ArrayList<String> value = result.get(abbr);
                double similarity=0;
                for(int j=0;j<value.size();j++){
                    String neachLine = value.get(j);
                    String[] splitResult = neachLine.split("##");

                    String projectName2 = getProjectName(splitResult[0]);
//                    if(!isInSameProject(path,splitResult[0])){
                    if(!isInSameProject(projectName1,projectName2)){
                        String vector2 = splitResult[1];
                        double[] myvector2 = changString2Double(vector2);
                        similarity += cosineSimilarity(myvector,myvector2);

                    }
                }
                double newSimilarity = (similarity*1.0)/value.size();
                if(newSimilarity >= 0.3){
                    ArrayList<String> temp1 = new ArrayList<>();
                    temp1.add(path+"##"+vector);
                    value.addAll(temp1);
                    result.put(abbr,value);
                }
            }

        }

        return result;
    }

//    public static void main(String[] args) throws Exception{
//         String abbrListPath = "D:\\FSE2021\\temp.txt";
//         String filterAbbrPath = "D:\\FSE2021\\finalfilter.txt";
//        ArrayList<String> abbrList = candidateAbbrList(abbrListPath);
//        HashMap<String, ArrayList<String>> filterAbbr = filterAbbr(abbrList);
//    }

}
