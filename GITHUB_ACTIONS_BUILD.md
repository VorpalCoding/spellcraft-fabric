# Spell Craft Mod - Build Instructions

## Project Status

- ✅ **77 Java source files** - All written and complete
- ✅ **35 Crafting recipes** - JSON files ready
- ✅ **5 Loot tables** - Configured and complete
- ✅ **3 Advancement files** - Ready for players
- ⏳ **JAR compilation** - Ready for GitHub Actions build

## Building the JAR

### Option 1: GitHub Actions (Recommended - No Local Setup Required)

1. Push this repository to GitHub
2. Go to **Actions** tab in your GitHub repo
3. The "Build Fabric Mod JAR" workflow will run automatically
4. Download the compiled JAR from the Artifacts section
5. Place JAR in your Minecraft `mods` folder

**Steps:**
```bash
# Initialize git (if not already)
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/YOUR_USERNAME/spellcraft-fabric.git
git push -u origin main
```

The JAR will be built automatically and available as a release download.

### Option 2: Local Build (Requires JDK 21 + Good Internet)

**Prerequisites:**
- Java 21+ installed
- Good network connection (downloads 1GB+ of dependencies)

**Build:**
```bash
cd c:\spellcraft-fabric
./gradlew build
```

The compiled JAR will be at: `build/libs/spellcraft-1.0.0.jar`

## Installation

1. Download compiled JAR from GitHub Releases or build output
2. Copy to your Minecraft mods folder:
   - Windows: `%APPDATA%\.minecraft\mods\`
   - Mac: `~/Library/Application Support/minecraft/mods/`
   - Linux: `~/.minecraft/mods/`
3. Restart Minecraft with Fabric Loader installed

## Mod Features

**18 Complete Spells with 72 Total Abilities:**

1. **Fire Spell** - Ignite, Meteor Shower, Flame Burst, Lava Trap
2. **Ice Spell** - Freeze, Ice Storm, Glacial Shield, Snowstorm
3. **Lightning Spell** - Shock, Chain Lightning, Static Field, Thunder Clap
4. **Wind Spell** - Gust, Tornado, Wind Slash, Vacuum
5. **Earth Spell** - Stomp, Earthquake, Rock Armor, Stone Prison
6. **Water Spell** - Splash, Whirlpool, Healing Tide, Water Shield
7. **Poison Spell** - Toxic Cloud, Poison Dart, Venom Aura, Decay
8. **Healing Spell** - Restore, Greater Heal, Resurrection, Blessing
9. **Arcane Spell** - Missile, Blink, Teleport, Reflect
10. **Dark Spell** - Curse, Shadow Clone, Death Mark, Void Rift
11. **Light Spell** - Smite, Holy Fire, Purify, Divine Shield
12. **Nature Spell** - Growth, Regeneration, Vines, Nature's Wrath
13. **Metal Spell** - Iron Skin, Sharpness Aura, Magnetism, Metal Storm
14. **Void Spell** - Void Bomb, Dimension Rift, Vortex, Annihilation
15. **Time Spell** - Slow, Haste, Rewind, Time Stop
16. **Illusion Spell** - Decoy, Invisibility, Mirage, Phantom
17. **Warden Spell** - Sonic Boom, Darkness, Summon, Power
18. **Dragon Spell** - Breath, Wing Slash, Claw Attack, Inferno

## Mod Requirements

- **Minecraft**: 1.21.10 (Fabric)
- **Fabric Loader**: 0.16.9+
- **Java**: 21+
- **Fabric API**: Latest for 1.21.10

## Project Structure

```
c:\spellcraft-fabric\
├── src/main/
│   ├── java/
│   │   └── com/ztimelessz/spellcraft/  (77 source files)
│   └── resources/
│       ├── data/spellcraft/
│       │   ├── advancements/  (3 files)
│       │   ├── loot_tables/   (5 files)
│       │   └── recipes/       (35 files)
│       ├── assets/spellcraft/
│       └── fabric.mod.json
├── build.gradle
├── gradle.properties
├── settings.gradle
└── .github/workflows/
    └── build.yml  (GitHub Actions workflow)
```

## Troubleshooting

### JAR Not Building on GitHub
- Check the Actions tab for error logs
- Ensure push includes all source files
- Verify build.gradle and gradle.properties are correct

### JAR Not Loading in Minecraft
- Ensure Fabric Loader is installed
- Check Minecraft logs for error messages
- Verify mod folder location is correct
- Ensure all dependencies are installed

### Build Fails Locally
- Verify Java 21+ is installed: `java -version`
- Check internet connection (downloads 1GB+)
- Clear gradle cache: `./gradlew clean`
- Try building on Linux/Mac if Windows has network issues

## License

MIT License - See LICENSE file

## Author

ZTimelessZ - Comprehensive Spell Crafting Mod for Minecraft Fabric
