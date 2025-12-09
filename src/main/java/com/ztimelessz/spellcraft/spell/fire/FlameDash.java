package com.ztimelessz.spellcraft.spell.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Fire Sword Ability: Flame Dash
 */
public class FlameDash extends BaseAbility {
	private static final double DASH_DISTANCE = 12;
	
	public FlameDash() {
		super("Flame Dash", "Dash with fire, damaging enemies in your path", 180);
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
		Vec3d dashVelocity = lookDirection.multiply(DASH_DISTANCE);
		player.setVelocity(dashVelocity);
		
		// Burn enemies
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.setOnFireFor(8);
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§cFlame Dash!"), false);
		setCooldown(player);
	}
}
