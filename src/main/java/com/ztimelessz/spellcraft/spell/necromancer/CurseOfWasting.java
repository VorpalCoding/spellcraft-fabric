package com.ztimelessz.spellcraft.spell.necromancer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class CurseOfWasting extends BaseAbility {
	public CurseOfWasting() {
		super("Curse of Wasting", "Curse enemies, weakening them", 320);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player.getWorld() instanceof net.minecraft.server.world.ServerWorld)) return;
		net.minecraft.server.world.ServerWorld serverWorld = (net.minecraft.server.world.ServerWorld) player.getWorld();
		
		Box box = new Box(player.getX() - 25, player.getY() - 25, player.getZ() - 25, player.getX() + 25, player.getY() + 25, player.getZ() + 25);
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 150, 2, false, false));
				living.damage(serverWorld, player.getDamageSources().magic(), 6.0f);
			}
		});
		player.sendMessage(net.minecraft.text.Text.literal("§5Curse of Wasting!"), false);
		setCooldown(player);
	}
}
