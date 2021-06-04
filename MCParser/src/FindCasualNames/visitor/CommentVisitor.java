package FindCasualNames.visitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;
import FindCasualNames.relation.Comment;

import java.util.ArrayList;

public class CommentVisitor extends ASTVisitor {
    CompilationUnit compilationUnit;
    private String[] source;
    public ArrayList<Comment> comments = new ArrayList<>();

    public CommentVisitor(CompilationUnit compilationUnit, String[] source) {
        super();
        this.compilationUnit = compilationUnit;
        this.source = source;
    }

    @Override
    public boolean visit(Javadoc node) {
        int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition());

        int endLineNumber = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength());
        Comment comment = new Comment(startLineNumber, endLineNumber, node.toString());
        comments.add(comment);
        return super.visit(node);
    }

    @Override
    public boolean visit(LineComment node) {
        int line = compilationUnit.getLineNumber(node.getStartPosition());
        String content = source[line - 1].trim();
        content = content.substring(content.indexOf("//"));

        Comment comment = new Comment(line, line, content);
        comments.add(comment);
        return true;
    }

    @Override
    public boolean visit(BlockComment node) {
        int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition());
        int endLineNumber = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength());

        StringBuffer blockComment = new StringBuffer();
        for (int lineCount = startLineNumber; lineCount <= endLineNumber; lineCount++) {
            String blockCommentLine = source[lineCount - 1].trim();
            blockComment.append(blockCommentLine);
            if (lineCount != endLineNumber) {
                blockComment.append("\n");
            }
        }
        Comment comment = new Comment(startLineNumber, endLineNumber, blockComment.toString());
        comments.add(comment);
        return true;
    }
}