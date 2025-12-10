package com.ztimelessz.spellcraft.spell.lightning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Lightning Sword Ability: Thunder Slash
 */
public class ThunderSlash extends BaseAbility {
	
	public ThunderSlash() {
		super("Thunder Slash", "Slash with electricity", 220);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) {
			return;
		}
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(14);
		player.setVelocity(dashVelocity);
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.damage(world, player.getDamageSources().magic(), (float)8);
				living.setOnFireFor(2);
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§eThunder Slash!"), false);
		setCooldown(player);
	}
}
