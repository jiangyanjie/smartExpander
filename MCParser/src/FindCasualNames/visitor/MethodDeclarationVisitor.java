package FindCasualNames.visitor;

import FindCasualNames.relation.Parameter;
import FindCasualNames.relation.RegularVariable;
import org.eclipse.jdt.core.dom.*;
import FindCasualNames.relation.MethodDeclarationInfo;

import java.util.ArrayList;
import java.util.List;

public class MethodDeclarationVisitor extends ASTVisitor{

    CompilationUnit compilationUnit;
    public ArrayList<MethodDeclarationInfo> methodDeclarations = new ArrayList<>();

    public MethodDeclarationVisitor(CompilationUnit compilationUnit){
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(MethodDeclaration node){

        MethodDeclarationInfo methodDeclarationInfo =getMethodDeclarationInfo(node, compilationUnit);
        methodDeclarations.add(methodDeclarationInfo);
        return super.visit(node);
    }

    public static MethodDeclarationInfo getMethodDeclarationInfo(MethodDeclaration node, CompilationUnit compilationUnit){
        //method name
        String name = node.getName().toString();


        List parameters = node.parameters();

        ArrayList<Parameter> myParameters = new ArrayList<>();
        for (Object object :
                parameters) {
            SingleVariableDeclaration singleVariableDeclaration = (SingleVariableDeclaration) object;
            RegularVariable regularVariable = VisitorUtil.getRegularVariableFrom(singleVariableDeclaration, compilationUnit);
            myParameters.add(Parameter.constructFrom(regularVariable));
        }

        Block body = node.getBody();
        ArrayList<RegularVariable> variables = new ArrayList<>();
        if (body != null) {
            VariableInMethodVisitor variableInMethodVisitor = new VariableInMethodVisitor(compilationUnit);
            body.accept(variableInMethodVisitor);
            variables.addAll(variableInMethodVisitor.variables);
        }
        int line = compilationUnit.getLineNumber(node.getName().getStartPosition());

        //System.out.println("i want to know the method name is what " + name + " " + line + " "+ parameters);

        MethodDeclarationInfo method = new MethodDeclarationInfo(line, name,"", parameters);
        return method;

    }


}
