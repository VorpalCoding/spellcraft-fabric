package com.ztimelessz.spellcraft.spell.nature;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class VineStrike extends BaseAbility {
	public VineStrike() {
		super("Vine Strike", "Strike with vines", 220);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(world, player.getDamageSources().magic(), 7);
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§2Vine Strike!"), false);
		setCooldown(player);
	}
}
