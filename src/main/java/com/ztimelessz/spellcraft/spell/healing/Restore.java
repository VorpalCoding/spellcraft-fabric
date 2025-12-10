package com.ztimelessz.spellcraft.spell.healing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Healing Spell - Ability 1: Restore
 */
public class Restore extends BaseAbility {
	
	public Restore() {
		super("Restore", "Heal yourself and nearby allies", 500);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) {
			return;
		}
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		player.heal(6);
		
		for (PlayerEntity nearby : serverWorld.getPlayers()) {
			if (nearby != player && nearby.squaredDistanceTo(player) < 400) {
				nearby.heal(4);
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§aRestore!"), false);
		setCooldown(player);
	}
}
