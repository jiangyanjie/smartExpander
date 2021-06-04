package FindCasualNames.visitor;

import FindCasualNames.relation.IdentifierWithoutType;
import org.eclipse.jdt.core.dom.ASTVisitor;
import FindCasualNames.relation.Identifier;
import java.util.ArrayList;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class SimpleVisitor extends ASTVisitor {
    private CompilationUnit compilationUnit;
    public ArrayList<Identifier> identifiers = new ArrayList<>();

    public SimpleVisitor(CompilationUnit compilationUnit){
        super();
        this.compilationUnit = compilationUnit;
    }

    @Override
    public boolean visit(SimpleName node){
        Identifier identifier = getIdentifier(node, compilationUnit);
        identifiers.add(identifier);
        return super.visit(node);
    }

    public Identifier getIdentifier(SimpleName node, CompilationUnit compilationUnit){
        String name = node.getIdentifier();
        int line = compilationUnit.getLineNumber(node.getStartPosition());
        Identifier identifier = new Identifier(line, name);
//        System.out.println("====" + identifier.toString());
        return identifier;

    }

}
