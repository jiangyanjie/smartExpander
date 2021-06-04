package FindCasualNames.relation;

import util.Config;

import java.util.ArrayList;

public class RegularVariable extends IdentifierWithType {
    public static String NAME = "VariableName";
    public String methodName = "";
    public ArrayList<String> words = new ArrayList<>();

    public RegularVariable(int line, String content, String type) {
        super(line, content, type);
    }

    @Override
    public String toString() {
        return NAME + Config.fengefu + super.toString() + Config.fengefu +
                methodName + Config.fengefu + String.join(" ", words);

    }
}
