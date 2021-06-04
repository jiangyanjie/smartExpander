package FindCasualNames.relation;

import java.util.ArrayList;

public class FieldName extends IdentifierWithType {

    public static String NAME = "FieldName";
    public String methodName = "";
    public ArrayList<String> words = new ArrayList<>();


    public FieldName(int line, String content, String type, ArrayList<MethodDeclarationInfo> methodNames){
        super(line, content,type);

    }



}
