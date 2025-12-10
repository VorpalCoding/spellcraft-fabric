package com.ztimelessz.spellcraft.event;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.item.SpellSwordItem;

/**
 * Handles right-click ability activation for spell swords
 */
public class SwordAbilityHandler implements UseItemCallback {
	
	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand) {
		if (!(world instanceof net.minecraft.server.world.ServerWorld)) {
			return ActionResult.PASS;
		}
		
		ItemStack itemStack = player.getStackInHand(hand);
		
		// Check if the player is holding a spell sword
		if (itemStack.getItem() instanceof SpellSwordItem) {
			SpellSwordItem swordItem = (SpellSwordItem) itemStack.getItem();
			swordItem.executeAbility(player);
			return ActionResult.SUCCESS;
		}
		
		return ActionResult.PASS;
	}
	
	public static void register() {
		UseItemCallback.EVENT.register(new SwordAbilityHandler());
	}
}
