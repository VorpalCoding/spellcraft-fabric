package com.ztimelessz.spellcraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

/**
 * Base interface for spell abilities
 */
public interface IAbility {
	/**
	 * Execute the ability
	 */
	void execute(PlayerEntity player);
	
	/**
	 * Get cooldown in ticks (20 ticks = 1 second)
	 */
	int getCooldown();
	
	/**
	 * Check if ability is on cooldown
	 */
	boolean isOnCooldown(PlayerEntity player);
	
	/**
	 * Get remaining cooldown ticks
	 */
	int getRemainingCooldown(PlayerEntity player);
	
	/**
	 * Get ability name
	 */
	String getName();
	
	/**
	 * Get ability description
	 */
	String getDescription();
}
