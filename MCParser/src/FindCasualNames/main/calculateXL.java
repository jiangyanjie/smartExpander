package FindCasualNames.main;

import util.Util;

import java.util.ArrayList;

public class calculateXL {

    public static void calculPR(ArrayList<String> list){
        int NE=0;
        int AE = 0;
        int AC = 0;
        int JE = 0;
        int falseJ =0;
        int falseT =0;
        int jAE =0;
        int jJE =0;
        int jNE =0;
        for(int i=0;i<list.size();i++){
            String[] eachLine = list.get(i).split(",");
            String label = eachLine[9];
            String judge = eachLine[10];

            if(label.equals("0") && judge.equals("1")){
                falseJ++;
                System.out.println(eachLine[6] + "====" + eachLine[7]);
            }

            if(label.equals("1")){
                NE++;
            }
            if(label.equals("0")){
                jNE++;
            }
            if(label.equals("1") && (judge.equals("1"))){
                AE++;
            }
            if(label.equals("0") && (judge.equals("0"))){
                jAE++;
            }
            if(label.equals(judge)){
                AC++;
            }
            if(judge.equals("1")){
                JE++;
            }
            if(judge.equals("0")){
                jJE++;
            }
            if(label.equals("1") && judge.equals("0"))
            {
                falseT++;
//                System.out.println("**** "+ eachLine[3]);
            }
        }

        double accuracy = (AC*1.0)/(list.size());
        double precision = (AE*1.0)/JE;
        System.out.println("AE" + AE);
        System.out.println("JE" + JE);

        double recall = (AE*1.0)/NE;
//        System.out.println("====="+falseJ);
        System.out.println("accuracy : " + accuracy + "   " + "precision : "+ precision + "    " + "recall : " + recall);
        System.out.println("correct decision" + AE +" "+ "incorrect" + falseT);

        //label =0
        System.out.println("==================================================================");
        double precisionJ =(jAE*1.0)/jJE;
        double recallJ =(jAE*1.0)/jNE;
        System.out.println("precision : " + precisionJ + " recall : " + recallJ);
        System.out.println("correct decision" + jAE +"  " + "incorrect" + falseJ);

    }

//    public static void main(String[] args){
//        ArrayList<String> list = Util.readFile("D:\\FSE2021\\dataSet\\rebuttal\\FINAL.csv");//FINAL.csv
//        calculPR(list);
//    }
}
