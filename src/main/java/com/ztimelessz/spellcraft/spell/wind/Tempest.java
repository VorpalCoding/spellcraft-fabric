package com.ztimelessz.spellcraft.spell.wind;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Tempest extends BaseAbility {
	public Tempest() { super("Tempest", "Enhanced - Summon a devastating storm", 700); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 2, false, false));
		Box box = new Box(player.getX() - 25, player.getY() - 25, player.getZ() - 25, player.getX() + 25, player.getY() + 25, player.getZ() + 25);
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.takeKnockback(1.5, player.getX() - entity.getX(), player.getZ() - entity.getZ());
				living.damage(serverWorld, player.getDamageSources().magic(), 12.0f);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§fTempest!"), false);
		setCooldown(player);
	}
}
