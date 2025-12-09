package com.ztimelessz.spellcraft.spell.dragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Dragon Spell - Ability 2: Dragon's Fury
 */
public class DragonsFury extends BaseAbility {
	
	public DragonsFury() {
		super("Dragon's Fury", "Unleash violent dragon power", 400);
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
		
		Box box = new Box(
			player.getX() - 30, player.getY() - 30, player.getZ() - 30,
			player.getX() + 30, player.getY() + 30, player.getZ() + 30
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().playerAttack(player), 15);
				living.knockBack(0.6, player.getX() - entity.getX(), player.getZ() - entity.getZ());
				living.setOnFireFor(8);
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§6Dragon's Fury!"), false);
		setCooldown(player);
	}
}
