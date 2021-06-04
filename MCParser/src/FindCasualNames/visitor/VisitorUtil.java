package FindCasualNames.visitor;

import FindCasualNames.relation.RegularVariable;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

public class VisitorUtil {
    public static RegularVariable getRegularVariableFrom(SingleVariableDeclaration node, CompilationUnit compilationUnit) {
        int line = compilationUnit.getLineNumber(node.getName().getStartPosition());
        String name = node.getName().toString();
        String type = node.getType().toString();
        return new RegularVariable(line, name, type);
    }

    public static RegularVariable getRegularVariableFrom(VariableDeclarationFragment node, CompilationUnit compilationUnit) {
        int line = compilationUnit.getLineNumber(node.getName().getStartPosition());
        String type;
        if (node.resolveBinding() == null) {
            if (node.getParent().getNodeType() == ASTNode.FIELD_DECLARATION) {
                FieldDeclaration fieldDeclaration = (FieldDeclaration) node.getParent();
                type = fieldDeclaration.getType().toString();
            } else if (node.getParent().getNodeType() == ASTNode.VARIABLE_DECLARATION_STATEMENT) {
                VariableDeclarationStatement variableDeclarationStatement = (VariableDeclarationStatement) node.getParent();
                type = variableDeclarationStatement.getType().toString();
            } else if (node.getParent().getNodeType() == ASTNode.VARIABLE_DECLARATION_EXPRESSION) {
                VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) node.getParent();
                type = variableDeclarationExpression.getType().toString();
            } else if (node.getParent().getNodeType() == ASTNode.LAMBDA_EXPRESSION) {
                return null;
            } else {
                System.out.println(line);
                System.out.println(node.getParent().getNodeType());
                System.err.println("NameVisitor - VariableDeclarationFragment - " + node.toString());
                throw new IllegalArgumentException("sdf");
            }
        } else {
            if (node.resolveBinding().getType() == null) {
                if (node.getParent().getNodeType() == ASTNode.VARIABLE_DECLARATION_EXPRESSION) {
                    VariableDeclarationExpression variableDeclarationExpression = (VariableDeclarationExpression) node.getParent();
                    type = variableDeclarationExpression.getType().toString();
                } else {
                    System.out.println(line);
                    System.out.println(node.getParent().getNodeType());
                    System.err.println("NameVisitor - VariableDeclarationFragment - " + node.toString());
                    throw new IllegalArgumentException("sdf");
                }
            } else {
                type = node.resolveBinding().getType().getName();
            }
        }
        String name = node.getName().toString();

        return new RegularVariable(line, name, type);
    }
}
