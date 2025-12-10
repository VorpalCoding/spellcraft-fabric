package com.ztimelessz.spellcraft.spell.healing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Healing Spell - Ability 2: Rejuvenation
 */
public class Rejuvenation extends BaseAbility {
	
	public Rejuvenation() {
		super("Rejuvenation", "Grant regeneration to yourself and allies", 550);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) {
			return;
		}
		net.minecraft.server.world.ServerWorld serverWorld = (net.minecraft.server.world.ServerWorld) player.getWorld();
		
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 2, false, false));
		
		for (PlayerEntity nearby : serverWorld.getPlayers()) {
			if (nearby != player && nearby.squaredDistanceTo(player) < 400) {
				nearby.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1, false, false));
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§aRejuvenation!"), false);
		setCooldown(player);
	}
}
