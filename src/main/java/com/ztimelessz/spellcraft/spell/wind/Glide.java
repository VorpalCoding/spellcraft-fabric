package com.ztimelessz.spellcraft.spell.wind;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Glide extends BaseAbility {
	public Glide() { super("Glide", "Float gracefully through the air", 350); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 250, 0, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§fGlide!"), false);
		setCooldown(player);
	}
}
