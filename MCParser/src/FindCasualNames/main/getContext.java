package FindCasualNames.main;


import FindCasualNames.relation.Identifier;
import util.Dic;
import util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class getContext {

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        String path = "./Data/evaluation/RQ5/filepath200.csv";
        String pathcontext = "./Data/evaluation/RQ5/filecontext.csv";
        String context = "./Data/evaluation/RQ5/context.csv";
        Util.deleteIfExist(new File(pathcontext));
        Util.deleteIfExist(new File(context));
        File file = new File(path);
        BufferedReader reader;
        int i=0;

        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (!tempString.equals("")) {
                    ArrayList<Identifier> elements = Step3_getAllIdentifiers.parseAllidentifier(tempString);
                    Util.appendFile(tempString + "," +elements.toString().replace(",", " ").replace("[","").replace("]","") + "\n", pathcontext);
                    Util.appendFile(elements.toString().replace(",", " ").replace("[","").replace("]","") + "\n", context);
                    i++;
                    System.out.println(i + "/434375");
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println(time);
    }
}
