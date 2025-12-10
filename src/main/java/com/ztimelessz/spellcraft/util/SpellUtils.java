package com.ztimelessz.spellcraft.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

/**
 * Utility methods for spell compatibility with Minecraft 1.21.10
 */
public class SpellUtils {
	
	/**
	 * Safely get ServerWorld from player, returns null if not on server
	 */
	public static ServerWorld getServerWorld(PlayerEntity player) {
		if (player instanceof ServerPlayerEntity serverPlayer) {
			return serverPlayer.getServerWorld();
		}
		return null;
	}
	
	/**
	 * Check if player is on client side
	 */
	public static boolean isClientSide(PlayerEntity player) {
		return !(player instanceof ServerPlayerEntity);
	}
	
	/**
	 * Safely damage entity on server
	 */
	public static void damageEntity(LivingEntity entity, ServerWorld world, float damage) {
		if (world != null) {
			entity.damage(world.getDamageSources().generic(), damage);
		}
	}
	
	/**
	 * Apply knockback to entity
	 */
	public static void applyKnockback(LivingEntity entity, double strength, double dx, double dz) {
		entity.takeKnockback(strength, dx, dz);
	}
}
