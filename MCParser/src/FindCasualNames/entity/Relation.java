package FindCasualNames.entity;

import FindCasualNames.relation.ForVariable;
import FindCasualNames.relation.IdentifierWithType;
import FindCasualNames.relation.Parameter;
import FindCasualNames.relation.RegularVariable;
import FindCasualNames.relation.MethodDeclarationInfo;
import FindCasualNames.relation.ClassName;
import util.Config;

import java.util.ArrayList;
import java.util.HashSet;

public class Relation {
    public int line;
    public String identifierType;
    public String type;
    public String name;
    public String projectName;
    public String methodName;
    public HashSet<String> parameters;
    public HashSet<String> words;
    public String trueLabel = "";
    private static String getProjectNameFromPath(String path) {
        String result = path.substring("C:/PHDONE/ASE19/1000PROJECTS/".length());
        result = result.substring(0, result.indexOf('/'));
        return result;
    }

    public Relation(int line, String identifierType, String path, String type, String name, String methodName, HashSet<String> parameters, HashSet<String> words) {
        this.line = line;
        this.identifierType = identifierType;
        if (path.startsWith("C:/PHDONE/ASE19/1000PROJECTS/")) {
            this.projectName = getProjectNameFromPath(path);
        } else {
            this.projectName = path;
        }
        this.type = type;
        this.name = name;
        this.methodName = methodName;
        this.parameters = parameters;
        this.words = words;
    }

    public static Relation  constructRelationFrom(String line) {
        String[] parts = line.split(Config.fengefu, -1);
        HashSet<String> tempParameters = new HashSet<>();

            for (String part: parts[6].split(" ")) {
                tempParameters.add(part);
            }


        HashSet<String> tempWords = new HashSet<>();
        for (String part: parts[7].split(" ")) {
            tempWords.add(part);
        }
        Relation relation = new Relation(Integer.valueOf(parts[0]), parts[1], parts[4], parts[2], parts[3], parts[5], tempParameters, tempWords);
        relation.trueLabel = parts[8];
        return relation;
    }

    @Override
    public String toString() {
        return String.join(Config.fengefu, String.valueOf(line), identifierType, type, name, projectName, methodName, String.join(" ", parameters), String.join(" ", words), trueLabel);
    }

    public static Relation constructRelationFrom(IdentifierWithType identifierWithType) {
        if (identifierWithType instanceof ForVariable) {
            return constructRelationFrom((ForVariable)identifierWithType);
        } else if (identifierWithType instanceof Parameter) {
            return constructRelationFrom((Parameter)identifierWithType);
        } else if (identifierWithType instanceof RegularVariable) {
            return constructRelationFrom((RegularVariable)identifierWithType);
        } else if (identifierWithType instanceof MethodDeclarationInfo){
            //MethodDeclarationInfo tm=(MethodDeclarationInfo) identifierWithType;
            return constructRelationFrom((MethodDeclarationInfo) identifierWithType);
        } else if (identifierWithType instanceof ClassName){
            return constructRelationFrom((ClassName)identifierWithType);
        }
        assert 0 == 1;
        return null;
    }

    public static Relation constructRelationFrom(ForVariable forVariable) {
        return new Relation(forVariable.getLine(), forVariable.NAME, forVariable.path, "", forVariable.getContent(), "", new HashSet<>(), new HashSet<>());
    }

    public static Relation constructRelationFrom(Parameter parameter) {
        HashSet<String> parameterNames = new HashSet<>();
        for (Parameter temp :
                parameter.parameters) {
            parameterNames.add(temp.getContent());
        }
        return new Relation(parameter.getLine(), parameter.NAME, parameter.path, "", parameter.getContent(), parameter.methodName, parameterNames, new HashSet<>());
    }

    public static Relation constructRelationFrom(RegularVariable regularVariable) {
        return new Relation(regularVariable.getLine(),regularVariable.NAME, regularVariable.path, regularVariable.type, regularVariable.getContent(), regularVariable.methodName, new HashSet<>(), arrayListToHashSet(regularVariable.words));
    }

    public static Relation constructRelationFrom(MethodDeclarationInfo methodDeclarationInfo) {
        //return new Relation(methodDeclarationInfo.getLine(),methodDeclarationInfo.NAME, methodDeclarationInfo.path, methodDeclarationInfo.type,"", "", new HashSet<>(), new HashSet<>());
        return new Relation(methodDeclarationInfo.getLine(),methodDeclarationInfo.NAME, methodDeclarationInfo.path, methodDeclarationInfo.getType(),methodDeclarationInfo.getContent(), methodDeclarationInfo.getParameter().toString().replace(",","#"), new HashSet<>(), new HashSet<>());
    }


    public static Relation constructRelationFrom(ClassName className){
        return new Relation(className.getLine(), className.NAME, className.path, className.getType(),className.getContent(), className.getMethodsName().toString().replace(",","#"), new HashSet<>(), new HashSet<>());
    }

    private static HashSet<String> arrayListToHashSet(ArrayList<String> arrayList) {
        HashSet<String> hashSet = new HashSet<>();
        for (String temp:
                arrayList) {
            hashSet.add(temp);
        }
        return hashSet;
    }
}

