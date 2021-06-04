package FindCasualNames.relation;

import java.util.List;

public class MethodDeclarationInfo extends IdentifierWithType {

    public static String NAME = "MethodName";
    private List parameters;

    private int line;
    private String content;
    private String type;


    public MethodDeclarationInfo(int line, String content, String type, List parameters) {
        super(line, content,type);
        this.parameters = parameters;
        this.line=line;
        this.content=content;
        this.type=type;
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

    public List getParameter(){
        return parameters;
    }

    @Override
    public String toString(){
        return NAME + super.toString();
    }

}
