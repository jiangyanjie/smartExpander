package FindCasualNames.entity;

import util.Config;
import util.Util;

import java.util.Objects;

public class RelationKey {
    public String identifierType;
    public String type;
    public String name;
    public RelationKey(String identifierType, String type, String name) {
        this.identifierType = identifierType;
        this.type = type;
        this.name = removeFirstLastNumXiahuaxian(name);
    }

    public static String removeFirstLastNumXiahuaxian(String name) {
        char[] chars = name.toCharArray();
        int i = 0;
        for (; i < chars.length; i++) {
            if (Util.isNum(chars[i])) {
                continue;
            } else if (chars[i] == '_') {
                continue;
            } else {
                break;
            }
        }
        int j = chars.length - 1;
        for (; j >= 0 ; j--) {
            if (Util.isNum(chars[j])) {
                continue;
            } else if (chars[j] == '_') {
                continue;
            } else {
                break;
            }
        }
        if (j >= i) {
            return name.substring(i, j + 1);
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return String.join(Config.fengefu, identifierType, type, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationKey)) return false;
        RelationKey that = (RelationKey) o;
        return Objects.equals(identifierType, that.identifierType) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifierType, type, name);
    }
}
