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
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(15);
		player.setVelocity(dashVelocity);
		for (var entity : serverWorld.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(serverWorld, player.getDamageSources().magic(), 10f);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 80, 0, false, false));
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§8Void Strike!"), false);
		setCooldown(player);
	}
}
