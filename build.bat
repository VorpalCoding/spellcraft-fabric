@echo off
REM Manual Minecraft Fabric Mod Build Script
REM This bypasses Gradle's slow Loom configuration

setlocal enabledelayedexpansion

cd /d C:\spellcraft-fabric

echo ============================================
echo Spell Craft Manual Build Script
echo ============================================
echo.

REM Create directories
echo [1/5] Creating build directories...
if not exist build\classes\java\main mkdir build\classes\java\main
if not exist build\libs mkdir build\libs
if not exist lib mkdir lib
echo OK

REM Download essential dependencies
echo [2/5] Downloading Fabric dependencies...
REM Using Fabric's pre-built JAR which already has everything
if not exist lib\fabric-api.jar (
    REM For now, we'll create a stub - real build would download from Maven
    echo Fabric API download would go here
)
echo OK (Note: Real deployment requires Gradle)

REM Compile Java sources
echo [3/5] Compiling Java sources...
setlocal enabledelayedexpansion

REM Get all Java files
set SRC_PATH=src\main\java
set OUT_PATH=build\classes\java\main
set CP=

REM Compile with minimal dependencies
javac -encoding UTF-8 -d !OUT_PATH! ^
    -sourcepath !SRC_PATH! ^
    !SRC_PATH!\com\ztimelessz\spellcraft\*.java ^
    2>nul

if !errorlevel! equ 0 (
    echo Java compilation OK
) else (
    echo WARNING: Full compilation requires Fabric API libraries
)

REM Copy resources
echo [4/5] Copying resources...
xcopy /E /Y src\main\resources\* build\resources\main\ >nul 2>&1
if exist build\resources\main\fabric.mod.json (
    echo Resources copied
) else (
    echo Resource copy incomplete
)

REM Create metadata JAR with just resources
echo [5/5] Creating data JAR...
set JAR_NAME=spellcraft-1.0.0-resources.jar
set JAR_PATH=build\libs\!JAR_NAME!

REM Use PowerShell to create JAR
powershell -Command "Add-Type -AssemblyName System.IO.Compression.FileSystem; [System.IO.Compression.ZipFile]::CreateFromDirectory('src\main\resources', '!JAR_PATH!')"

if exist !JAR_PATH! (
    echo Created: !JAR_NAME!
    echo File size: 
    for %%A in (!JAR_PATH!) do echo %%~zA bytes
) else (
    echo JAR creation failed
)

echo.
echo ============================================
echo Build Complete (Partial - requires Gradle)
echo ============================================
echo.
echo NOTE: Full mod compilation requires:
echo - Gradle 9.2.1 with Fabric Loom 1.8.13
echo - Fabric API and dependencies
echo - Minecraft mappings
echo.
echo The JAR created contains resources only.
echo For a complete mod, run: gradle build
echo ============================================

endlocal
