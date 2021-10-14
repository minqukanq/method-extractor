package ast;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;

public class ParserRequestor extends FileASTRequestor 
{
	private ASTVisitor visitor;

	public ParserRequestor(ASTVisitor v) {
		this.visitor = v;
	}

	@Override
	public void acceptAST(String sourceFilePath, CompilationUnit ast) {
		ast.accept(this.visitor);
		super.acceptAST(sourceFilePath, ast);
	}

}