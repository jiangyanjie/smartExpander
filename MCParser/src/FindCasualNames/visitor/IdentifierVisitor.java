package FindCasualNames.visitor;

import FindCasualNames.relation.IdentifierWithoutType;
import FindCasualNames.relation.RegularVariable;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.ArrayList;

public class IdentifierVisitor extends ASTVisitor {
	CompilationUnit compilationUnit;
    public ArrayList<IdentifierWithoutType> elements = new ArrayList<>();
    public IdentifierVisitor(CompilationUnit compilationUnit) {
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(SimpleName node) {
        int line = compilationUnit.getLineNumber(node.getStartPosition());
        elements.add(new IdentifierWithoutType(line, node.getIdentifier()));
        return super.visit(node);
    }
}