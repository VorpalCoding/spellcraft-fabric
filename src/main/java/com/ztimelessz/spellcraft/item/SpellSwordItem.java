package com.ztimelessz.spellcraft.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.IAbility;
import com.ztimelessz.spellcraft.spell.SpellRegistry;

/**
 * Custom sword item that executes sword abilities on right-click
 */
public class SpellSwordItem extends ToolItem {
	private String spellType;
	
	public SpellSwordItem(ToolMaterial toolMaterial, String spellType) {
		super(toolMaterial, new Settings());
		this.spellType = spellType;
	}
	
	public String getSpellType() {
		return spellType;
	}
	
	/**
	 * Execute the sword ability when right-clicked
	 */
	public void executeAbility(PlayerEntity player) {
		World world = player.getWorld();
		if (world.isClient) {
			return;
		}
		
		// Get the sword ability from the spell registry
		IAbility swordAbility = SpellRegistry.getSwordAbility(spellType);
		if (swordAbility != null) {
			swordAbility.execute(player);
		}
	}
}
