@echo off
cd /d c:\spellcraft-fabric
echo Killing any existing Java processes...
taskkill /F /IM java.exe 2>nul
timeout /t 3 /nobreak

echo Cleaning gradle cache...
if exist .gradle\locks rmdir /s /q .gradle\locks 2>nul
if exist build rmdir /s /q build 2>nul

echo Starting fresh gradle build...
call gradle.bat clean build --no-daemon --max-workers=2
echo Build completed with exit code: %ERRORLEVEL%
pause
