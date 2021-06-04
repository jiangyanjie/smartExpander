package FindCasualNames.relation;

import util.Config;

public class ForVariable extends IdentifierWithType {
    public static String NAME = "ForVariableName";

    public ForVariable(int line, String content, String type) {
        super(line, content, type);
    }

    @Override
    public String toString() {
        return NAME + Config.fengefu + super.toString();
    }

    public static ForVariable constructFrom(RegularVariable regularVariable) {
        return new ForVariable(regularVariable.getLine(), regularVariable.getContent(),regularVariable.type);
    }
    public static ForVariable constructFrom(String line) {
        return null;
    }
}
