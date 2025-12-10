package com.ztimelessz.spellcraft.spell.necromancer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class SoulReaver extends BaseAbility {
	public SoulReaver() {
		super("Soul Reaver", "Enhanced - Drain souls from enemies", 600);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		Box box = new Box(player.getX() - 20, player.getY() - 20, player.getZ() - 20, player.getX() + 20, player.getY() + 20, player.getZ() + 20);
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(serverWorld, player.getDamageSources().magic(), 10.0f);
				player.heal(5);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§5Soul Reaver!"), false);
		setCooldown(player);
	}
}
