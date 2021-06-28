package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import model.Method;


public class SourceCodeVisitor extends ASTVisitor {

	private List<Method> methodList;
	private String packageName;
	private String commitID;

	public SourceCodeVisitor(List<Method> methodObjects, String filePath) {
		this.methodList = methodObjects;
	}
	
	@Override
	public boolean visit(PackageDeclaration node) {
		this.packageName = node.toString();
		return true;
	}
	
	@Override
	public boolean visit(TypeDeclaration node) {
		SimpleName className = node.getName();
		MethodDeclaration[] methods = node.getMethods();
		for (MethodDeclaration methodDeclaration : methods) {
			methodList.add(new Method(packageName, className, methodDeclaration));
		}
		return true;
	}

}