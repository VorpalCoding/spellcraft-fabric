package com.ztimelessz.spellcraft.spell.necromancer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class DeathStrike extends BaseAbility {
	public DeathStrike() {
		super("Death Strike", "Strike with dark power", 240);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		World world = player.getWorld();
		if (world.isClient) return;
		
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().magic(), 8);
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§5Death Strike!"), false);
		setCooldown(player);
	}
}
