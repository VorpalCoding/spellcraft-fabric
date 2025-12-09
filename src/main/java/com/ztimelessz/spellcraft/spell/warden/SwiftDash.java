package com.ztimelessz.spellcraft.spell.warden;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Warden Sword Ability: Swift Dash
 * Dash forward and damage/weaken enemies
 */
public class SwiftDash extends BaseAbility {
	private static final double DASH_DISTANCE = 15;
	private static final float DAMAGE = 8;
	
	public SwiftDash() {
		super("Swift Dash", "Dash forward with your sword, damaging and weakening enemies", 200); // 10 second cooldown
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
		
		// Get player look direction and dash
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(DASH_DISTANCE);
		
		player.setVelocity(dashVelocity);
		
		// Damage nearby entities
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().playerAttack(player), DAMAGE);
				if (living instanceof PlayerEntity) {
					living.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 0, false, false));
					living.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 100, 0, false, false));
				}
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§5Swift Dash executed!"), false);
		setCooldown(player);
	}
}
