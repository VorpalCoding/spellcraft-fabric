package com.ztimelessz.spellcraft.spell.dragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Dragon Sword Ability: Dragon Cleave
 */
public class DragonCleave extends BaseAbility {
	
	public DragonCleave() {
		super("Dragon Cleave", "Powerful dragon-enhanced slash", 250);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		World world = player.getWorld();
		if (world.isClient) {
			return;
		}
		
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(16);
		player.setVelocity(dashVelocity);
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(6))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().playerAttack(player), 12);
				living.setOnFireFor(5);
				living.takeKnockback(0.8, player.getX() - entity.getX(), player.getZ() - entity.getZ());
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§6Dragon Cleave!"), false);
		setCooldown(player);
	}
}
