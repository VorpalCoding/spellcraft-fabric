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
		
		World world = player.getWorld();
		if (world.isClient) {
			return;
		}
		
		// Create fireball projectile
		Vec3d lookDirection = player.getRotationVec(1.0f).normalize();
		FireballEntity fireball = new FireballEntity(world, player, 
			lookDirection.x, lookDirection.y, lookDirection.z, 2);
		
		fireball.setPos(player.getEyePos().add(lookDirection.multiply(1.5)));
		world.spawnEntity(fireball);
		
		player.sendMessage(net.minecraft.text.Text.literal("§cFireball launched!"), false);
		setCooldown(player);
	}
}
