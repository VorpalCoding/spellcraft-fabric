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
		World world = player.getWorld();
		if (world.isClient) return;
		player.heal(4);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 150, 1, false, false));
		for (var entity : world.getOtherEntities(player, player.getBoundingBox().expand(5))) {
			if (entity instanceof net.minecraft.entity.LivingEntity) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(player.getDamageSources().playerAttack(player), 6);
			}
		}
		player.sendMessage(net.minecraft.text.Text.literal("§ePotion Slash!"), false);
		setCooldown(player);
	}
}
