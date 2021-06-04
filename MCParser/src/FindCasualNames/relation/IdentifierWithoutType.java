package FindCasualNames.relation;

import util.Config;

public class IdentifierWithoutType {
    private int line;
    private String content;

    public IdentifierWithoutType(int line, String content) {
        this.line = line;
        this.content = content;
    }

    @Override
    public String toString() {
        return line + "," + content;
    } //解析所有标识符时需要改成逗号

    public int getLine() {
        return line;
    }

    public String getContent() {
        return content;
    }
}
