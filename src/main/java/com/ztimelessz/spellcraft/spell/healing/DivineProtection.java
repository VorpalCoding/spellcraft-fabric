package com.ztimelessz.spellcraft.spell.healing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Healing Spell - Ability 3: Divine Protection (Enhanced)
 */
public class DivineProtection extends BaseAbility {
	
	public DivineProtection() {
		super("Divine Protection", "Enhanced - Grant absorption shield to all nearby allies", 700);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		player.heal(10);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 250, 2, false, false));
		
		for (PlayerEntity nearby : serverWorld.getPlayers()) {
			if (nearby != player && nearby.squaredDistanceTo(player) < 400) {
				nearby.heal(8);
				nearby.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, false, false));
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§aDivine Protection!"), false);
		setCooldown(player);
	}
}
