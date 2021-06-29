package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import ast.ASTSupportSingleton;
import ast.SourceCodeVisitor;
import model.Method;

public class Init {

	public static void main(String[] args) {
		
		String dir = "src/main/java/ast/ASTSupportSingleton.java";
		int lastIndex = 0;
		for(int i=0; i<dir.length();i++) {
			int index = dir.indexOf("/", i);
			if(index == -1) {
				break;
			}
			lastIndex  = index;
		}
		
		String PATH  = dir.substring(0, lastIndex) + "/";
		String FILENAME = dir.split(PATH)[1];
		System.out.println(PATH);
		System.out.println(FILENAME);
		
		String input = "";
		try {
		    BufferedReader file = new BufferedReader(new FileReader(dir));
		    String line;

		    while ((line = file.readLine()) != null) 
		        input += line + '\n';
		    file.close();
		    
		} catch (Exception e) {System.out.print("Problem reading the file.\n" + e + "\n");}
		
		String source = input;
		
			ASTSupportSingleton astTest = ASTSupportSingleton.getInstance();
			List<Method> methodObjects = new ArrayList<Method>();
			astTest.parse(source, new SourceCodeVisitor(methodObjects, source));
			for (Method method : methodObjects) {
				MethodDeclaration methodDecl = method.getMethodDeclaration();
				System.out.println(methodDecl);
			}
		
	}

}
