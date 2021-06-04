package FindCasualNames.main;

import util.Util;

import java.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;


public class miningAbbrInTheSameProject {

    public static ArrayList<String> miningSameProject(String prjName){
        ArrayList<String> dataSet = Util.readFile("./Data/abbreviation.csv");
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
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        for (Entry<String,Integer> entry : list) {
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
        return sameList;
    }
    public static void main(String[] args){
        constructSamePRJabbr();
    }
}
