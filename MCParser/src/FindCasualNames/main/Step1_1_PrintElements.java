package FindCasualNames.main;

import FindCasualNames.relation.Comment;
import FindCasualNames.relation.ForVariable;
import FindCasualNames.relation.IdentifierWithType;
import FindCasualNames.relation.IdentifierWithoutType;
import FindCasualNames.relation.Method;
import FindCasualNames.relation.Parameter;
import FindCasualNames.relation.RegularVariable;
import FindCasualNames.relation.MethodDeclarationInfo;
import FindCasualNames.relation.ClassName;
import java.util.ArrayList;
import java.util.HashSet;



public class Step1_1_PrintElements {
    public ArrayList<IdentifierWithoutType> elements = new ArrayList<>();

    public static void printElements(ArrayList<IdentifierWithoutType> elements, String path) throws Exception {
        ArrayList<IdentifierWithType> elementsNeedPrint = getElementsNeedPrint(elements);
        ArrayList<Comment> comments = getComments(elements);
        ArrayList<RegularVariable> regularVariables = getRegularVariable(elementsNeedPrint);
        ArrayList<IdentifierWithoutType> identifierWithoutTypes = getAllIdentifierWithoutType(elements);

        constructContext(regularVariables, elementsNeedPrint, identifierWithoutTypes);
        linkComment(elementsNeedPrint, comments);

        for (IdentifierWithType element :
                elementsNeedPrint) {
            element.path = path;
//            System.out.println(element);
//            Util.appendFile(Config.projectName + Config.fengefu + element + "\n");
        }


        Step1_2_FilterNamesAndConstructContext.parseOneFile(elementsNeedPrint);
    }


    private static ArrayList<Comment> getComments(ArrayList<IdentifierWithoutType> elements) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (IdentifierWithoutType element : elements) {
            if (element instanceof Comment) {
                comments.add((Comment) element);
            }
        }
        return comments;
    }

    //提取出变量的context文本
    private static void constructContext(ArrayList<RegularVariable> regularVariables, ArrayList<IdentifierWithType> elementsNeedPrint, ArrayList<IdentifierWithoutType> identifierWithoutTypes) {
        for (RegularVariable regularVariable :
                regularVariables) {
            for (IdentifierWithoutType identifierWithoutType : elementsNeedPrint) {
                if (identifierWithoutType.getLine() != regularVariable.getLine() &&
                        Math.abs(identifierWithoutType.getLine() - regularVariable.getLine()) <= Setting.contextLine) {
                    regularVariable.words.add(identifierWithoutType.getContent());
                }
            }
            for (IdentifierWithoutType identifierWithoutType : identifierWithoutTypes) {
                if (identifierWithoutType.getLine() == regularVariable.getLine()) {
                    regularVariable.words.add(identifierWithoutType.getContent());
                }
            }
        }
    }

    private static ArrayList<RegularVariable> getRegularVariable(ArrayList<IdentifierWithType> elementsNeedPrint) {
        ArrayList<RegularVariable> result = new ArrayList<>();
        for (IdentifierWithoutType element :
                elementsNeedPrint) {
            if (element instanceof Method) {
                result.addAll(((Method) element).getVariables());
            } else if (element instanceof RegularVariable) {
                result.add((RegularVariable) element);
            }
        }
        return result;
    }


    private static ArrayList<IdentifierWithType> getElementsNeedPrint(ArrayList<IdentifierWithoutType> elements) {
        ArrayList<IdentifierWithType> result = new ArrayList<>();
        for (IdentifierWithoutType element :
                elements) {
            if (element instanceof Method) {
                Method method = (Method) element;
                for (Parameter parameter : method.getParameters()) {
                    parameter.parameters.addAll(method.getParameters());
                    parameter.methodName = method.getName();
                }
                for (RegularVariable regularVariable : method.getVariables()) {
                    regularVariable.methodName = method.getName();
                }
                result.addAll(method.getParameters());
                result.addAll(method.getVariables());

            } else if (element instanceof RegularVariable) {
                result.add((RegularVariable) element);
            } else if (element instanceof ForVariable) {
                result.add((ForVariable) element);
            } else if (element instanceof MethodDeclarationInfo){
                result.add((MethodDeclarationInfo) element);
            } else if (element instanceof ClassName){
                result.add((ClassName) element);
            }
        }
        return result;
    }


    private static ArrayList<IdentifierWithoutType> getAllIdentifierWithoutType(ArrayList<IdentifierWithoutType> elements) {
        ArrayList<IdentifierWithoutType> identifierWithoutTypes = new ArrayList<>();
        for (IdentifierWithoutType element :
                elements) {
            if (element.getClass().equals(IdentifierWithoutType.class)) {
                identifierWithoutTypes.add(element);
            }
        }
        elements.removeAll(identifierWithoutTypes);
        return identifierWithoutTypes;
    }

    private static void linkComment(ArrayList<IdentifierWithType> elementsNeedPrint, ArrayList<Comment> comments) {
        for (Comment comment :
                comments) {
            HashSet<IdentifierWithType> elementsWithCommentsFor = getElementsFor(comment, elementsNeedPrint);
            for (IdentifierWithType element : elementsWithCommentsFor) {
                element.comment = comment.toString();
            }
        }
    }

    private static HashSet<IdentifierWithType> getElementsFor(Comment comment, ArrayList<IdentifierWithType> elements) {
        HashSet<IdentifierWithType> result = new HashSet<>();
        // comment in current line
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getLine() == comment.getStartLine()) {
                result.add(elements.get(i));
            }
        }
        if (result.size() > 0) {
            return result;
        }
        // comment in previous line
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getLine() == (comment.getEndLine() + 1)) {
                result.add(elements.get(i));
            }
        }
        return result;
    }
}
