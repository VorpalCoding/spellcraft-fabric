package com.ztimelessz.spellcraft.spell.nature;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class VineWhip extends BaseAbility {
	public VineWhip() {
		super("Vine Whip", "Strike with nature's vines", 280);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Box box = new Box(player.getX() - 25, player.getY() - 25, player.getZ() - 25, player.getX() + 25, player.getY() + 25, player.getZ() + 25);
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(world, player.getDamageSources().magic(), 6);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 1, false, false));
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§2Vine Whip!"), false);
		setCooldown(player);
	}
}
