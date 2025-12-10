package com.ztimelessz.spellcraft.spell.warden;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Warden Spell - Ability 1: Domain Expansion
 * Creates a sphere around player and traps nearby entities
 */
public class DomainExpansion extends BaseAbility {
	private static final int RADIUS = 20;
	private static final int DURATION_TICKS = 200; // 10 seconds
	
	public DomainExpansion() {
		super("Domain Expansion", "Creates a sphere around you, trapping and weakening nearby enemies", 600); // 30 second cooldown
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) {
			return;
		}
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		// Create sphere effect
		Box box = new Box(
			player.getX() - RADIUS, player.getY() - RADIUS, player.getZ() - RADIUS,
			player.getX() + RADIUS, player.getY() + RADIUS, player.getZ() + RADIUS
		);
		
		// Apply effects to nearby entities
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof PlayerEntity && entity != player) {
				PlayerEntity targetPlayer = (PlayerEntity) entity;
				// Apply darkness and wither (10 seconds)
				targetPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, DURATION_TICKS, 0, false, false));
				targetPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, DURATION_TICKS, 0, false, false));
				// Apply glowing
				targetPlayer.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, DURATION_TICKS, 0, false, false));
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§5Domain Expansion activated!"), false);
		setCooldown(player);
	}
}
