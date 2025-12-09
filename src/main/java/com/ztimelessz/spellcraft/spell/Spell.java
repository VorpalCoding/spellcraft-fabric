package com.ztimelessz.spellcraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single spell with its abilities
 */
public class Spell {
	private String spellId;
	private String spellName;
	private List<BaseAbility> abilities;
	private boolean isEnhanced;
	
	public Spell(String spellId, String spellName, List<BaseAbility> abilities, boolean isEnhanced) {
		this.spellId = spellId;
		this.spellName = spellName;
		this.abilities = new ArrayList<>(abilities);
		this.isEnhanced = isEnhanced;
	}
	
	public String getSpellId() {
		return spellId;
	}
	
	public String getSpellName() {
		return spellName;
	}
	
	public List<BaseAbility> getAbilities() {
		return abilities;
	}
	
	public boolean isEnhanced() {
		return isEnhanced;
	}
	
	public BaseAbility getAbility(int index) {
		if (index >= 0 && index < abilities.size()) {
			return abilities.get(index);
		}
		return null;
	}
}
