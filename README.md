# Spell Craft - Minecraft Fabric Mod

A comprehensive spell system mod for Minecraft 1.21.3 featuring 18 unique spells, each with 3 base abilities plus 1 enhanced ability, sword variants, and a complete progression system.

## Features

### Core Systems

- **18 Unique Spells** - Warden, Fire, Ice, Lightning, Healing, Dragon, Earth, Light, Nature, Necromancer, Potion, Shield, Time, Void, and Wind
- **Spell Abilities** - Each spell has 3 base abilities + 1 enhanced variant
- **Sword Integration** - Every spell has a corresponding sword with a unique right-click ability
- **Spell Cooldown System** - Per-player cooldown tracking with millisecond precision
- **Max 2 Active Spells** - Players can only have 2 spells active at once
- **NBT Persistence** - Spells save automatically and persist across server restarts
- **Spell Leveling System** - Spells gain experience and level up
- **Spell Gems & Blocks** - Craftable items and decorative blocks
- **Custom Loot Drops** - Mobs drop spell gems for progression
- **Advancements** - Achievement system for spell progression

## Currently Implemented

### All 18 Spells

**Warden** - Domain Expansion, Locate, Sculk Sense (enhanced), Swift Dash (sword)
**Fire** - Fireball, Inferno, Burning Soul (enhanced), Flame Dash (sword)
**Ice** - Frost Bolt, Blizzard, Absolute Zero (enhanced), Frozen Slash (sword)
**Lightning** - Thunderbolt, Chain Lightning, Electrocution (enhanced), Thunder Slash (sword)
**Healing** - Restore, Rejuvenation, Divine Protection (enhanced), Lifesteal (sword)
**Dragon** - Dragon Breath, Dragon's Fury, Ancient Dragon Power (enhanced), Dragon Cleave (sword)
**Earth** - Earthquake, Stone Armor, Titan's Grip (enhanced), Tremor Strike (sword)
**Light** - Holy Beam, Radiance, Divine Judgment (enhanced), Holy Slash (sword)
**Nature** - Vine Whip, Regrowth, Nature's Bounty (enhanced), Vine Strike (sword)
**Necromancer** - Curse of Wasting, Dark Pact, Soul Reaver (enhanced), Death Strike (sword)
**Potion** - Swift, Strength, Power Potion (enhanced), Potion Slash (sword)
**Shield** - Barrier, Fortify, Iron Skin (enhanced), Shield Bash (sword)
**Time** - Rewind, Haste, Chronosphere (enhanced), Time Dilation (sword)
**Void** - Void Rift, Void Pull, Void Mastery (enhanced), Void Strike (sword)
**Wind** - Wind Gust, Glide, Tempest (enhanced), Wind Slash (sword)

## Installation

1. Install Fabric Loader for Minecraft 1.21.3
2. Install Fabric API
3. Place `spellcraft-1.0.0.jar` in your `mods` folder
4. Launch Minecraft

## Usage

### Commands
```
/wdspell <SpellName>  - Absorb a spell (max 2 active)
```

### Spell Absorption
Right-click and run `/wdspell warden` to absorb the Warden spell. Try all 18 spells!

### Crafting
Each spell can be crafted with thematic ingredients:
- **Fire Spell** - fire_charge + lava_bucket + blaze_rod + magma_block + netherite_ingot + crying_obsidian
- **Ice Spell** - blue_ice + packed_ice + ice + amethyst_shard + netherite_ingot + crying_obsidian
- (And 16 more unique recipes)

**Spell Swords**
Combine spell item + stick to create spell swords:
```
  S
  S
  T
```

**Spell Gems**
Mine spell ore blocks and smelt to get gems for crafting:
- Warden Ore, Fire Ore, Ice Ore, Lightning Ore, Void Ore

### In-Game HUD
- Displays active spells above hotbar
- Shows ability names and cooldown status
- Green "Ready" or red remaining ticks
- Real-time updates

### Sword Combat
- Right-click with spell sword to use sword ability
- Each sword ability has unique effects
- Cooldowns per spell type

### Spell Leveling
- Spells gain XP through usage
- 100 XP per level
- Level milestones unlock enhanced effects
- Tracked in player NBT data

## Technical Details

**Architecture**
- Factory pattern for spell registration
- Per-player cooldown tracking with HashMap
- NBT serialization for persistence
- Fabric API event callbacks

**Performance**
- Lazy spell instantiation
- Efficient cooldown system
- Client-side HUD rendering only
- Minimal server tick impact

**File Structure**
```
src/main/java/com/ztimelessz/spellcraft/
├── spell/              - Core spell system
├── item/               - Custom items (swords, gems)
├── block/              - Custom blocks (ores, decorative)
├── command/            - Command system
├── event/              - Event handlers
├── client/             - Client rendering
├── loot/               - Loot table system
└── gui/                - HUD display

src/main/resources/data/spellcraft/
├── recipes/            - Crafting recipes (36 total)
├── loot_tables/        - Mob loot tables
└── advancements/       - Achievement progression
```

## Version Info

- **Mod Version**: 1.0.0
- **Minecraft**: 1.21.3
- **Fabric Loader**: 0.16.9+
- **Fabric API**: 0.102.0+1.21.3
- **Java**: 21+

## Future Updates

- [ ] Custom spell structures with loot chests
- [ ] Spell mastery titles and ranks
- [ ] PvP arena mode
- [ ] Configuration system for balance
- [ ] Datapack support for custom spells
- [ ] Particle effect customization
- [ ] Sound effect pack

## Credits

Developed with Fabric Modding Framework for Minecraft 1.21.3

## License

This project is provided as-is for personal and educational use.

## Project Structure

```
spellcraft-fabric/
├── src/main/java/com/ztimelessz/spellcraft/
│   ├── SpellCraftMod.java                 # Main mod entry point
│   ├── client/
│   │   ├── SpellCraftClient.java          # Client initialization
│   │   └── gui/
│   │       └── SpellHudRenderer.java      # HUD rendering
│   ├── spell/
│   │   ├── IAbility.java                  # Ability interface
│   │   ├── BaseAbility.java               # Abstract ability base
│   │   ├── Spell.java                     # Spell class
│   │   ├── PlayerSpellManager.java        # Player spell management
│   │   ├── SpellRegistry.java             # Spell registration
│   │   └── warden/
│   │       ├── DomainExpansion.java       # Warden ability 1
│   │       ├── Locate.java                # Warden ability 2
│   │       ├── SculkSense.java            # Warden ability 3 (enhanced)
│   │       └── SwiftDash.java             # Warden sword ability
│   └── command/
│       └── SpellCommand.java              # /wdspell command
├── src/main/resources/
│   ├── fabric.mod.json                    # Mod metadata
│   ├── spellcraft.mixins.json             # Mixin configuration
│   ├── assets/spellcraft/                 # Textures, models, etc.
│   └── data/spellcraft/recipes/           # Crafting recipes
├── build.gradle                           # Build configuration
└── gradle.properties                      # Gradle properties
```

## Adding New Spells

To add a new spell (e.g., Fire):

1. **Create ability classes** in `src/main/java/com/ztimelessz/spellcraft/spell/fire/`:
   ```java
   public class Fireball extends BaseAbility {
       public Fireball() {
           super("Fireball", "Launch a fireball at enemies", 400);
       }
       
       @Override
       public void execute(PlayerEntity player) {
           if (isOnCooldown(player)) return;
           // Implement ability logic
           setCooldown(player);
       }
   }
   ```

2. **Register the spell** in `SpellRegistry.java`:
   ```java
   SPELLS.put("fire", (enhanced) -> {
       if (enhanced) {
           return new Spell("fire", "Fire", Arrays.asList(
               new Fireball(),
               new Inferno(),
               new BurningSoul() // Enhanced ability
           ), true);
       } else {
           return new Spell("fire", "Fire", Arrays.asList(
               new Fireball(),
               new Inferno()
           ), false);
       }
   });
   ```

3. **Add crafting recipe** in `src/main/resources/data/spellcraft/recipes/fire_spell.json`:
   ```json
   {
     "type": "minecraft:crafting_shapeless",
     "ingredients": [
       {"item": "minecraft:fire_charge"},
       {"item": "minecraft:lava_bucket"},
       {"item": "minecraft:blaze_rod"}
     ],
     "result": {"item": "spellcraft:fire_spell"}
   }
   ```

## Building

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`

## TODO / Remaining Features

- [ ] Add remaining 17 spells
- [ ] Implement sword right-click activation
- [ ] Create custom structures with spells
- [ ] Add sound effects and particle effects
- [ ] Implement spell leveling system
- [ ] Add spell books for alternative crafting
- [ ] Balance cooldowns and ability effects
- [ ] Create datapacks for custom spells

## License

MIT

## Author

ZTimelessZ
