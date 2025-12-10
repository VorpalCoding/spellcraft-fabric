package com.ztimelessz.spellcraft.spell.wind;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class WindSlash extends BaseAbility {
	public WindSlash() { super("Wind Slash", "Strike with wind power", 240); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) return;
		net.minecraft.server.world.ServerWorld serverWorld = (net.minecraft.server.world.ServerWorld) player.getWorld();
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(16);
		player.setVelocity(dashVelocity);
		for (var entity : serverWorld.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(serverWorld, player.getDamageSources().magic(), 7.0f);
				living.takeKnockback(0.8, player.getX() - entity.getX(), player.getZ() - entity.getZ());
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§fWind Slash!"), false);
		setCooldown(player);
	}
}
