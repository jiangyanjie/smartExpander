package FindCasualNames.main;

import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class constructData4Clique {

    public static void getFilewithProgress(String path){

        File file = new File(path);
        ArrayList<String> paths = new ArrayList<>();
        for (File oneFile : file.listFiles()) {
            paths.add(oneFile.getPath());
        }
        Collections.reverse(paths);
        for (String tempPath : paths) {
            System.out.println(tempPath);
            ArrayList<String> result1 = getData(tempPath,0.2);
            String abbrt = tempPath.substring(tempPath.lastIndexOf("\\"),tempPath.lastIndexOf(".txt"));
            print2file(result1,"./Data/clique/threshold/0.2/inputData/"+ abbrt+".txt");
        }
    }

    public static ArrayList<String> getData (String filePath, double threshold){

        ArrayList<String> file_data = Util.readFile(filePath);
        ArrayList<String> edge = new ArrayList<>();
        edge.add(""+file_data.size());
        for(int i=0; i< file_data.size();i++)
        {
            String ID1 = file_data.get(i).split(",")[0];
            String vector1 = file_data.get(i).split(",")[3];
            double[] v1 = changString2Double(vector1);
            for(int j=i+1;j<file_data.size();j++){
                String ID2 = file_data.get(j).split(",")[0];
                String vector2 = file_data.get(j).split(",")[3];
                double[] v2 = changString2Double(vector2);
                double similarity = cosineSimilarity(v1,v2);
                if(similarity > threshold){
                    edge.add(ID1 + "," + ID2);
                    edge.add(ID2 + "," + ID1);
                }
            }
        }
        return edge;
    }

    public static void print2file(ArrayList<String> info, String outpuPath){
        int edge_size = info.size()-1;
        Util.appendFile(info.get(0),outpuPath);
        Util.appendFile("\n", outpuPath);
        Util.appendFile(""+edge_size,outpuPath);
        Util.appendFile("\n", outpuPath);
        for(int i=1;i<info.size();i++){
            Util.appendFile(info.get(i), outpuPath);
            Util.appendFile("\n", outpuPath);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
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

//    public static void main(String[] args){
//        System.out.println("start====");
//        getFilewithProgress("D:\\FSE2021\\abbr\\filterAbbr");
//    }

}
