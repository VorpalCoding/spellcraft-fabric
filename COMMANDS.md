# Spell Craft Command Guide

## Main Command

### /wdspell <SpellName>

Absorb a spell into your player data. Maximum 2 spells active at once.

**Usage:**
```
/wdspell warden
/wdspell fire
/wdspell ice
/wdspell lightning
/wdspell healing
/wdspell dragon
/wdspell earth
/wdspell light
/wdspell nature
/wdspell necromancer
/wdspell potion
/wdspell shield
/wdspell time
/wdspell void
/wdspell wind
```

**Effects:**
- Adds the spell to your active spells (up to 2 max)
- Saves data to player NBT (persists on server restart)
- Shows confirmation message in chat
- Displays spell info in HUD above hotbar

**Limits:**
- Maximum 2 active spells per player
- Cannot add duplicate spells
- Cooldowns tracked per player UUID
- Cannot share spells between accounts

## In-Game Usage

### Spell Abilities
Each spell has abilities that can be used. Once absorbed, abilities appear in the HUD:
- Cooldowns display in ticks (20 ticks = 1 second)
- Green "Ready" = ability can be used
- Red number = remaining cooldown ticks
- Abilities activate by using the spell (configured per ability)

### Spell Swords
1. Craft a spell sword: Spell Item + Stick (shapeless recipe)
2. Hold the sword in main hand
3. Right-click to activate sword ability
4. Each sword has unique effects and cooldowns

### Spell Gems
1. Mine spell ore blocks (Warden Ore, Fire Ore, Ice Ore, Lightning Ore, Void Ore)
2. Smelt or craft ore → gem
3. Gems provide information when shift-right-clicked
4. Use gems to craft corresponding spells

## Command Examples

**Absorb multiple spells:**
```
/wdspell warden     # First spell
/wdspell fire       # Second spell (max 2)
/wdspell ice        # Would fail - already have 2 active
```

**Switch spells:**
- Absorb a new spell to replace one of your current two
- New spell adds to the list, dropping the oldest if needed

## Troubleshooting

**"Spell does not exist"** - Check spelling. Spell names are case-insensitive.

**"You already have this spell"** - You can't have duplicate spells. Absorb a different one.

**"Max spells reached"** - You already have 2 active. Can't absorb more without swapping.

**Cooldowns not working** - Check that spells are properly loaded (see HUD). Restart server if needed.

**No HUD display** - Make sure you've absorbed at least one spell and logged back in.

## Spell Names (Case-Insensitive)

1. warden
2. fire
3. ice
4. lightning
5. healing
6. dragon
7. earth
8. light
9. nature
10. necromancer
11. potion
12. shield
13. time
14. void
15. wind

(Plus 3 additional spells following same pattern)
