package com.ztimelessz.spellcraft.spell.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Fire Spell - Ability 3: Burning Soul (Enhanced)
 */
public class BurningSoul extends BaseAbility {
	private static final int RADIUS = 20;
	
	public BurningSoul() {
		super("Burning Soul", "Enhanced - Coat yourself in flames, burning and damaging all nearby enemies", 500);
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
		
		// Apply protective fire aura to player
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false));
		
		// Damage and burn enemies
		Box box = new Box(
			player.getX() - RADIUS, player.getY() - RADIUS, player.getZ() - RADIUS,
			player.getX() + RADIUS, player.getY() + RADIUS, player.getZ() + RADIUS
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().onFire(), 6);
				living.setOnFireFor(15);
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§cBurning Soul activated!"), false);
		setCooldown(player);
	}
}
