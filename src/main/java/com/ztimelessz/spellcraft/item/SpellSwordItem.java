package com.ztimelessz.spellcraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.IAbility;
import com.ztimelessz.spellcraft.spell.SpellRegistry;

/**
 * Custom sword item that executes sword abilities on right-click
 */
public class SpellSwordItem extends Item {
	private String spellType;
	
	public SpellSwordItem(String spellType) {
		super(new Settings());
		this.spellType = spellType;
	}
	
	public String getSpellType() {
		return spellType;
	}
	
	/**
	 * Execute the sword ability when right-clicked
	 */
	public void executeAbility(PlayerEntity player) {
		// Check if we're on the server side
		if (player instanceof net.minecraft.server.network.ServerPlayerEntity) {
			// Get the sword ability from the spell registry
			IAbility swordAbility = SpellRegistry.getSwordAbility(spellType);
			if (swordAbility != null) {
				swordAbility.execute(player);
			}
		}
	}
}
