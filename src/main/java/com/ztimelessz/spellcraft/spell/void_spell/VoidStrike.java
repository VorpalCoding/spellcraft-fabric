package com.ztimelessz.spellcraft.spell.void_spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class VoidStrike extends BaseAbility {
	public VoidStrike() { super("Void Strike", "Strike with void energy", 300); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		World world = player.getWorld();
		if (world.isClient) return;
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(15);
		player.setVelocity(dashVelocity);
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().magic(), 10);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 0, false, false));
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§8Void Strike!"), false);
		setCooldown(player);
	}
}
