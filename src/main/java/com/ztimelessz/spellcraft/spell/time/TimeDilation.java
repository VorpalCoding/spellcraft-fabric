package com.ztimelessz.spellcraft.spell.time;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class TimeDilation extends BaseAbility {
	public TimeDilation() { super("Time Dilation", "Quick strike through time", 280); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		World world = player.getWorld();
		if (world.isClient) return;
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150, 2, false, false));
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().magic(), 7);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2, false, false));
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§dTime Dilation!"), false);
		setCooldown(player);
	}
}
