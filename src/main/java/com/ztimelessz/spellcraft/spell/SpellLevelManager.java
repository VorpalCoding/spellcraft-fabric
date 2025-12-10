package com.ztimelessz.spellcraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import java.util.HashMap;
import java.util.Map;

/**
 * Tracks spell usage and levels for players
 */
public class SpellLevelManager {
	private static final Map<String, Integer> spellLevels = new HashMap<>();
	private static final Map<String, Integer> spellExperience = new HashMap<>();
	
	public static void addExperience(PlayerEntity player, String spellId, int amount) {
		String key = player.getUuid() + ":" + spellId;
		int currentExp = spellExperience.getOrDefault(key, 0);
		spellExperience.put(key, currentExp + amount);
		
		// Level up every 100 experience points
		int level = spellLevels.getOrDefault(key, 1);
		int expForLevel = level * 100;
		
		if (spellExperience.get(key) >= expForLevel) {
			spellLevels.put(key, level + 1);
			spellExperience.put(key, 0);
			
			player.sendMessage(
				net.minecraft.text.Text.literal("§6§lSpell Level Up! " + spellId + " is now level " + (level + 1)),
				false
			);
		}
		
		saveToNBT(player);
	}
	
	public static int getSpellLevel(PlayerEntity player, String spellId) {
		String key = player.getUuid() + ":" + spellId;
		return spellLevels.getOrDefault(key, 1);
	}
	
	public static int getSpellExperience(PlayerEntity player, String spellId) {
		String key = player.getUuid() + ":" + spellId;
		return spellExperience.getOrDefault(key, 0);
	}
	
	private static void saveToNBT(PlayerEntity player) {
		NbtCompound data = player.getPersistentData();
		NbtCompound spellData = data.getCompound("SpellCraftData").orElse(new NbtCompound());
		
		NbtCompound levels = new NbtCompound();
		spellLevels.forEach((key, level) -> {
			if (key.startsWith(player.getUuid().toString())) {
				String spellId = key.split(":")[1];
				levels.putInt(spellId, level);
			}
		});
		
		spellData.put("SpellLevels", levels);
		data.put("SpellCraftData", spellData);
	}
	
	public static void loadFromNBT(PlayerEntity player) {
		NbtCompound data = player.getPersistentData();
		NbtCompound spellData = data.getCompound("SpellCraftData").orElse(new NbtCompound());
		
		if (spellData.contains("SpellLevels")) {
			NbtCompound levels = spellData.getCompound("SpellLevels").orElse(new NbtCompound());
			for (String spellId : levels.getKeys()) {
				String key = player.getUuid() + ":" + spellId;
				spellLevels.put(key, levels.getInt(spellId));
			}
		}
	}
}
