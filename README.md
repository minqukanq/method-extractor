# method-extractor-java
For source code research, only the Method part in the java file is extracted.


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

output
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
