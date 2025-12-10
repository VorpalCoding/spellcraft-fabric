package com.ztimelessz.spellcraft.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.text.Text;
import net.minecraft.sound.SoundEvents;

/**
 * Spell gem items - can be right-clicked to get spell information
 */
public class SpellGemItem extends Item {
	private String spellType;
	private String spellName;
	
	public SpellGemItem(String spellType, String spellName) {
		super(new Settings());
		this.spellType = spellType;
		this.spellName = spellName;
	}
	
	public String getSpellType() {
		return spellType;
	}
	
	public String getSpellName() {
		return spellName;
	}
	
	@Override
	public ActionResult use(World world, PlayerEntity playerEntity, Hand hand) {
		if (world instanceof net.minecraft.server.world.ServerWorld && playerEntity.isSneaking()) {
			// Show spell info on shift-right-click
			playerEntity.sendMessage(Text.literal("§6Spell Gem: " + spellName), false);
			playerEntity.sendMessage(Text.literal("§7Right-click a Spell Crafting Table with this gem to absorb the " + spellName + " spell"), false);
			world.playSound(null, playerEntity.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, net.minecraft.sound.SoundCategory.PLAYERS, 1.0f, 1.0f);
			return ActionResult.SUCCESS;
		}
		return super.use(world, playerEntity, hand);
	}
}
