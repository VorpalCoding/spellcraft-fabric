package com.ztimelessz.spellcraft.spell.time;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Haste extends BaseAbility {
	public Haste() { super("Haste", "Accelerate everything", 350); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (player.getWorld().isClient) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200, 2, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§dHaste!"), false);
		setCooldown(player);
	}
}
