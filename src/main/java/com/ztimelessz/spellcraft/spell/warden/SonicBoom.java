package com.ztimelessz.spellcraft.spell.warden;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Warden Spell - Ability 3: Sonic Boom
 * Enhanced ability - releases a powerful sonic boom that damages and pushes nearby entities
 */
public class SonicBoom extends BaseAbility {
	private static final int RADIUS = 25;
	
	public SonicBoom() {
		super("Sonic Boom", "Release a devastating sonic boom, pushing and damaging all nearby enemies", 500); // 25 second cooldown
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
		
		// Create blast effect
		Box box = new Box(
			player.getX() - RADIUS, player.getY() - RADIUS, player.getZ() - RADIUS,
			player.getX() + RADIUS, player.getY() + RADIUS, player.getZ() + RADIUS
		);
		
		// Apply knockback and damage to nearby entities
		int hitCount = 0;
		for (Entity entity : serverWorld.getOtherEntities(player, box)) {
			if (entity instanceof LivingEntity livingEntity) {
				// Calculate knockback direction
				double dx = entity.getX() - player.getX();
				double dy = entity.getY() - player.getY();
				double dz = entity.getZ() - player.getZ();
				double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
				
				if (distance > 0) {
					// Normalize and apply knockback
					double strength = 2.0;
					livingEntity.setVelocity(
						(dx / distance) * strength,
						(dy / distance) * strength + 0.5,
						(dz / distance) * strength
					);
					
					// Deal damage (4 hearts)
					livingEntity.damage(serverWorld, player.getDamageSources().generic(), 8.0f);
					hitCount++;
				}
			}
		}
		
		player.sendMessage(
			net.minecraft.text.Text.literal(String.format("§6Sonic Boom! Hit %d entities", hitCount)),
			false
		);
		
		setCooldown(player);
	}
}
