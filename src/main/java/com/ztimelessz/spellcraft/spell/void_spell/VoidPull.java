package com.ztimelessz.spellcraft.spell.void_spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class VoidPull extends BaseAbility {
	public VoidPull() { super("Void Pull", "Pull enemies into the void", 420); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) return;
		net.minecraft.server.world.ServerWorld serverWorld = (net.minecraft.server.world.ServerWorld) player.getWorld();
		Box box = new Box(player.getX() - 20, player.getY() - 20, player.getZ() - 20, player.getX() + 20, player.getY() + 20, player.getZ() + 20);
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.setPosition(player.getX() + 2, player.getY(), player.getZ() + 2);
				living.damage(serverWorld, player.getDamageSources().magic(), 8f);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§8Void Pull!"), false);
		setCooldown(player);
	}
}
