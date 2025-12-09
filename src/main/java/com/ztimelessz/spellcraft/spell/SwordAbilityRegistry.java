package com.ztimelessz.spellcraft.spell;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for sword abilities - maps spell type to its sword ability
 */
public class SwordAbilityRegistry {
	private static final Map<String, IAbility> SWORD_ABILITIES = new HashMap<>();
	
	public static void registerSwordAbilities() {
		// Warden
		SWORD_ABILITIES.put("warden", new com.ztimelessz.spellcraft.spell.warden.SwiftDash());
		
		// Fire
		SWORD_ABILITIES.put("fire", new com.ztimelessz.spellcraft.spell.fire.FlameDash());
		
		// Ice
		SWORD_ABILITIES.put("ice", new com.ztimelessz.spellcraft.spell.ice.FrozenSlash());
		
		// Lightning
		SWORD_ABILITIES.put("lightning", new com.ztimelessz.spellcraft.spell.lightning.ThunderSlash());
		
		// Healing
		SWORD_ABILITIES.put("healing", new com.ztimelessz.spellcraft.spell.healing.Lifesteal());
		
		// Dragon
		SWORD_ABILITIES.put("dragon", new com.ztimelessz.spellcraft.spell.dragon.DragonCleave());
		
		// Earth
		SWORD_ABILITIES.put("earth", new com.ztimelessz.spellcraft.spell.earth.TremorStrike());
		
		// Light
		SWORD_ABILITIES.put("light", new com.ztimelessz.spellcraft.spell.light.HolySlash());
		
		// Nature
		SWORD_ABILITIES.put("nature", new com.ztimelessz.spellcraft.spell.nature.VineStrike());
		
		// Necromancer
		SWORD_ABILITIES.put("necromancer", new com.ztimelessz.spellcraft.spell.necromancer.DeathStrike());
		
		// Potion
		SWORD_ABILITIES.put("potion", new com.ztimelessz.spellcraft.spell.potion.PotionSlash());
		
		// Shield
		SWORD_ABILITIES.put("shield", new com.ztimelessz.spellcraft.spell.shield.ShieldBash());
		
		// Time
		SWORD_ABILITIES.put("time", new com.ztimelessz.spellcraft.spell.time.TimeDilation());
		
		// Void
		SWORD_ABILITIES.put("void", new com.ztimelessz.spellcraft.spell.void_spell.VoidStrike());
		
		// Wind
		SWORD_ABILITIES.put("wind", new com.ztimelessz.spellcraft.spell.wind.WindSlash());
	}
	
	public static IAbility getAbility(String spellType) {
		return SWORD_ABILITIES.get(spellType);
	}
	
	public static boolean exists(String spellType) {
		return SWORD_ABILITIES.containsKey(spellType);
	}
}
