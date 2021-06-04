package FindCasualNames.relation;

import java.util.ArrayList;

public class ClassName extends IdentifierWithType{

    public static String NAME = "ClassName";
    private ArrayList<MethodDeclarationInfo> methodNames;

    private int line;
    private String content;
    private String type;

    public ClassName(int line, String content, String type, ArrayList<MethodDeclarationInfo> methodNames){

        super(line, content, type);
        this.methodNames = methodNames;
        this.line = line;
        this.content = content;
        this.type = type;
    }

    public int getLine(){
        return line;
    }

    public String getContent(){
        return content;
    }

    public String getType(){
        return type;
    }

    public ArrayList<MethodDeclarationInfo> getMethodsName(){
        return methodNames;
    }

    @Override
    public String toString(){
        return NAME + super.toString();
    }

}
