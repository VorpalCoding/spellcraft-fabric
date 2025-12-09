# Build JAR with pre-cached dependencies
$projectDir = "C:\spellcraft-fabric"
$buildDir = "$projectDir\build"
$classesDir = "$buildDir\classes\java\main"
$libsDir = "$buildDir\libs"
$jarFile = "$libsDir\spellcraft-1.0.0.jar"

# Create output directory
if (-not (Test-Path $libsDir)) {
    New-Item -ItemType Directory -Path $libsDir -Force | Out-Null
}

# Copy resources (recipes, loot tables, etc)
$resourcesDir = "$projectDir\src\main\resources"
if (Test-Path $resourcesDir) {
    Copy-Item "$resourcesDir\*" $classesDir -Recurse -Force 2>/dev/null
}

# Create JAR from classes directory
if (Test-Path $classesDir) {
    Push-Location $classesDir
    jar cvf $jarFile . 2>&1 | Write-Host
    Pop-Location
    
    if (Test-Path $jarFile) {
        $size = (Get-Item $jarFile).Length
        Write-Host "✓ JAR created: $size bytes"
    }
} else {
    Write-Host "✗ Classes directory not found"
}
