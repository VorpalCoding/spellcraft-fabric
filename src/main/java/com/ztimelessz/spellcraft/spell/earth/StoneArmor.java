package com.ztimelessz.spellcraft.spell.earth;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Earth Spell - Ability 2: Stone Armor
 */
public class StoneArmor extends BaseAbility {
	
	public StoneArmor() {
		super("Stone Armor", "Harden your body with earth", 400);
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
		
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 300, 2, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0, false, false));
		
		player.sendMessage(net.minecraft.text.Text.literal("§2Stone Armor!"), false);
		setCooldown(player);
	}
}
