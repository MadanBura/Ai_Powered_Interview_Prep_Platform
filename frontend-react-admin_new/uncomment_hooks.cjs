const fs = require('fs');
const path = require('path');

function walk(dir) {
  let results = [];
  const list = fs.readdirSync(dir);
  list.forEach((file) => {
    file = path.join(dir, file);
    const stat = fs.statSync(file);
    if (stat && stat.isDirectory()) {
      results = results.concat(walk(file));
    } else if (file.endsWith('.ts')) {
      results.push(file);
    }
  });
  return results;
}

const files = walk('/Users/apple/AI-Powered Interview Preparation Platform/frontend-react-admin_new/src/features');

files.forEach(file => {
  let content = fs.readFileSync(file, 'utf8');
  if (content.includes('// return useQuery({') || content.includes('// return useMutation({')) {
    content = content.replace(/\/\/ return useQuery\(\{/g, 'return useQuery({');
    content = content.replace(/\/\/   queryKey:/g, '  queryKey:');
    content = content.replace(/\/\/   queryFn:/g, '  queryFn:');
    content = content.replace(/\/\/ \}\)/g, '})');
    
    content = content.replace(/\/\/ return useMutation\(\{/g, 'return useMutation({');
    content = content.replace(/\/\/   mutationFn:/g, '  mutationFn:');
    
    fs.writeFileSync(file, content, 'utf8');
    console.log('Fixed', file);
  }
});
