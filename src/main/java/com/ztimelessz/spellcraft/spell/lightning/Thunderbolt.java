package com.ztimelessz.spellcraft.spell.lightning;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.Vec3d;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Lightning Spell - Ability 1: Thunderbolt
 */
public class Thunderbolt extends BaseAbility {
	
	public Thunderbolt() {
		super("Thunderbolt", "Strike down lightning on enemies", 350);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) {
			return;
		}
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize().multiply(30);
		Vec3d strikePos = player.getPos().add(lookDirection);
		
		LightningEntity lightning = new LightningEntity(net.minecraft.entity.EntityType.LIGHTNING_BOLT, world);
		lightning.setPos(strikePos);
		world.spawnEntity(lightning);
		
		player.sendMessage(net.minecraft.text.Text.literal("§eThunderbolt!"), false);
		setCooldown(player);
	}
}
