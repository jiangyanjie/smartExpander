package FindCasualNames.visitor;

import FindCasualNames.relation.Method;
import FindCasualNames.relation.Parameter;
import FindCasualNames.relation.RegularVariable;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import java.util.ArrayList;
import java.util.List;

public class MethodVisitor extends ASTVisitor {
    CompilationUnit compilationUnit;
    public ArrayList<Method> methods = new ArrayList<>();

    public MethodVisitor(CompilationUnit compilationUnit) {
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        Method method = getMethod(node, compilationUnit);
        methods.add(method);
        return super.visit(node);
    }

    public Method getMethod(MethodDeclaration node,
                            CompilationUnit compilationUnit) {

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
        String name = node.getName().toString();

        Method method = new Method(line, name, myParameters, variables);
        return method;
    }
}
