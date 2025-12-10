package com.ztimelessz.spellcraft.spell.potion;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Strength extends BaseAbility {
	public Strength() { super("Strength", "Boost your strength", 350); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 250, 2, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§cStrength!"), false);
		setCooldown(player);
	}
}
