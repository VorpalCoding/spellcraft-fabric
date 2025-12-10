package com.ztimelessz.spellcraft.spell.fire;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.projectile.FireballEntity;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Fire Spell - Ability 1: Fireball
 */
public class Fireball extends BaseAbility {
	
	public Fireball() {
		super("Fireball", "Launch a flaming projectile at enemies", 300);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		// Create fireball projectile
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		FireballEntity fireball = new FireballEntity(serverWorld, player, 
			lookDirection.x, lookDirection.y, lookDirection.z, 2);
		
		fireball.setPos(player.getEyePos().add(lookDirection.multiply(1.5)));
		serverWorld.spawnEntity(fireball);
		
		player.sendMessage(net.minecraft.text.Text.literal("§cFireball launched!"), false);
		setCooldown(player);
	}
}
