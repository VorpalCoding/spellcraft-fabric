package com.ztimelessz.spellcraft.spell.ice;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Ice Spell - Ability 2: Blizzard
 */
public class Blizzard extends BaseAbility {
	
	public Blizzard() {
		super("Blizzard", "Create a freezing storm, immobilizing enemies", 450);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		Box box = new Box(
			player.getX() - 20, player.getY() - 20, player.getZ() - 20,
			player.getX() + 20, player.getY() + 20, player.getZ() + 20
		);
		
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 3, false, false));
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 100, 1, false, false));
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§bBlizzard!"), false);
		setCooldown(player);
	}
}
