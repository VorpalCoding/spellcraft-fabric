package com.ztimelessz.spellcraft.spell.time;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Chronosphere extends BaseAbility {
	public Chronosphere() { super("Chronosphere", "Enhanced - Time distortion field", 700); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (player.getWorld().isClient) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE, 300, 3, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 3, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§dChronosphere!"), false);
		setCooldown(player);
	}
}
