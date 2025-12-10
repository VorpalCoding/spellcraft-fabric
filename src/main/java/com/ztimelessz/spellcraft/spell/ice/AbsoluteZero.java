package com.ztimelessz.spellcraft.spell.ice;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Ice Spell - Ability 3: Absolute Zero (Enhanced)
 */
public class AbsoluteZero extends BaseAbility {
	
	public AbsoluteZero() {
		super("Absolute Zero", "Enhanced - Freeze everything around you instantly", 550);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		// Apply protective frost to player
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, false, false));
		
		Box box = new Box(
			player.getX() - 25, player.getY() - 25, player.getZ() - 25,
			player.getX() + 25, player.getY() + 25, player.getZ() + 25
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 4, false, false));
					living.damage(world, player.getDamageSources().magic(), (float)8);
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§bAbsolute Zero!"), false);
		setCooldown(player);
	}
}
