package FindCasualNames.main;

import FindCasualNames.relation.IdentifierWithoutType;
import FindCasualNames.visitor.CommentVisitor;
import FindCasualNames.visitor.ForVisitor;
import FindCasualNames.visitor.IdentifierVisitor;
import FindCasualNames.visitor.MethodVisitor;
import FindCasualNames.visitor.NameVisitor;
import FindCasualNames.visitor.MethodDeclarationVisitor;
import FindCasualNames.visitor.ClassNameVisitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import util.Config;
import util.Util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Step1_1_ParseNames {
    public static void main(String[] args) throws Exception {

        boolean singleFile = false;
        if (singleFile) {
            try {
                String path = " ";
                ArrayList<IdentifierWithoutType> elements = parseOneFile(path);
                Step1_1_PrintElements.printElements(elements, path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String projectsPath = Setting.projectsPath;
            Util.deleteIfExist(new File(Setting.outFile));
            parseFilesWithProgress(projectsPath);
        }
    }


    private static void parseFilesWithProgress(String path) {
        int i = 1;
        File file = new File(path);
        ArrayList<String> paths = new ArrayList<>();
        for (File oneFile :
                file.listFiles()) {
            paths.add(oneFile.getPath());
        }
        Collections.sort(paths);
        for (String tempPath :
                paths) {
            System.out.println(i++ + "\t" + tempPath);
            parseFiles(tempPath);
        }
    }
    private static void parseFiles(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            for (File oneFile :
                    file.listFiles()) {
                if (oneFile.isDirectory()) {
                    parseFiles(oneFile.getPath());
                } else {
                    if (oneFile.getPath().endsWith(".java")) {
                        try {
                            ArrayList<IdentifierWithoutType> elements = parseOneFile(oneFile.getPath());
                            Step1_1_PrintElements.printElements(elements, oneFile.getPath());
                        } catch (Exception e) {
                            System.err.println(oneFile.getPath());
                        }
                    }
                }
            }
        } else {
            if (file.getPath().endsWith(".java")) {
                try {
                    ArrayList<IdentifierWithoutType> elements = parseOneFile(file.getPath());
                    Step1_1_PrintElements.printElements(elements, file.getPath());
                } catch (Exception e) {
                    System.err.println(file.getPath());
                }
            }
        }
    }


    private static String getStringFromFile(String javaFilePath) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(javaFilePath));
        byte[] input = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(input);
        bufferedInputStream.close();
        return new String(input);
    }

    private static ArrayList<IdentifierWithoutType> parseOneFile(String javaFilePath) throws Exception {
        Config.projectName = javaFilePath;
        String source = getStringFromFile(javaFilePath); //得到对应java文件的源代码
        ASTParser astParser = ASTParser.newParser(AST.JLS8);
        String[] sourceFilePaths = {};
        String[] encodings = {};
        String[] classPathPaths = {};
        astParser.setSource(source.toCharArray());
        astParser.setEnvironment(classPathPaths, sourceFilePaths, encodings, true);
        astParser.setResolveBindings(true);
        astParser.setBindingsRecovery(true);
        astParser.setUnitName("");
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);

        CompilationUnit unit = (CompilationUnit) (astParser.createAST(null));
        String[] sourceLines = splitSource(source); //得到代码的每一行

        ArrayList<IdentifierWithoutType> elements = new ArrayList<>();

        ForVisitor forVisitor = new ForVisitor(unit);
        unit.accept(forVisitor);
        elements.addAll(forVisitor.forVariables);

        MethodVisitor methodVisitor = new MethodVisitor(unit);
        unit.accept(methodVisitor);
        elements.addAll(methodVisitor.methods);

        NameVisitor nameVisitor = new NameVisitor(unit);
        unit.accept(nameVisitor);
        elements.addAll(nameVisitor.variables);


        MethodDeclarationVisitor methodDeclarationVisitor = new MethodDeclarationVisitor(unit);
        unit.accept(methodDeclarationVisitor);
        elements.addAll(methodDeclarationVisitor.methodDeclarations);


        ClassNameVisitor classNamevisitor = new ClassNameVisitor(unit);
        unit.accept(classNamevisitor);
        elements.addAll(classNamevisitor.methodNames);



        for (Object object : unit.getCommentList()) {
            Comment comment = (Comment) object;
            CommentVisitor commentVisitor = new CommentVisitor(unit, sourceLines);
            comment.accept(commentVisitor);
            elements.addAll(commentVisitor.comments);
        }

        IdentifierVisitor identifierVisitor = new IdentifierVisitor(unit);
        unit.accept(identifierVisitor);
        elements.addAll(identifierVisitor.elements);
        return elements;
    }

    private static String[] splitSource(String source) {
        ArrayList<String> result = new ArrayList<>();
        String[] temp = source.split("\r\n", -1);
        for (String string :
                temp) {
            String[] temp2 = string.split("\n", -1);
            for (String string2 :
                    temp2) {
                String[] temp3 = string2.split("\r", -1);
                for (String string3 :
                        temp3) {
                    result.add(string3);
                }
            }
        }
        String[] strings = new String[result.size()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = result.get(i);
        }
        return strings;
    }
}
