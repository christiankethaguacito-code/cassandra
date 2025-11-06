@echo off
title Financial Management System - Desktop
cd /d "%~dp0"

echo.
echo ===============================================
echo   Financial Management System - Desktop
echo ===============================================
echo.
echo Please wait while the application starts...
echo This may take 5-10 seconds.
echo.

REM Launch with javaw to hide this console window after startup
start "Financial Management" javaw -jar "target\financial-management-1.0.0-desktop.jar"

echo.
echo Application is starting in the background...
echo You can close this window once the login screen appears.
echo.
timeout /t 3 >nul
exit
