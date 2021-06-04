package FindCasualNames.visitor;

import java.util.ArrayList;
import FindCasualNames.relation.ClassName;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import FindCasualNames.relation.MethodDeclarationInfo;

public class ClassNameVisitor extends ASTVisitor{
    private CompilationUnit compilationUnit;
    public ArrayList<ClassName> methodNames = new ArrayList<>();

    public ClassNameVisitor(CompilationUnit compilationUnit){
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(TypeDeclaration node){
      ClassName className = getClassInfo(node, compilationUnit);
      methodNames.add(className);
      return super.visit(node);
    }

    public static ClassName getClassInfo(TypeDeclaration node, CompilationUnit compilationUnit){
        String name = node.getName().toString();
        MethodDeclaration[] methods = node.getMethods();

        ArrayList<MethodDeclarationInfo> methodsName = new ArrayList<>();

        for(MethodDeclaration methodDeclaration : methods){
            MethodDeclarationInfo methodDeclarationInfo = MethodDeclarationVisitor.getMethodDeclarationInfo(methodDeclaration,compilationUnit);
            methodsName.add(methodDeclarationInfo);
        }

        int line = compilationUnit.getLineNumber(node.getName().getStartPosition());

//        System.out.println("i want to know the method name is what " + name + " " + line + " "+ methodsName);

        ClassName includeMethod = new ClassName(line, name, "", methodsName);
        return includeMethod;
    }

}
