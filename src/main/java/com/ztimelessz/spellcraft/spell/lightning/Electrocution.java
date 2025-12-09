package com.ztimelessz.spellcraft.spell.lightning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Lightning Spell - Ability 3: Electrocution (Enhanced)
 */
public class Electrocution extends BaseAbility {
	
	public Electrocution() {
		super("Electrocution", "Enhanced - Stun and damage all nearby enemies", 600);
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
			player.getX() - 20, player.getY() - 20, player.getZ() - 20,
			player.getX() + 20, player.getY() + 20, player.getZ() + 20
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().magic(), 12);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 150, 2, false, false));
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§eElectrocution!"), false);
		setCooldown(player);
	}
}
