package com.ztimelessz.spellcraft.spell.ice;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Ice Spell - Ability 1: Frost Bolt
 */
public class FrostBolt extends BaseAbility {
	
	public FrostBolt() {
		super("Frost Bolt", "Launch a freezing projectile", 280);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		// Get nearby entities and apply slowness
		Box box = new Box(
			player.getX() - 30, player.getY() - 30, player.getZ() - 30,
			player.getX() + 30, player.getY() + 30, player.getZ() + 30
		);
		
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 150, 2, false, false));
				living.damage(serverWorld, player.getDamageSources().magic(), 4.0f);
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§bFrost Bolt!"), false);
		setCooldown(player);
	}
}
