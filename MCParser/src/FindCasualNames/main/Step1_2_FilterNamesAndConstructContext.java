package FindCasualNames.main;

import FindCasualNames.entity.Relation;
import FindCasualNames.relation.IdentifierWithType;
import util.Dic;
import util.Heu;
import util.Util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Step1_2_FilterNamesAndConstructContext {
    public static int contextLine = 2;
    public static String filteredName = Setting.outFile;
    public static String filteredAbbrName = Setting.filteredAbbrName;
    public static String perparedData = "./Data/perparedDatan.csv";
    public static String resultWithContext = "./Data/resultWithContext.csv";
    public static String allContextPath = "./Data/evaluation/RQ5/project1.csv";

    public static int totalNum = 0;
    public static int criterion1Num = 0;
    public static int criterion2Num = 0;
    public static int heuNum = 0;
    public static int dicNum = 0;

    public static void parseOneFile(ArrayList<IdentifierWithType> identifierWithTypes) throws Exception{
        printRecords(identifierWithTypes, Setting.outFile);
    }

    private static void printRecords(ArrayList<IdentifierWithType> identifierWithTypes, String filteredName) throws Exception {
        for (IdentifierWithType identifierWithType :
                identifierWithTypes) {
            totalNum++;
            if (criterion1(identifierWithType.getContent())) {
                criterion1Num++;
                if (criterion2(identifierWithType.getContent())) {
                    criterion2Num++;
                    if (canNotHeu(identifierWithType.getContent(), identifierWithType.type, identifierWithType.comment)) {
                        heuNum++;
                        if (canNotDic(identifierWithType.getContent())) {
                            dicNum++;
                            print(Relation.constructRelationFrom(identifierWithType) + "\n", filteredName);
                        }
                    }
                }
            }
        }
    }

    private static boolean canNotHeu(String name, String type, String comment) {
       return true;
    }

    private static boolean canNotHeuComment(String name, String comment) {
        return true;
    }

    private static boolean canNotHeuType(String name, String type) {
        return true;
    }

    private static boolean canNotDic(String name) {
        return true;
    }

    private static boolean criterion2(String name) {
        return true;
    }

    private static boolean criterion3(String name) {
        ArrayList<String> tokens = Util.split(name);
        for (String token :
                tokens) {
            if (token.length() == 1) {
                return true;
            }
            if (token.length() >= 2) {
                if (!Dic.isInDict(token)) {
                    return true;
                }
            }
        }
        return false;

    }

    private static boolean criterion1(String name) {
          return true;
    }

    private static void print(String name, String fileName) throws Exception {
        BufferedWriter out;
        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, true)));
        out.write(name);
        out.flush();
        out.close();
    }
}
