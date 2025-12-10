package com.ztimelessz.spellcraft.spell.void_spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class VoidRift extends BaseAbility {
	public VoidRift() { super("Void Rift", "Tear a rift in reality", 380); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) return;
		net.minecraft.server.world.ServerWorld serverWorld = (net.minecraft.server.world.ServerWorld) player.getWorld();
		Box box = new Box(player.getX() - 25, player.getY() - 25, player.getZ() - 25, player.getX() + 25, player.getY() + 25, player.getZ() + 25);
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(serverWorld, player.getDamageSources().magic(), 10f);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§8Void Rift!"), false);
		setCooldown(player);
	}
}
