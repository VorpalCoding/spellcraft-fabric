package com.ztimelessz.spellcraft.spell.earth;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Earth Spell - Ability 1: Earthquake
 */
public class Earthquake extends BaseAbility {
	
	public Earthquake() {
		super("Earthquake", "Shake the ground, damaging nearby enemies", 350);
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
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Box box = new Box(
			player.getX() - 25, player.getY() - 25, player.getZ() - 25,
			player.getX() + 25, player.getY() + 25, player.getZ() + 25
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.damage(world, player.getDamageSources().magic(), (float)8);
				living.takeKnockback(1.5, player.getX() - entity.getX(), player.getZ() - entity.getZ());
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§2Earthquake!"), false);
		setCooldown(player);
	}
}
