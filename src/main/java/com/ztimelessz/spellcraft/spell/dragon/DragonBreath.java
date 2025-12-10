package com.ztimelessz.spellcraft.spell.dragon;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.projectile.FireballEntity;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Dragon Spell - Ability 1: Dragon Breath
 */
public class DragonBreath extends BaseAbility {
	
	public DragonBreath() {
		super("Dragon Breath", "Breathe fire in an arc", 320);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		for (int i = 0; i < 3; i++) {
			Vec3d offset = lookDirection.rotateY((i - 1) * 0.3f);
			FireballEntity fireball = new FireballEntity(world, player, offset, 3);
			Vec3d pos = player.getEyePos().add(offset.multiply(1.5));
			fireball.setPos(pos.x, pos.y, pos.z);
			world.spawnEntity(fireball);
		}
		
		player.sendMessage(net.minecraft.text.Text.literal("§6Dragon Breath!"), false);
		setCooldown(player);
	}
}
