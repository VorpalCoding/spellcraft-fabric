package com.ztimelessz.spellcraft.spell.potion;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Swift extends BaseAbility {
	public Swift() { super("Swift", "Gain increased speed", 300); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		World world = player.getWorld();
		if (world.isClient) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§eSwift!"), false);
		setCooldown(player);
	}
}
