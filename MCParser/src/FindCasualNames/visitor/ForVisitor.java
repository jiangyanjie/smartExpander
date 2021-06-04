package FindCasualNames.visitor;

import FindCasualNames.relation.ForVariable;
import FindCasualNames.relation.RegularVariable;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.ArrayList;
import java.util.List;

public class ForVisitor extends ASTVisitor {
    CompilationUnit compilationUnit;
    public ArrayList<ForVariable> forVariables = new ArrayList<>();

    public ForVisitor(CompilationUnit compilationUnit) {
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(EnhancedForStatement node) {
        SingleVariableDeclaration parameter = node.getParameter();
        RegularVariable regularVariable = VisitorUtil.getRegularVariableFrom(parameter, compilationUnit);
        forVariables.add(ForVariable.constructFrom(regularVariable));
        return super.visit(node);
    }

    @Override
    public boolean visit(ForStatement node) {
        List expressions = node.initializers();
        if (expressions.size() == 1) {
            Object expression = expressions.get(0);
            if (expression instanceof VariableDeclarationExpression) {
                VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) expression;
                List fragements = variableDeclarationExpression.fragments();
                for (Object object :
                        fragements) {
                    VariableDeclarationFragment variableDeclarationFragment = (VariableDeclarationFragment) object;
                    RegularVariable regularVariable = VisitorUtil.getRegularVariableFrom(variableDeclarationFragment, compilationUnit);
                    forVariables.add(ForVariable.constructFrom(regularVariable));
                }
            }
        }
        return super.visit(node);
    }
}