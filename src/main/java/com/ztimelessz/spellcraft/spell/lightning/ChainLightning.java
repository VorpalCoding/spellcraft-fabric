package com.ztimelessz.spellcraft.spell.lightning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Lightning Spell - Ability 2: Chain Lightning
 */
public class ChainLightning extends BaseAbility {
	
	public ChainLightning() {
		super("Chain Lightning", "Lightning chains between nearby enemies", 400);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) {
			return;
		}
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Box box = new Box(
			player.getX() - 25, player.getY() - 25, player.getZ() - 25,
			player.getX() + 25, player.getY() + 25, player.getZ() + 25
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.damage(world, player.getDamageSources().magic(), (float)10);
				living.setOnFireFor(3);
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§eChain Lightning!"), false);
		setCooldown(player);
	}
}
