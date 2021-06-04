package FindCasualNames.main;


import FindCasualNames.relation.Identifier;
import util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Step_3_1_getAllFileIdentifiers {

    public static void main(String[] args) throws Exception{

        String resultWithContext = Step1_2_FilterNamesAndConstructContext.resultWithContext;
        String allContextPath = Step1_2_FilterNamesAndConstructContext.allContextPath;
        Util.deleteIfExist(new File(allContextPath));

        Set<String> javaFilePaths = getAllJavaFilePath(resultWithContext);

        for(String eachPath:javaFilePaths)
        {
            ArrayList<Identifier> elements = Step3_getAllIdentifiers.parseAllidentifier(eachPath);
            Util.appendFile(elements.toString().replace(",", " ").replace("[", "").replace("]","") + "\n",allContextPath);
        }
    }

    public static Set<String> getAllJavaFilePath(String resultWithContext){

        ArrayList<String> data = Util.readFile(resultWithContext);
        Set<String> javaFilePaths = new HashSet<String>();

        for(int i=0;i< data.size();i++)
        {
            String javaFilePath = data.get(i).split(",")[5];
            javaFilePaths.add(javaFilePath);
        }
        return javaFilePaths;
    }
}
