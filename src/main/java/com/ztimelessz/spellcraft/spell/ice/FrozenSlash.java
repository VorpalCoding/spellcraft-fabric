package com.ztimelessz.spellcraft.spell.ice;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Ice Sword Ability: Frozen Slash
 */
public class FrozenSlash extends BaseAbility {
	
	public FrozenSlash() {
		super("Frozen Slash", "Slash with ice, freezing enemies", 200);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		Vec3d dashVelocity = lookDirection.multiply(13);
		player.setVelocity(dashVelocity);
		
		for (var entity : serverWorld.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(serverWorld, player.getDamageSources().magic(), 6.0f);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2, false, false));
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§bFrozen Slash!"), false);
		setCooldown(player);
	}
}
