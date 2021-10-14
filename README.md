# method-extractor
For source code research, only **Method** part of **Java**, **Python** file is extracted using **AST tree**.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.



## Prerequisites
To install the software, you need to download the following:

### 1. JAVA
The code only works in [Eclipse](https://www.eclipse.org/downloads/).
1. Download [Eclipse](https://www.eclipse.org/downloads/).
2. Clone or download the repository to your local computer.
3. Import method-extractor/java.
4. Open this project, The library to run the project is automatically downloaded by Maven.
5. If an import error occurs, manually add the **jar** files in the Lib folder.

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
```java
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
```java
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

### 2. PYTHON

1. Clone or download the repository to your local computer.
2. Open the terminal and install the packages


### Installing
#### pip:
```
$ pip install astunparse
```

#### or conda:
```
$ conda install -c conda-forge astunparse
```

### Test the application

Run Analyzer.py
* Method extractor takes a String as input and provides only the uncommented pure Method part.

Input
```python
class Analyzer(ast.NodeVisitor):
    def __init__(self) -> None:
        self.stats = {"import": [], "class": [], "function": [], "behavior":[]}

    def visit_Import(self, node: ast.Import) -> None:
        for alias in node.names:
            self.stats["import"].append(alias.name)

    def visit_ClassDef(self, node: ast.ClassDef) -> None:
        self.stats["class"].append(node.name)
        self.generic_visit(node)

    def visit_FunctionDef(self, node: ast.FunctionDef) -> None:
        self.stats["function"].append(node.name)

        args     = astunparse.unparse(node.args).replace("\n","")
        content  = astunparse.unparse(node.body)
        
        behabior = f'def {node.name} ({args}): {content}'
        self.stats["behavior"].append(behabior)
        print(behabior)

    @staticmethod
    def visit_Assign(node: ast.Assign) -> None:
        print('Assign : do something with node={}'.format(node))

    def report(self) -> None: 
        pprint(self.stats)

def main():
    filename = f'python{os.sep}Analyzer.py'

    with open(filename, "r") as source:
        node = ast.parse(source.read())

    analyzer = Analyzer()
    analyzer.visit(node)
    analyzer.report()

if __name__ == "__main__":
    main()
```

Output
'behavior':
```python
def __init__ (self): 
self.stats = {'import': [], 'class': [], 'function': [], 'behavior': []}

def visit_Import (self, node: ast.Import): 
for alias in node.names:
    self.stats['import'].append(alias.name)

def visit_ClassDef (self, node: ast.ClassDef):       
self.stats['class'].append(node.name)
self.generic_visit(node)

def visit_FunctionDef (self, node: ast.FunctionDef): 
self.stats['function'].append(node.name)
args = astunparse.unparse(node.args).replace('\n', '')
content = astunparse.unparse(node.body)
behabior = f'def {node.name} ({args}): {content}'
self.stats['behavior'].append(behabior)
print(behabior)

def visit_Assign (node: ast.Assign):
print('Assign : do something with node={}'.format(node))

def report (self):
pprint(self.stats)

def main ():
filename = f'python{os.sep}Analyzer.py'
with open(filename, 'r') as source:
    node = ast.parse(source.read())
analyzer = Analyzer()
analyzer.visit(node)
analyzer.report()
```


## Built With
### JAVA
* org.eclipse.jdt.core.dom.AST
* org.eclipse.jdt.core.dom.ASTNode
* org.eclipse.jdt.core.dom.ASTParser;
* org.eclipse.jdt.core.dom.ASTVisitor;
### PYTHON
* ast
* astunparse

## Authors
* **Mingu Kang** - [Github](https://github.com/minqukanq)
