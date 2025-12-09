package com.ztimelessz.spellcraft.spell.healing;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Healing Sword Ability: Lifesteal
 */
public class Lifesteal extends BaseAbility {
	
	public Lifesteal() {
		super("Lifesteal", "Heal based on damage dealt", 300);
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
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().playerAttack(player), 5);
				player.heal(5);
			}
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§aLifesteal!"), false);
		setCooldown(player);
	}
}
