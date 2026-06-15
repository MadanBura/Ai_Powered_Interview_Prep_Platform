import os
import glob

def fix_imports():
    hooks_dir = 'src/features'
    for filepath in glob.glob(f'{hooks_dir}/**/hooks/**/*.ts', recursive=True):
        with open(filepath, 'r') as f:
            content = f.read()

        has_query_import = "import { useQuery" in content or "import { useMutation, useQuery" in content or "import { useQuery," in content or "import { useQueryClient, useQuery" in content
        has_mutation_import = "import { useMutation" in content or "import { useQuery, useMutation" in content or "import { useMutation," in content or "import { useQueryClient, useMutation" in content
        has_client_import = "import { useQueryClient" in content or "import { useQuery, useQueryClient" in content or "import { useQueryClient," in content or "import { useMutation, useQueryClient" in content
        
        needs_query = 'useQuery(' in content and not has_query_import
        needs_mutation = 'useMutation(' in content and not has_mutation_import
        needs_query_client = 'useQueryClient(' in content and not has_client_import

        imports = []
        if needs_query:
            imports.append('useQuery')
        if needs_mutation:
            imports.append('useMutation')
        if needs_query_client:
            imports.append('useQueryClient')

        if imports:
            import_str = f"import {{ {', '.join(imports)} }} from '@tanstack/react-query';\n"
            
            lines = content.splitlines()
            if lines and lines[0].startswith('//'):
                lines.insert(1, import_str.strip())
            else:
                lines.insert(0, import_str.strip())
            
            with open(filepath, 'w') as f:
                f.write('\n'.join(lines) + '\n')
            print(f"Fixed imports in {filepath}")

if __name__ == '__main__':
    fix_imports()
