# method-extractor-java
For source code research, only **Method** part of Java file is extracted using **AST tree**.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

The code only works in [Eclipse](https://www.eclipse.org/downloads/).

### Prerequisites

To install the software, you need to download the following:
1. Download [Eclipse](https://www.eclipse.org/downloads/).
2. Clone or download the repository to your local computer.
3. Import method-extractor-java.
4. Open this project, The library to run the project is automatically downloaded by Maven.

### tree
```
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── app
│   │   │   │   └── Init.java
│   │   │   ├── ast
│   │   │   │   ├── ASTSupportSingleton.java
│   │   │   │   ├── ParserRequestor.java
│   │   │   │   └── SourceCodeVisitor.java
│   │   │   └── model
│   │   │       └── Method.java
│   │   └── resources
│   └── test
│       ├── java
│       │   └── ast
│       │       └── MethodExtractorTest.java
│       └── resources
```

### Running the application

Run app.Init.java

### Test the application

Run MethodExtractorTest.java
* Method extractor takes a String as input and provides only the uncommented pure Method part.

Input
```
public abstract class MisraTableViewColumn extends ColumnLabelProvider {
    
    public abstract String getText(  Object element );
    
    public abstract String getTitle();
    
    public abstract int getWidth();
    
    /*
     * Insert a new column into the data in the data table.
    */
    public TableViewerColumn addColumnTo(  TableViewer tableViewer){
        TableViewerColumn tableViewerColumn=new TableViewerColumn(tableViewer,SWT.NONE); 
        TableColumn column=tableViewerColumn.getColumn(); 
        column.setMoveable(false); // For data visualization
        column.setResizable(false); // For data visualization
        column.setText(getTitle()); // For data visualization
        column.setWidth(getWidth()); // For data visualization
        tableViewerColumn.setLabelProvider(this); // For data visualization
        return tableViewerColumn; 
    }
}
```

Output
```
public abstract String getText(Object element);

public abstract String getTitle();

public abstract int getWidth();

public TableViewerColumn addColumnTo(TableViewer tableViewer){
  TableViewerColumn tableViewerColumn=new TableViewerColumn(tableViewer,SWT.NONE);
  TableColumn column=tableViewerColumn.getColumn();
  column.setMoveable(false);
  column.setResizable(false);
  column.setText(getTitle());
  column.setWidth(getWidth());
  tableViewerColumn.setLabelProvider(this);
  return tableViewerColumn;
}
```
