package ast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.junit.Test;

import model.Method;

public class MethodExtractorTest {

	String source = "public abstract class MisraTableViewColumn extends ColumnLabelProvider {\r\n" + 
			"  public abstract String getText(  Object element);\r\n" + 
			"  public abstract String getTitle();\r\n" + 
			"  public abstract int getWidth();\r\n" + 
			"  public TableViewerColumn addColumnTo(  TableViewer tableViewer){\r\n" + 
			"    TableViewerColumn tableViewerColumn=new TableViewerColumn(tableViewer,SWT.NONE);\r\n" + 
			"    TableColumn column=tableViewerColumn.getColumn();\r\n" + 
			"    column.setMoveable(false);\r\n" + 
			"    column.setResizable(false);\r\n" + 
			"    column.setText(getTitle());\r\n" + 
			"    column.setWidth(getWidth());\r\n" + 
			"    tableViewerColumn.setLabelProvider(this);\r\n" + 
			"    return tableViewerColumn;\r\n" + 
			"  }\r\n" + 
			"}";
	
	@Test
	public void extractMethod() {
		ASTSupportSingleton astTest = ASTSupportSingleton.getInstance();
		List<Method> methodObjects = new ArrayList();
		astTest.parse(source, new SourceCodeVisitor(methodObjects, source));
		for (Method method : methodObjects) {
			MethodDeclaration methodDecl = method.getMethodDeclaration();
			System.out.println(methodDecl);
		}
	}
}
