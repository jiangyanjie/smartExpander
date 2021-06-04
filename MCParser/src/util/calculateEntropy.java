package util;

import java.util.ArrayList;

public class calculateEntropy {

   public static void entropy() {
        ArrayList<String> result = Util.readFile("D:\\EmpiricalStudy\\EmpiricalStudy\\logInfo\\file.cache.ngram.order.file.dynamic.lambda.log");
       int num = 0;
       double entropy = 0.0;

        for(int k=0;k<result.size();k++){
            System.out.println(result.get(k));
        }
//        for (int i = 1381; i <= 1470; i++) {
//            if (result.get(i).startsWith("prob(jyj)")) {
//                num++;
//                entropy += Double.parseDouble(result.get(i).split(": ")[1]);
//
//            }
//
//        }

//        System.out.println("===================");
//        for (int j = 4435; j <= 4474; j++) {
//            if (result.get(j).startsWith("prob(jyj)")) {
//                num++;
//                entropy += Double.parseDouble(result.get(j).split(": ")[1]);
//
//            }
//        }

//        System.out.println("number : " + num);
//        System.out.println("entropy : " + entropy);
//        System.out.println("average entropy: " + -entropy/num);
    }



    public static void main (String[] args){

        entropy();


    }


}
