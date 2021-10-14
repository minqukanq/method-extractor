package ast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;


public class ASTSupportSingleton
{
	
	private ASTSupportSingleton(){}
	
	private static class SingletonHelper{
    	private static final ASTSupportSingleton INSTANCE = new ASTSupportSingleton();
    }

    public static ASTSupportSingleton getInstance(){
    		return SingletonHelper.INSTANCE;
    }
    
	public void parse(String sourceCodeAsString, ASTVisitor visitor)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setEnvironment(null, new String[]{}, null, true);
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_6, options);
		parser.setCompilerOptions(options);
		
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(sourceCodeAsString.toCharArray());
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		cu.accept(visitor);
	}

	public void parse(String sourceFile, String[] libs, ASTVisitor visitor)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setEnvironment(libs, new String[]
		{}, null, true);
		Map options = JavaCore.getOptions();
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_6, options);
		parser.setCompilerOptions(options);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);

		parser.createASTs(new String[]
		{ sourceFile }, null, new String[0], new ParserRequestor(visitor), null);
	}
	
	public void stmtParse(String sourceCodeAsString, ASTVisitor visitor)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(sourceCodeAsString.toCharArray());
		parser.setKind(ASTParser.K_STATEMENTS);
		Block block = (Block) parser.createAST(null);
		block.accept(visitor);
	}
	
	public void expParse(String sourceCodeAsString, ASTVisitor visitor)
	{
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_EXPRESSION);
		parser.setResolveBindings(true);
		parser.setSource(sourceCodeAsString.toCharArray());
		ASTNode astNode = parser.createAST(null);
		astNode.accept(visitor);
	}
}
