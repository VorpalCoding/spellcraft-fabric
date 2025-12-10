package com.ztimelessz.spellcraft.spell.time;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Rewind extends BaseAbility {
	public Rewind() { super("Rewind", "Slow time around you", 400); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		ServerWorld serverWorld = serverPlayer.getServerWorld();
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 250, 1, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§dRewind!"), false);
		setCooldown(player);
	}
}
