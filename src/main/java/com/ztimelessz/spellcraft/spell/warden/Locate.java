package com.ztimelessz.spellcraft.spell.warden;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Warden Spell - Ability 2: Locate
 * Detects nearby players and shows their positions
 */
public class Locate extends BaseAbility {
	private static final int DETECTION_RANGE = 30;
	
	public Locate() {
		super("Locate", "Sense nearby players within 30 blocks and see them through walls", 400); // 20 second cooldown
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
					net.minecraft.text.Text.literal(String.format("§6Found: %s at (%.1f, %.1f, %.1f)", 
						targetPlayer.getName().getString(), targetPlayer.getX(), targetPlayer.getY(), targetPlayer.getZ())),
					false
				);
			}
		}
		
		if (foundCount == 0) {
			player.sendMessage(net.minecraft.text.Text.literal("§6No players detected nearby"), false);
		}
		
		setCooldown(player);
	}
}
