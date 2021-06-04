package FindCasualNames.paragraphVector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CalculateSimilarity {

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

    public static ArrayList<double[]> getInfo(String vectorPath){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<double[]> finalResult = new ArrayList<>();
        File file = new File(vectorPath);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (!tempString.equals("")) {
                    result.add(tempString);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0;i<result.size();i++){
            String[] myvector =result.get(i).split(" ");
            double[] finalVector = readVector(myvector);
            finalResult.add(finalVector);
        }
        return finalResult;
    }


    public static double[] readVector(String[] myvecotr){
            double[] getVector = new double[300];
            for(int j=0;j<myvecotr.length;j++){
                getVector[j] = Double.parseDouble(myvecotr[j]);
            }
        return getVector;
    }
}
