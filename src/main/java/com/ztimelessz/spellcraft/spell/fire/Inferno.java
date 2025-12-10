package com.ztimelessz.spellcraft.spell.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Fire Spell - Ability 2: Inferno
 */
public class Inferno extends BaseAbility {
	private static final int RADIUS = 15;
	
	public Inferno() {
		super("Inferno", "Ignite the area around you, burning nearby enemies", 400);
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
		
		// Burn nearby entities
		Box box = new Box(
			player.getX() - RADIUS, player.getY() - RADIUS, player.getZ() - RADIUS,
			player.getX() + RADIUS, player.getY() + RADIUS, player.getZ() + RADIUS
		);
		
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.setOnFireFor(10);
				if (living instanceof PlayerEntity && entity != player) {
					living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1, false, false));
				}
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§cInferno activated!"), false);
		setCooldown(player);
	}
}
