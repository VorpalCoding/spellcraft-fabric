package com.ztimelessz.spellcraft.spell.shield;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class ShieldBash extends BaseAbility {
	public ShieldBash() { super("Shield Bash", "Bash enemies away", 240); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		for (var entity : serverWorld.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.takeKnockback(1.2, player.getX() - entity.getX(), player.getZ() - entity.getZ());
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§9Shield Bash!"), false);
		setCooldown(player);
	}
}
