package FindCasualNames.entity;

import util.Config;

import java.util.HashSet;

public class Features {
    public HashSet<String> methods;
    public HashSet<String> parameters;
    public HashSet<String> words;

    public Features(HashSet<String> methods, HashSet<String> parameters, HashSet<String> words) {
        this.methods = methods;
        this.parameters = parameters;
        this.words = words;
    }

    @Override
    public String toString() {
        return String.join(Config.fengefu, String.join(" ", methods),String.join(" ", parameters), String.join(" ", words));
    }

    public static Features constructFrom(String methods, String parameters, String words) {
        HashSet<String> tempMethods = new HashSet<>();
        HashSet<String> tempParameters = new HashSet<>();
        HashSet<String> tempWords = new HashSet<>();
        for (String part :
                methods.split(" ")) {
            tempMethods.add(part);
        }
        for (String part :
                parameters.split(" ")) {
            tempParameters.add(part);
        }
        for (String part :
                words.split(" ")) {
            tempWords.add(part);
        }
        return new Features(tempMethods, tempParameters, tempWords);
    }
}
