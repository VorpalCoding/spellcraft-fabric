package com.ztimelessz.spellcraft.spell.wind;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class WindGust extends BaseAbility {
	public WindGust() { super("Wind Gust", "Create a gust of wind", 300); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		World world = player.getWorld();
		if (world.isClient) return;
		Box box = new Box(player.getX() - 20, player.getY() - 20, player.getZ() - 20, player.getX() + 20, player.getY() + 20, player.getZ() + 20);
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.knockBack(1.2, player.getX() - entity.getX(), player.getZ() - entity.getZ());
				living.damage(player.getDamageSources().magic(), 5);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§fWind Gust!"), false);
		setCooldown(player);
	}
}
