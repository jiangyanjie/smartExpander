package FindCasualNames.main;

import FindCasualNames.visitor.*;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Comment;
import util.Util;
import FindCasualNames.relation.IdentifierWithoutType;
import FindCasualNames.relation.Identifier;

import java.io.File;
import java.util.ArrayList;
import util.Config;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class Step3_getAllIdentifiers {
    public static void main(String[] args) throws Exception{
        getAllIdentifiers();
    }


    public static void getAllIdentifiers() throws Exception{
        String abbrPath = Step1_2_FilterNamesAndConstructContext.filteredAbbrName;
        String resultWithContext = Step1_2_FilterNamesAndConstructContext.resultWithContext;
        Util.deleteIfExist(new File(resultWithContext));

        ArrayList<String> data = Util.readFile(abbrPath);
        ArrayList<String> result = new ArrayList<>();

        for(int i=0; i< data.size();i++){
            String[] lineData = data.get(i).split(",");
            String identifierPath = lineData[5];
            ArrayList<Identifier> elements = parseAllidentifier(identifierPath);
            Util.appendFile(data.get(i) + elements.toString().replace(",", "*") + "\n",resultWithContext);
        }
    }

    public static ArrayList<Identifier> parseAllidentifier(String javaFilePath) throws Exception{
        Config.projectName = javaFilePath;
        String source = getStringFromFile(javaFilePath);
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
        String[] sourceLines = splitSource(source);

        ArrayList<Identifier> elements = new ArrayList<>();

        SimpleVisitor simpleVisitor = new SimpleVisitor(unit);
        unit.accept(simpleVisitor);
        elements.addAll(simpleVisitor.identifiers);
        return elements;
    }

    public static String getStringFromFile(String javaFilePath) throws Exception{
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
        byte[] input = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(input);
        bufferedInputStream.close();
        return new String(input);
    }

    public static String[] splitSource (String source){
        ArrayList<String> result = new ArrayList<>();
        String[] temp = source.split("\r\n", -1);
        for(String string : temp){
            String[] temp2 = string.split("\n",-1);
            for(String string2 : temp2){
                String[] temp3 = string2.split("\r", -1);
                for(String string3 : temp3){
                    result.add(string3);
                }
            }
        }

        String[] strings = new String[result.size()];
        for(int i=0; i < strings.length;i++){
            strings[i] = result.get(i);
        }
        return strings;
    }

}


