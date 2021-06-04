package FindCasualNames.visitor;

import FindCasualNames.relation.RegularVariable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import java.util.ArrayList;

public class VariableInMethodVisitor extends ASTVisitor {
    CompilationUnit compilationUnit;
    public ArrayList<RegularVariable> variables = new ArrayList<>();

    public VariableInMethodVisitor(CompilationUnit compilationUnit) {
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        return false;
    }

    // field declarations, local variable declarations,
    // ForStatement initializers, and LambdaExpression parameters
    @Override
    public boolean visit(VariableDeclarationFragment node) {
        if (node.getParent().getParent().getNodeType() ==
                ASTNode.FOR_STATEMENT) {
            return super.visit(node);
        }
        RegularVariable variable = VisitorUtil.getRegularVariableFrom(node, compilationUnit);
        if (variable != null)
            variables.add(variable);
        return super.visit(node);
    }


    // catch clauses
    @Override
    public boolean visit(SingleVariableDeclaration node) {
        if (node.getParent().getNodeType() == ASTNode.ENHANCED_FOR_STATEMENT) {
            return super.visit(node);
        }
        RegularVariable variable = VisitorUtil.getRegularVariableFrom(node, compilationUnit);
        variables.add(variable);
        return super.visit(node);
    }
}