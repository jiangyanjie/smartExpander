package FindCasualNames.main;

import java.io.File;
import java.util.*;

public class smartExpander {

    public static String dataPath = "./Data/";

    public static ArrayList<String> constructDic(String path){
        ArrayList<String> abbrDic = new ArrayList<>();
        abbrDic = jyjutil.readFile(path);
        return abbrDic;
    }

    public static ArrayList<String> getDataSet(String path){
        ArrayList<String> dataSet = new ArrayList<>();
        dataSet = jyjutil.readFile(path);
        return dataSet;
    }

    public static double propClassName(int x){
        double y = 3.086e-28 * Math.pow(x,20) - 2.284e-25 * Math.pow(x,19) + 7.012e-23 * Math.pow(x,18) - 1.068e-20 * Math.pow(x,17) + 5.65e-19 * Math.pow(x,16) + 7.234e-17 * Math.pow(x,15)
                - 1.379e-14 * Math.pow(x,14) + 3.662e-13 * Math.pow(x,13) + 1.459e-10 * Math.pow(x,12) -2.468e-08 * Math.pow(x,11) + 2.161e-06 * Math.pow(x,10) - 0.0001256 * Math.pow(x,9) +
                0.005172 * Math.pow(x,8) - 0.1538 * Math.pow(x,7) + 3.294 * Math.pow(x,6) - 49.84* Math.pow(x,5) + 514.9 * Math.pow(x,4) - 3457 * Math.pow(x,3) + 1.396e+04 * Math.pow(x,2) -
                2.72e+04 * Math.pow(x,1) + 2.164e+04 * Math.pow(x,0);
        return y;
    }

    public static double propForVariableName(int x){
        double y = -6.789e-25 * Math.pow(x,20) + 5.198e-22 * Math.pow(x,19) - 1.779e-19 * Math.pow(x,18) + 3.536e-17 * Math.pow(x,17) - 4.359e-15 * Math.pow(x,16) + 3.07e-13 * Math.pow(x,15)
                - 3.61e-12 * Math.pow(x,14) - 1.842e-09 * Math.pow(x,13) + 2.422e-07 * Math.pow(x,12) - 1.793e-05 * Math.pow(x,11) + 0.0009212 * Math.pow(x,10) - 0.03481 * Math.pow(x,9) + 0.9857
                * Math.pow(x,8) - 20.95 * Math.pow(x,7) + 331 * Math.pow(x,6) - 3809* Math.pow(x,5) + 3.092e+04 * Math.pow(x,4) - 1.683e+05 * Math.pow(x,3) + 5.672e+05 * Math.pow(x,2)
                - 1.031e+06 * Math.pow(x,1) + 7.663e+05 * Math.pow(x,0);
        return y;
    }


    public static double propMethodName(int x){
        double y = -2.375e-31 * Math.pow(x,20) + 3.721e-28 * Math.pow(x,19) - 2.591e-25 * Math.pow(x,18) + 1.037e-22 * Math.pow(x,17) - 2.516e-20 * Math.pow(x,16) + 3.19e-18 * Math.pow(x,15)
                + 9.564e-17 * Math.pow(x,14) - 1.348e-13 * Math.pow(x,13) + 3.164e-11 * Math.pow(x,12) - 4.55e-09 * Math.pow(x,11) + 4.624e-07 * Math.pow(x,10) - 3.469e-05 * Math.pow(x,9) + 0.001947
                * Math.pow(x,8) - 0.08158 * Math.pow(x,7) + 2.517 * Math.pow(x,6) - 55.72* Math.pow(x,5) + 844.8 * Math.pow(x,4) - 8046 * Math.pow(x,3) + 3.921e+04 * Math.pow(x,2)
                - 3.881e+04 * Math.pow(x,1) + 3298 * Math.pow(x,0);
        return y;
    }

    public static double propParameterName(int x){
        double y = 6.503e-24 * Math.pow(x,20) - 3.362e-21 * Math.pow(x,19) + 7.315e-19 * Math.pow(x,18) - 8.168e-17 * Math.pow(x,17) + 3.806e-15 * Math.pow(x,16) + 1.611e-13 * Math.pow(x,15)
                - 3.151e-11 * Math.pow(x,14) + 1.112e-09 * Math.pow(x,13) + 1.081e-07 * Math.pow(x,12) - 1.634e-05 * Math.pow(x,11) + 0.001103 * Math.pow(x,10) - 0.04859 * Math.pow(x,9) + 1.523
                * Math.pow(x,8) - 34.92 * Math.pow(x,7) + 587.9 * Math.pow(x,6) - 7163* Math.pow(x,5) + 6.113e+04 * Math.pow(x,4) - 3.443e+05 * Math.pow(x,3) + 1.147e+06 * Math.pow(x,2)
                - 1.827e+06 * Math.pow(x,1) + 1.16e+06 * Math.pow(x,0);
        return y;
    }

    public static double propVariableName(int x){
        double y = -3.608e-27 * Math.pow(x,20) + 3.662e-24 * Math.pow(x,19) - 1.659e-21 * Math.pow(x,18) + 4.351e-19 * Math.pow(x,17) - 7.024e-17 * Math.pow(x,16) + 6.286e-15 * Math.pow(x,15)
                - 2.122e-14 * Math.pow(x,14) - 8.322e-11 * Math.pow(x,13) + 1.376e-08 * Math.pow(x,12) - 1.336e-06 * Math.pow(x,11) + 9.103e-05 * Math.pow(x,10) - 0.004582 * Math.pow(x,9) + 0.1732
                * Math.pow(x,8) - 4.92 * Math.pow(x,7) + 103.8 * Math.pow(x,6) - 1589* Math.pow(x,5) + 1.698e+04 * Math.pow(x,4) - 1.189e+05 * Math.pow(x,3) + 4.872e+05 * Math.pow(x,2)
                - 9.51e+05 * Math.pow(x,1) + 9.041e+05 * Math.pow(x,0);
        return y;
    }

    public static int detaJudge(String abbr, String identifier, String property, String expansion){
        if(abbr.length() <=3){
            return 1;
        }
        int judge =1;
        int expn = expansion.length() - abbr.length();
        int expIDE = identifier.length() + expn;
        double deta =-2;
        if(property.equals("forVariableName")){
            double origal = propForVariableName(identifier.length());
            double curr = propForVariableName(expIDE);
            double t1 = (origal - curr);
            if(t1<deta){
                judge =1;
            }else{
                judge =0;
            }
        }

        if(property.equals("variableName")){
            double origal = propVariableName(identifier.length());
            double curr = propVariableName(expIDE);
            double t1 = (origal - curr);
            if(t1<deta){
                judge =1;
            }else{
                judge =0;
            }
        }


        if(property.equals("parameterName")){
            double origal = propParameterName(identifier.length());
            double curr = propParameterName(expIDE);
            double t1 = (origal - curr);
            if(t1<deta){
                judge =1;
            }else{
                judge =0;
            }
        }

        if(property.equals("methodName")){
            double origal = propMethodName(identifier.length());
            double curr = propMethodName(expIDE);
            double t1 = (origal - curr);
            if(t1<deta){
                judge =1;
            }else{
                judge =0;
            }
        }

        if(property.equals("className")){
            double origal = propClassName(identifier.length());
            double curr = propClassName(expIDE);
            double t1 = (origal - curr);
            if(t1<deta){
                judge =1;
            }else{
                judge =0;
            }
        }
        return judge;
    }

    public static int LengthDis(String abbr, String identifier, String property, String expansion){
        int res = detaJudge(abbr,identifier,property,expansion);
        return res;
    }

    public static ArrayList<String> miningSameProject(String prjName){
        ArrayList<String> dataSet = jyjutil.readFile(dataPath+"abbreviation.csv");
        HashMap<String,Integer> commonAbbr = new HashMap<>();

        for(int i=0; i< dataSet.size();i++){
            String[] eachLine = dataSet.get(i).split(",");
            String abbr = eachLine[0];
            String identifier = eachLine[4];
            String comAbbr = abbr+"#"+identifier;

            if(!commonAbbr.containsKey(comAbbr)){
                commonAbbr.put(comAbbr,1);
            }else{
                int num = commonAbbr.get(comAbbr);
                num++;
                commonAbbr.put(comAbbr,num);
            }

        }

        ArrayList<String> key = new ArrayList<>();
        ArrayList<String> total = new ArrayList<>();

        List<Map.Entry<String,Integer>> list = new ArrayList<>(commonAbbr.size());
        list.addAll(commonAbbr.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();		// 升序
            }
        });
        for (Map.Entry<String,Integer> entry : list) {
            key.add(entry.getKey()+","+entry.getValue());
        }
        double length = list.size();
        for(int j=0;j<length;j++){
            String commAbbr = key.get(j).split(",")[0];
            String totalNum = key.get(j).split(",")[1];
            if(Integer.parseInt(totalNum) >25 && commAbbr.split("#")[0].length()>=3){
                total.add(commAbbr);
            }
        }
        return total;
    }


    public static HashMap<String,ArrayList<String>> constructSamePRJabbr(){
        ArrayList<String> prjName = new ArrayList<>();
        prjName.add("davmail");
        prjName.add("docfetcher");
        prjName.add("drjava");
        prjName.add("dubbo");
        prjName.add("findbugs");
        prjName.add("flink");
        prjName.add("jboss");
        prjName.add("jedit");
        prjName.add("jsmooth");
        prjName.add("opennlp");

        HashMap<String,ArrayList<String>> sameList = new HashMap<>();

        for(int i=0;i<prjName.size();i++){
            String name = prjName.get(i);
            ArrayList<String> result = miningSameProject(name);
            sameList.put(name,result);
        }
        for (Map.Entry<String, ArrayList<String>> entry : sameList.entrySet()) {
        }
        return sameList;
    }

    public static int isCommonAbbr(ArrayList<String> dict, String abbr, String identifier, String prjName){
       // HashMap<String,ArrayList<String>> samePrj = constructSamePRJabbr();

        if( dict.contains(abbr)){ //samePrj.get(prjName).contains(abbr+"#"+identifier) ||
            return 0;
        }else{
            return 1;
        }
    }

    public static boolean equalOfWord(String str1, String str2) {
        str1 = str1.trim();
        str2 = str2.trim();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if (str1.length() == 0 || str2.length() == 0) {
            return false;
        }
        if (str1.equals(str2)) {
            return true;
        } else {
            String str1Single = str1;
            String str2Single = str2;
            if (str1.charAt(str1.length()-1) == 's' &&
                    str1.length()>1) {
                str1Single = str1.substring(0, str1.length()-1);
            }
            if (str2.charAt(str2.length()-1) == 's' &&
                    str2.length()>1) {
                str2Single = str2.substring(0, str2.length()-1);
            }
            return str1Single.equals(str2) ||
                    str2Single.equals(str1) ||
                    str1Single.equals(str2Single);
        }
    }

    public static int sameLine(String surroundingIdentifier, String expansion){
        String[] surroundToken = surroundingIdentifier.split("#");
        String[] expansionToken = expansion.split(" ");
        int num =0;
        for(int i=0; i< expansionToken.length;i++){
            String t1 = expansionToken[i];
            for(int j=0;j<surroundToken.length;j++){
                String t2 = surroundToken[j];
                if(equalOfWord(t1,t2)){
                    num++;
                }
            }
        }
        if(num>=expansionToken.length){
            return 0;
        }else{
            return 1;
        }
    }

    public static void main(String[] args) throws Exception{
        ArrayList<String> dict = constructDic(dataPath+"NormalList.txt");

            String abbr = args[0];
            String identifier = args[1];
            String sameLine = args[2];
            String property = args[3];
            String expansion = args[4];
            String prjName = args[5];

            if((isCommonAbbr(dict,abbr,identifier,prjName) == 0 )||(sameLine(sameLine,expansion) == 0)||(LengthDis(abbr, identifier, property, expansion) == 0)) {
                System.out.println("False");
            }else{
                System.out.println("True");
            }
    }
}
