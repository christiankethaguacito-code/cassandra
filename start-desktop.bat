@echo off
echo ================================================
echo Personal Financial Management System
echo Starting Desktop Application...
echo ================================================
echo.
echo Please wait while the application loads...
echo ================================================
echo.

call mvn exec:java -Dexec.mainClass="com.isulan.financial.desktop.DesktopApplication"
