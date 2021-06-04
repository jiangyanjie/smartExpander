package util;

import java.util.ArrayList;

public class Heu {
    public static String H1(String abbr, ArrayList<String> terms) {
        if (abbr.length() == 1) {
            return null;
        }
        if (abbr.length() > terms.size()) {
            return null;
        }

        for (int i = 0; i <= terms.size() - abbr.length(); i++) {
            String ics = "";

            for (int j = i; j < abbr.length() + i; j++) {
                String term = terms.get(j);
                ics = ics + term.charAt(0);
            }
            if (abbr.equals(ics)) {
                String temp = "";
                boolean found = true;
                for (int j = i; j < abbr.length() + i; j++) {
                    String term = terms.get(j);
                    if (!Dic.isInDict(term)) {
                        found = false;
                        break;
                    }
                    temp = temp + term;
                }
                if (found) {
                    String expansion = temp;
                    return expansion;
                }
            }
        }
        return null;
    }

    public static String H2(String abbr, ArrayList<String> terms) {
        if (H1(abbr, terms) != null) {
            return null;
        }
        ArrayList<String> possibleExpansions = new ArrayList<String>();
        for (String term : terms) {
            if (term.startsWith(abbr) && Dic.isInDict(term)) {
                possibleExpansions.add(term);
            }
        }
        if (possibleExpansions.size() == 0) {
            return null;
        }

        String expansion = "";
        for (String possibleExpansion : possibleExpansions) {
            expansion += possibleExpansion + "#";
        }
        return expansion;
    }


    public static boolean canHeuComment(String part, String comment, String H) {
        String result = "";
        comment = getWords(comment);
        String[] words = comment.split(" ");

        ArrayList<String> dicWordList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() != 0 && Dic.isInDict(words[i])) {
                dicWordList.add(words[i]);
            }
        }

        switch (H) {
            case "H1":
                if (dicWordList.size() >= part.length()) {
                    for (int i = 0; i <= dicWordList.size() - part.length(); i++) {
                        boolean found = true;
                        String expansion = "";
                        for (int j = 0; j < part.length(); j++) {
                            if (part.charAt(j) != dicWordList.get(i + j).charAt(0)) {
                                found = false;
                                break;
                            }
                            expansion += dicWordList.get(i + j);
                        }
                        if (found == true) {
                            result += expansion + ";";
                        }
                    }
                }
                break;
            case "H2":
                for (int i = 0; i < words.length; i++) {
                    if (words[i].length() != 0) {
                        String expansion = Heu.H2(part, Util.split(words[i]));
                        if (expansion != null) {
                            result += expansion + ";";
                        }
                    }
                }
                break;
        }
        return result.length() != 0;
    }

    public static String getWords(String comment) {
        String result = "";
        char[] chars = comment.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Util.isLetter(chars[i])) {
                result += chars[i];
            } else {
                result += " ";
            }
        }
        while (result.contains("  ")) {
            result = result.replaceAll("  ", " ");
        }
        return result;
    }

    public static boolean canHeuType(String part, String identifier, String H) {
        String expansion = null;
        switch (H) {
            case "H1":
                expansion = Heu.H1(part, Util.split(identifier));
                break;
            case "H2":
                expansion = Heu.H2(part, Util.split(identifier));
                break;
        }

        if (expansion == null) {
            if (part.length() > 1 && part.charAt(part.length() - 1) == 's') {
                String singlePart = part.substring(0, part.length() - 1);
                switch (H) {
                    case "H1":
                        expansion = Heu.H1(singlePart, Util.split(identifier));
                        break;
                    case "H2":
                        expansion = Heu.H2(singlePart, Util.split(identifier));
                        break;
                }
            }
        }
        return expansion != null;
    }

    public static void main(String[] args) {
        System.out.println(H2("pref", Util.split("SharedPreferences")));
    }
}
