package com.ztimelessz.spellcraft.spell;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Registry for all spells in the mod
 */
public class SpellRegistry {
	private static final Map<String, SpellFactory> SPELLS = new HashMap<>();
	
	/**
	 * Interface for creating spells
	 */
	@FunctionalInterface
	public interface SpellFactory {
		Spell create(boolean enhanced);
	}
	
	/**
	 * Register all spells
	 */
	public static void registerSpells() {
		// Warden
		SPELLS.put("warden", (enhanced) -> {
			if (enhanced) {
				return new Spell("warden", "Warden", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.warden.DomainExpansion(),
					new com.ztimelessz.spellcraft.spell.warden.Locate(),
					new com.ztimelessz.spellcraft.spell.warden.SculkSense()
				), true);
			} else {
				return new Spell("warden", "Warden", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.warden.DomainExpansion(),
					new com.ztimelessz.spellcraft.spell.warden.Locate()
				), false);
			}
		});
		
		// Fire
		SPELLS.put("fire", (enhanced) -> {
			if (enhanced) {
				return new Spell("fire", "Fire", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.fire.Fireball(),
					new com.ztimelessz.spellcraft.spell.fire.Inferno(),
					new com.ztimelessz.spellcraft.spell.fire.BurningSoul()
				), true);
			} else {
				return new Spell("fire", "Fire", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.fire.Fireball(),
					new com.ztimelessz.spellcraft.spell.fire.Inferno()
				), false);
			}
		});
		
		// Ice
		SPELLS.put("ice", (enhanced) -> {
			if (enhanced) {
				return new Spell("ice", "Ice", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.ice.FrostBolt(),
					new com.ztimelessz.spellcraft.spell.ice.Blizzard(),
					new com.ztimelessz.spellcraft.spell.ice.AbsoluteZero()
				), true);
			} else {
				return new Spell("ice", "Ice", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.ice.FrostBolt(),
					new com.ztimelessz.spellcraft.spell.ice.Blizzard()
				), false);
			}
		});
		
		// Lightning
		SPELLS.put("lightning", (enhanced) -> {
			if (enhanced) {
				return new Spell("lightning", "Lightning", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.lightning.Thunderbolt(),
					new com.ztimelessz.spellcraft.spell.lightning.ChainLightning(),
					new com.ztimelessz.spellcraft.spell.lightning.Electrocution()
				), true);
			} else {
				return new Spell("lightning", "Lightning", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.lightning.Thunderbolt(),
					new com.ztimelessz.spellcraft.spell.lightning.ChainLightning()
				), false);
			}
		});
		
		// Healing
		SPELLS.put("healing", (enhanced) -> {
			if (enhanced) {
				return new Spell("healing", "Healing", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.healing.Restore(),
					new com.ztimelessz.spellcraft.spell.healing.Rejuvenation(),
					new com.ztimelessz.spellcraft.spell.healing.DivineProtection()
				), true);
			} else {
				return new Spell("healing", "Healing", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.healing.Restore(),
					new com.ztimelessz.spellcraft.spell.healing.Rejuvenation()
				), false);
			}
		});
		
		// Dragon
		SPELLS.put("dragon", (enhanced) -> {
			if (enhanced) {
				return new Spell("dragon", "Dragon", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.dragon.DragonBreath(),
					new com.ztimelessz.spellcraft.spell.dragon.DragonsFury(),
					new com.ztimelessz.spellcraft.spell.dragon.AncientDragonPower()
				), true);
			} else {
				return new Spell("dragon", "Dragon", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.dragon.DragonBreath(),
					new com.ztimelessz.spellcraft.spell.dragon.DragonsFury()
				), false);
			}
		});
		
		// Earth
		SPELLS.put("earth", (enhanced) -> {
			if (enhanced) {
				return new Spell("earth", "Earth", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.earth.Earthquake(),
					new com.ztimelessz.spellcraft.spell.earth.StoneArmor(),
					new com.ztimelessz.spellcraft.spell.earth.TitansGrip()
				), true);
			} else {
				return new Spell("earth", "Earth", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.earth.Earthquake(),
					new com.ztimelessz.spellcraft.spell.earth.StoneArmor()
				), false);
			}
		});
		
		// Light
		SPELLS.put("light", (enhanced) -> {
			if (enhanced) {
				return new Spell("light", "Light", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.light.HolyBeam(),
					new com.ztimelessz.spellcraft.spell.light.Radiance(),
					new com.ztimelessz.spellcraft.spell.light.DivineJudgment()
				), true);
			} else {
				return new Spell("light", "Light", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.light.HolyBeam(),
					new com.ztimelessz.spellcraft.spell.light.Radiance()
				), false);
			}
		});
		
		// Nature
		SPELLS.put("nature", (enhanced) -> {
			if (enhanced) {
				return new Spell("nature", "Nature", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.nature.VineWhip(),
					new com.ztimelessz.spellcraft.spell.nature.Regrowth(),
					new com.ztimelessz.spellcraft.spell.nature.NaturesBounty()
				), true);
			} else {
				return new Spell("nature", "Nature", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.nature.VineWhip(),
					new com.ztimelessz.spellcraft.spell.nature.Regrowth()
				), false);
			}
		});
		
		// Necromancer
		SPELLS.put("necromancer", (enhanced) -> {
			if (enhanced) {
				return new Spell("necromancer", "Necromancer", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.necromancer.CurseOfWasting(),
					new com.ztimelessz.spellcraft.spell.necromancer.DarkPact(),
					new com.ztimelessz.spellcraft.spell.necromancer.SoulReaver()
				), true);
			} else {
				return new Spell("necromancer", "Necromancer", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.necromancer.CurseOfWasting(),
					new com.ztimelessz.spellcraft.spell.necromancer.DarkPact()
				), false);
			}
		});
		
		// Potion
		SPELLS.put("potion", (enhanced) -> {
			if (enhanced) {
				return new Spell("potion", "Potion", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.potion.Swift(),
					new com.ztimelessz.spellcraft.spell.potion.Strength(),
					new com.ztimelessz.spellcraft.spell.potion.PowerPotion()
				), true);
			} else {
				return new Spell("potion", "Potion", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.potion.Swift(),
					new com.ztimelessz.spellcraft.spell.potion.Strength()
				), false);
			}
		});
		
		// Shield
		SPELLS.put("shield", (enhanced) -> {
			if (enhanced) {
				return new Spell("shield", "Shield", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.shield.Barrier(),
					new com.ztimelessz.spellcraft.spell.shield.Fortify(),
					new com.ztimelessz.spellcraft.spell.shield.IronSkin()
				), true);
			} else {
				return new Spell("shield", "Shield", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.shield.Barrier(),
					new com.ztimelessz.spellcraft.spell.shield.Fortify()
				), false);
			}
		});
		
		// Time
		SPELLS.put("time", (enhanced) -> {
			if (enhanced) {
				return new Spell("time", "Time", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.time.Rewind(),
					new com.ztimelessz.spellcraft.spell.time.Haste(),
					new com.ztimelessz.spellcraft.spell.time.Chronosphere()
				), true);
			} else {
				return new Spell("time", "Time", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.time.Rewind(),
					new com.ztimelessz.spellcraft.spell.time.Haste()
				), false);
			}
		});
		
		// Void
		SPELLS.put("void", (enhanced) -> {
			if (enhanced) {
				return new Spell("void", "Void", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.void_spell.VoidRift(),
					new com.ztimelessz.spellcraft.spell.void_spell.VoidPull(),
					new com.ztimelessz.spellcraft.spell.void_spell.VoidMastery()
				), true);
			} else {
				return new Spell("void", "Void", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.void_spell.VoidRift(),
					new com.ztimelessz.spellcraft.spell.void_spell.VoidPull()
				), false);
			}
		});
		
		// Wind
		SPELLS.put("wind", (enhanced) -> {
			if (enhanced) {
				return new Spell("wind", "Wind", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.wind.WindGust(),
					new com.ztimelessz.spellcraft.spell.wind.Glide(),
					new com.ztimelessz.spellcraft.spell.wind.Tempest()
				), true);
			} else {
				return new Spell("wind", "Wind", Arrays.asList(
					new com.ztimelessz.spellcraft.spell.wind.WindGust(),
					new com.ztimelessz.spellcraft.spell.wind.Glide()
				), false);
			}
		});
		
		System.out.println("[Spell Craft] Registered " + SPELLS.size() + " spells");
	}
	
	/**
	 * Get a spell by ID
	 */
	public static Spell getSpell(String spellId, boolean enhanced) {
		SpellFactory factory = SPELLS.get(spellId.toLowerCase());
		if (factory == null) {
			return null;
		}
		return factory.create(enhanced);
	}
	
	/**
	 * Get all spell IDs
	 */
	public static java.util.Set<String> getSpellIds() {
		return new java.util.HashSet<>(SPELLS.keySet());
	}
	
	/**
	 * Check if spell exists
	 */
	public static boolean exists(String spellId) {
		return SPELLS.containsKey(spellId.toLowerCase());
	}
	
	/**
	 * Get sword ability for a spell
	 */
	public static IAbility getSwordAbility(String spellType) {
		return SwordAbilityRegistry.getAbility(spellType.toLowerCase());
	}
}
