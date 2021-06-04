package FindCasualNames.relation;

import util.Config;

public class IdentifierWithType extends IdentifierWithoutType {
    public String type;
    public String comment = "";
    public String path = "";

    public IdentifierWithType(int line, String content, String type) {
        super(line, content);
        this.type = handleType(type);
    }

    private static String handleType(String str) {
        int temp1 = str.indexOf("<");
        int temp2 = str.lastIndexOf(">");

        if (temp1 != -1 && temp2 != -1) {
            str = str.substring(0, temp1) + str.substring(temp2 + 1);
        }
        temp1 = str.indexOf("[");
        temp2 = str.lastIndexOf("]");
        if (temp1 != -1 && temp2 != -1) {
            str = str.substring(0, temp1) + str.substring(temp2 + 1);
        }
        return str;
    }

    @Override
    public String toString() {
        return path + Config.fengefu + super.toString() + Config.fengefu + type + Config.fengefu + comment;
    }
}
