package com.ztimelessz.spellcraft.spell.shield;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Barrier extends BaseAbility {
	public Barrier() { super("Barrier", "Create a protective shield", 350); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§9Barrier!"), false);
		setCooldown(player);
	}
}
