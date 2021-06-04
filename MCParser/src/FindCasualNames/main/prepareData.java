package FindCasualNames.main;

import util.Dic;
import util.Util;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import FindCasualNames.relation.Identifier;


public class prepareData {

    public static void main(String[] args) throws Exception{

        Util.deleteIfExist(new File(Step1_2_FilterNamesAndConstructContext.filteredAbbrName));
        Util.deleteIfExist(new File(Step1_2_FilterNamesAndConstructContext.perparedData));
        String path = Step1_2_FilterNamesAndConstructContext.filteredName;
        String abbrPath = Step1_2_FilterNamesAndConstructContext.filteredAbbrName;
        String perparedData = Step1_2_FilterNamesAndConstructContext.perparedData;
        int i=0;

        File file = new File(path);
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                if (!tempString.equals("")) {
                  String[] eachLine = tempString.split(",");
                  String nPath = eachLine[4];
                  String oneIdentifier = eachLine[3];
                  ArrayList<String> tokens = Util.split(oneIdentifier);
                  for(String token: tokens){
                      if(!(Dic.isInDictToken(token))){
                          ArrayList<Identifier> elements = Step3_getAllIdentifiers.parseAllidentifier(nPath);
                              Util.appendFile(token + "," +oneIdentifier+ ","+elements.toString().replace(",", " ") + "\n", abbrPath);
                              Util.appendFile(elements.toString().replace(",", " ") + "\n", perparedData);
                              i++;
                      }
                  }
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
