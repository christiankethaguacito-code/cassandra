@echo off
echo ========================================
echo Railway Deployment Setup
echo Financial Management System
echo ========================================
echo.

echo Step 1: Checking Git status...
git status
echo.

echo Step 2: Your repository is ready!
echo.
echo Next Steps:
echo.
echo 1. Create a GitHub repository:
echo    - Go to https://github.com/new
echo    - Name: financial-management
echo    - Description: Financial Management System with Spring Boot
echo    - Click "Create repository"
echo.
echo 2. Push your code to GitHub:
echo    git remote add origin https://github.com/YOUR_USERNAME/financial-management.git
echo    git branch -M main
echo    git push -u origin main
echo.
echo 3. Deploy to Railway:
echo    - Go to https://railway.app
echo    - Sign in with GitHub
echo    - Click "New Project"
echo    - Select "Deploy from GitHub repo"
echo    - Choose "financial-management"
echo    - Railway will auto-deploy!
echo.
echo 4. Your app will be live at:
echo    https://financial-management-production.up.railway.app
echo.
echo ========================================
echo Ready to deploy! Follow the steps above.
echo ========================================
echo.
pause
