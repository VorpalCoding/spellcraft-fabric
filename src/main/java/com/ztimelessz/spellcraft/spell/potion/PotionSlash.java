package com.ztimelessz.spellcraft.spell.potion;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class PotionSlash extends BaseAbility {
	public PotionSlash() { super("Potion Slash", "Drink and strike", 260); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		player.heal(4);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150, 1, false, false));
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.damage(world, player.getDamageSources().playerAttack(player), (float)6);
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§ePotion Slash!"), false);
		setCooldown(player);
	}
}
