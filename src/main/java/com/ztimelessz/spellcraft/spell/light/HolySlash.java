package com.ztimelessz.spellcraft.spell.light;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Light Sword Ability: Holy Slash
 */
public class HolySlash extends BaseAbility {
	
	public HolySlash() {
		super("Holy Slash", "Slash with divine light", 220);
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
		Vec3d dashVelocity = lookDirection.multiply(13);
		player.setVelocity(dashVelocity);
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().magic(), 7);
				if (living instanceof net.minecraft.entity.undead.UndeadEntity) {
					living.damage(player.getDamageSources().magic(), 7);
				}
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§eHoly Slash!"), false);
		setCooldown(player);
	}
}
