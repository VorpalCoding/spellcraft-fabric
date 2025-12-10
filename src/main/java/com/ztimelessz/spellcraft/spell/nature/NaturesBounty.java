package com.ztimelessz.spellcraft.spell.nature;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class NaturesBounty extends BaseAbility {
	public NaturesBounty() {
		super("Nature's Bounty", "Enhanced - Summon nature's power", 600);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		player.heal(10);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 2, false, false));
		Box box = new Box(player.getX() - 20, player.getY() - 20, player.getZ() - 20, player.getX() + 20, player.getY() + 20, player.getZ() + 20);
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(world, player.getDamageSources().magic(), 8);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§2Nature's Bounty!"), false);
		setCooldown(player);
	}
}
