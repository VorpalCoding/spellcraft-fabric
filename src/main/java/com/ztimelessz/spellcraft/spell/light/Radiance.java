package com.ztimelessz.spellcraft.spell.light;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Light Spell - Ability 2: Radiance
 */
public class Radiance extends BaseAbility {
	
	public Radiance() {
		super("Radiance", "Emit brilliant light, blinding enemies", 350);
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
		
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0, false, false));
		
		for (PlayerEntity nearby : serverWorld.getPlayers()) {
			if (nearby != player && nearby.squaredDistanceTo(player) < 400) {
				nearby.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0, false, false));
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§eRadiance!"), false);
		setCooldown(player);
	}
}
