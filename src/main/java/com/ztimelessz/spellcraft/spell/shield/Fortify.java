package com.ztimelessz.spellcraft.spell.shield;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Fortify extends BaseAbility {
	public Fortify() { super("Fortify", "Strengthen your defense", 400); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (player.getWorld().isClient) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 2, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§9Fortify!"), false);
		setCooldown(player);
	}
}
