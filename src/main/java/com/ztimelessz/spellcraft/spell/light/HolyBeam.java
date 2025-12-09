package com.ztimelessz.spellcraft.spell.light;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Light Spell - Ability 1: Holy Beam
 */
public class HolyBeam extends BaseAbility {
	
	public HolyBeam() {
		super("Holy Beam", "Cast a beam of holy light", 300);
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
				living.damage(player.getDamageSources().magic(), 7);
				if (living instanceof net.minecraft.entity.undead.UndeadEntity) {
					living.damage(player.getDamageSources().magic(), 7);
				}
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§eHoly Beam!"), false);
		setCooldown(player);
	}
}
