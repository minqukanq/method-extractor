package model;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

public class Method {

	private String packageName;
	private SimpleName className;
	private MethodDeclaration methodDeclaration;

	public Method(String packageName, SimpleName className, MethodDeclaration methodDeclaration) {
		this.packageName = packageName;
		this.className = className;
		this.methodDeclaration= methodDeclaration;
	}

	public String getPackageName() {
		return packageName;
	}

	public SimpleName getClassName() {
		return className;
	}

	public MethodDeclaration getMethodDeclaration() {
		return methodDeclaration;
	}
	
}
