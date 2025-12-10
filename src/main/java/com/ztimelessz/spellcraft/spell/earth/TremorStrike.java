package com.ztimelessz.spellcraft.spell.earth;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Earth Sword Ability: Tremor Strike
 */
public class TremorStrike extends BaseAbility {
	
	public TremorStrike() {
		super("Tremor Strike", "Strike with earth power, causing tremors", 240);
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
		Vec3d dashVelocity = lookDirection.multiply(14);
		player.setVelocity(dashVelocity);
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().playerAttack(player), 9);
				living.takeKnockback(0.7, player.getX() - entity.getX(), player.getZ() - entity.getZ());
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 80, 1, false, false));
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§2Tremor Strike!"), false);
		setCooldown(player);
	}
}
