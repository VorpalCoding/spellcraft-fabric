package com.ztimelessz.spellcraft.spell.dragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Dragon Spell - Ability 3: Ancient Dragon Power (Enhanced)
 */
public class AncientDragonPower extends BaseAbility {
	
	public AncientDragonPower() {
		super("Ancient Dragon Power", "Enhanced - Ultimate dragon power attack", 700);
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
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		// Give player temporary strength
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 150, 2, false, false));
		
		Box box = new Box(
			player.getX() - 35, player.getY() - 35, player.getZ() - 35,
			player.getX() + 35, player.getY() + 35, player.getZ() + 35
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.damage(serverWorld, player.getDamageSources().playerAttack(player), (float)20);
				living.takeKnockback(1.0, player.getX() - entity.getX(), player.getZ() - entity.getZ());
				living.setOnFireFor(15);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 2, false, false));
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§6Ancient Dragon Power!"), false);
		setCooldown(player);
	}
}
