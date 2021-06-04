package FindCasualNames.relation;

import util.Config;

import java.util.ArrayList;

public class Parameter extends IdentifierWithType {
    public static String NAME = "ParameterName";
    public ArrayList<Parameter> parameters = new ArrayList<>();
    public String methodName = "";

    public Parameter(int line, String content, String type) {
        super(line, content, type);
    }

    @Override
    public String toString() {
        ArrayList<String> parameterNames = new ArrayList<>();
        for (Parameter parameter :
                parameters) {
            parameterNames.add(parameter.getContent());
        }
        return NAME + Config.fengefu + super.toString() + Config.fengefu + methodName + Config.fengefu + String.join(" ", parameterNames);
    }

    public static Parameter constructFrom(RegularVariable regularVariable) {
        return new Parameter(regularVariable.getLine(), regularVariable.getContent(),regularVariable.type);
    }
}
