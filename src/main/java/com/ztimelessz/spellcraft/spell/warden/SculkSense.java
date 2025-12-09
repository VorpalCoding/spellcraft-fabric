package com.ztimelessz.spellcraft.spell.warden;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Warden Spell - Ability 3: Sculk Sense
 * Enhanced ability - detects sculk sensors within range
 */
public class SculkSense extends BaseAbility {
	
	public SculkSense() {
		super("Sculk Sense", "Enhanced - Sense all vibrations and marked locations in 30 block radius", 300); // 15 second cooldown
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
		
		// Apply temporary enhanced sense (10 seconds)
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200, 0, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 200, 0, false, false));
		
		player.sendMessage(net.minecraft.text.Text.literal("§5Enhanced senses activated!"), false);
		setCooldown(player);
	}
}
