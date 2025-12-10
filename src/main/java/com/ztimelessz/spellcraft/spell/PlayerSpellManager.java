package com.ztimelessz.spellcraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages player's active spells (max 2)
 */
public class PlayerSpellManager {
	private static final String SPELL_DATA_KEY = "SpellCraftData";
	private static final String ACTIVE_SPELLS_KEY = "ActiveSpells";
	
	private List<Spell> activeSpells = new ArrayList<>();
	private PlayerEntity player;
	
	public PlayerSpellManager(PlayerEntity player) {
		this.player = player;
		loadSpells();
	}
	
	/**
	 * Add a spell to active spells (max 2)
	 */
	public boolean addSpell(Spell spell) {
		if (activeSpells.size() >= 2) {
			return false;
		}
		activeSpells.add(spell);
		saveSpells();
		return true;
	}
	
	/**
	 * Remove a spell by ID
	 */
	public boolean removeSpell(String spellId) {
		boolean removed = activeSpells.removeIf(s -> s.getSpellId().equals(spellId));
		if (removed) {
			saveSpells();
		}
		return removed;
	}
	
	/**
	 * Get active spells
	 */
	public List<Spell> getActiveSpells() {
		return new ArrayList<>(activeSpells);
	}
	
	/**
	 * Get spell by ID
	 */
	public Spell getSpell(String spellId) {
		for (Spell spell : activeSpells) {
			if (spell.getSpellId().equals(spellId)) {
				return spell;
			}
		}
		return null;
	}
	
	/**
	 * Check if spell is active
	 */
	public boolean hasSpell(String spellId) {
		return getSpell(spellId) != null;
	}
	
	/**
	 * Clear all spells
	 */
	public void clearSpells() {
		activeSpells.clear();
		saveSpells();
	}
	
	private void saveSpells() {
		NbtCompound playerData = player.getPersistentData().orElseGet(NbtCompound::new);
		NbtCompound spellData = new NbtCompound();
		NbtList spellList = new NbtList();
		
		for (Spell spell : activeSpells) {
			NbtCompound spellTag = new NbtCompound();
			spellTag.putString("SpellId", spell.getSpellId());
			spellTag.putString("SpellName", spell.getSpellName());
			spellTag.putBoolean("Enhanced", spell.isEnhanced());
			spellList.add(spellTag);
		}
		
		spellData.put(ACTIVE_SPELLS_KEY, spellList);
		playerData.put(SPELL_DATA_KEY, spellData);
	}
	
	private void loadSpells() {
		NbtCompound playerData = player.getPersistentData().orElse(new NbtCompound());
		if (!playerData.contains(SPELL_DATA_KEY)) {
			return;
		}
		
		NbtCompound spellData = playerData.getCompound(SPELL_DATA_KEY).orElse(new NbtCompound());
		NbtList spellList = spellData.getList(ACTIVE_SPELLS_KEY);
		
		activeSpells.clear();
		for (int i = 0; i < spellList.size(); i++) {
			NbtCompound spellTag = spellList.getCompound(i).orElse(new NbtCompound());
			String spellId = spellTag.getString("SpellId").orElse("");
			Boolean enhanced = spellTag.getBoolean("Enhanced").orElse(false);
			Spell spell = SpellRegistry.getSpell(spellId, enhanced);
			if (spell != null) {
				activeSpells.add(spell);
			}
		}
	}
}
