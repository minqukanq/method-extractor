## PYTHON

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
