package com.ztimelessz.spellcraft.spell.shield;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class IronSkin extends BaseAbility {
	public IronSkin() { super("Iron Skin", "Enhanced - Become nearly invincible", 700); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 400, 3, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 400, 1, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§9Iron Skin!"), false);
		setCooldown(player);
	}
}
