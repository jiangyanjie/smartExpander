package FindCasualNames.calculate;

import util.Dic;
import util.Util;

import java.util.ArrayList;

public class calculIdentifierNum {

    public static void todo(){
        ArrayList<String> dataSet = Util.readFile("./Data/identifier.csv");
        int num=0;
        for(int i=0;i<dataSet.size();i++){

            String[] lineInfo = dataSet.get(i).split(",");
            String identifier = lineInfo[3];
            ArrayList<String> identifierToken = Util.split(identifier);
            for(int j=0;j< identifierToken.size();j++){
                String token = identifierToken.get(j);
                if(!(Dic.isInDictToken(token))){
                    num++;
                    break;
                }
            }
        }
        System.out.println(num);
    }

    public static void main(String[] args){

        todo();

    }
}
