const fs = require('fs');
const path = require('path');

const pagesDir = path.join(__dirname, 'src', 'pages');

if (fs.existsSync(pagesDir)) {
  const files = fs.readdirSync(pagesDir).filter(f => f.endsWith('.tsx'));
  
  files.forEach(file => {
    if (file === 'Admin_Login.tsx' || file === 'Admin_Forgot_Password.tsx' || file === 'Admin_OTP_Verification.tsx') {
      return; // Skip auth pages
    }
    
    const filePath = path.join(pagesDir, file);
    let content = fs.readFileSync(filePath, 'utf8');
    
    // Remove comments about Sidebar, TopNavbar, Main Content Shell
    content = content.replace(/\{\/\*\s*(SideNavBar|Sidebar|TopNavBar|Top Navigation|Main Content).*?\*\/\}\n?/gi, '');
    
    // Remove <main ...> and </main> tags for layout wrapping
    // Careful to only remove the outer main tag if it exists.
    // Most files have exactly one <main> tag.
    let mainMatch = content.match(/<main[^>]*>/);
    if (mainMatch) {
        content = content.replace(mainMatch[0], '');
        content = content.replace(/<\/main>/, '');
    }
    
    fs.writeFileSync(filePath, content, 'utf8');
    console.log(`Cleaned layout wrappers from ${file}`);
  });
}
