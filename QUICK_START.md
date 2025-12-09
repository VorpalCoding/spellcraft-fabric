# 🚀 Quick Start: Deploy Spell Craft Mod via GitHub Actions

## TL;DR - 5 Minutes to Compiled JAR

### Prerequisites
- GitHub account (free)
- Git installed locally

### Steps

1. **Open Git Bash/PowerShell in `C:\spellcraft-fabric`**

2. **Initialize and push to GitHub:**
```bash
git init
git add .
git commit -m "Spell Craft Mod - Complete Package"
git remote add origin https://github.com/YOUR_USERNAME/spellcraft-fabric
git branch -M main
git push -u origin main
```

3. **GitHub Actions builds automatically!**
   - Go to: `https://github.com/YOUR_USERNAME/spellcraft-fabric/actions`
   - Wait 5-10 minutes for build to complete
   - Click "Build Fabric Mod JAR" workflow
   - Download JAR from "Artifacts" section

4. **Install in Minecraft**
   - Copy JAR to `%APPDATA%\.minecraft\mods\`
   - Restart Minecraft with Fabric Loader
   - New spells available in survival mode!

---

## What You Get

✅ **77 Complete Java Classes**
- All spell logic implemented
- 18 spell types with 72 abilities
- Complete item/block system

✅ **43 Game Content Files**
- 35 crafting recipes
- 5 loot tables  
- 3 advancement files

✅ **Ready-to-Run JAR**
- Compiled and packaged on Linux via GitHub Actions
- No local build setup needed
- Automatic on every push

---

## GitHub Setup (First Time Only)

1. Go to https://github.com/new
2. **Repository name**: `spellcraft-fabric`
3. Choose: Public (for releases) or Private
4. Click **Create repository**
5. Don't select "Initialize with README" (we have our own)
6. Copy the HTTPS URL provided
7. Use that URL in the `git remote add origin` command above

---

## After First Build

- JAR downloads automatically to **Releases** page
- Every `git push` triggers new build
- Download latest JAR from **Releases** tab
- Place in Minecraft mods folder and play!

---

## Troubleshooting

**Build failed?**
- Check Actions tab for error log
- Verify all files were committed: `git status`
- Ensure push was successful: `git log --oneline`

**JAR won't load in Minecraft?**
- Confirm Fabric Loader installed
- Check Minecraft logs for errors
- Try older Fabric API version if needed

**Need to modify code?**
- Edit Java files in `src/main/java/`
- Edit recipes/loot tables in `src/main/resources/`
- Commit and push: `git add . && git commit -m "message" && git push`
- Build runs automatically!

---

## File Structure for Reference

```
spellcraft-fabric/
├── .github/workflows/build.yml    ← Automation (do not edit)
├── .gitignore                      ← Git ignore rules
├── build.gradle                    ← Build configuration
├── gradle.properties               ← Dependency versions
├── gradlew / gradlew.bat          ← Gradle wrapper (do not edit)
├── settings.gradle                 ← Project settings
├── SETUP_SUMMARY.md               ← Full documentation
├── GITHUB_ACTIONS_BUILD.md        ← GitHub Actions guide
├── src/main/
│   ├── java/                      ← Java source (edit here)
│   └── resources/                 ← Game content (edit here)
└── build/libs/spellcraft-1.0.0.jar ← Compiled output
```

---

## Success Indicators

✅ Repository created on GitHub
✅ Files pushed successfully (`git push` shows no errors)
✅ Actions tab shows "Build Fabric Mod JAR" workflow
✅ Workflow runs and shows green checkmark
✅ JAR appears in Artifacts/Releases
✅ JAR loads in Minecraft

---

## Feature Summary

| Spell | Abilities |
|-------|-----------|
| Fire | Ignite, Meteor Shower, Flame Burst, Lava Trap |
| Ice | Freeze, Ice Storm, Glacial Shield, Snowstorm |
| Lightning | Shock, Chain Lightning, Static Field, Thunder Clap |
| Wind | Gust, Tornado, Wind Slash, Vacuum |
| Earth | Stomp, Earthquake, Rock Armor, Stone Prison |
| Water | Splash, Whirlpool, Healing Tide, Water Shield |
| Poison | Toxic Cloud, Poison Dart, Venom Aura, Decay |
| Healing | Restore, Greater Heal, Resurrection, Blessing |
| Arcane | Missile, Blink, Teleport, Reflect |
| Dark | Curse, Shadow Clone, Death Mark, Void Rift |
| Light | Smite, Holy Fire, Purify, Divine Shield |
| Nature | Growth, Regeneration, Vines, Nature's Wrath |
| Metal | Iron Skin, Sharpness Aura, Magnetism, Metal Storm |
| Void | Void Bomb, Dimension Rift, Vortex, Annihilation |
| Time | Slow, Haste, Rewind, Time Stop |
| Illusion | Decoy, Invisibility, Mirage, Phantom |
| Warden | Sonic Boom, Darkness, Summon, Power |
| Dragon | Breath, Wing Slash, Claw Attack, Inferno |

---

**Status: Ready to Deploy** ✅
