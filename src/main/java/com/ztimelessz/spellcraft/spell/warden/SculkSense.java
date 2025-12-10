package com.ztimelessz.spellcraft.spell.warden;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Warden Spell - Ability 2: Sculk Sense
 * Detects nearby players and vibrations within range
 */
public class SculkSense extends BaseAbility {
	private static final int DETECTION_RANGE = 30;
	
	public SculkSense() {
		super("Sculk Sense", "Sense nearby players and vibrations within 30 block radius", 400); // 20 second cooldown
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
		
		// Find nearby players
		Box box = new Box(
			player.getX() - DETECTION_RANGE, player.getY() - DETECTION_RANGE, player.getZ() - DETECTION_RANGE,
			player.getX() + DETECTION_RANGE, player.getY() + DETECTION_RANGE, player.getZ() + DETECTION_RANGE
		);
		
		int foundCount = 0;
		for (PlayerEntity targetPlayer : world.getPlayers()) {
			if (targetPlayer != player && targetPlayer.getBoundingBox().intersects(box)) {
				foundCount++;
				player.sendMessage(
					net.minecraft.text.Text.literal(String.format("§5Detected: %s at (%.1f, %.1f, %.1f)", 
						targetPlayer.getName().getString(), targetPlayer.getX(), targetPlayer.getY(), targetPlayer.getZ())),
					false
				);
			}
		}
		
		if (foundCount == 0) {
			player.sendMessage(net.minecraft.text.Text.literal("§5No vibrations detected nearby"), false);
		}
		
		setCooldown(player);
	}
}
