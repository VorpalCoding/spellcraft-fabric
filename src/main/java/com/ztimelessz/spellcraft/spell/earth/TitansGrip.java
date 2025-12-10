package com.ztimelessz.spellcraft.spell.earth;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Earth Spell - Ability 3: Titan's Grip (Enhanced)
 */
public class TitansGrip extends BaseAbility {
	
	public TitansGrip() {
		super("Titan's Grip", "Enhanced - Immobilize and crush enemies", 600);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) {
			return;
		}
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		Box box = new Box(
			player.getX() - 20, player.getY() - 20, player.getZ() - 20,
			player.getX() + 20, player.getY() + 20, player.getZ() + 20
		);
		
		world.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
					living.damage(world, player.getDamageSources().magic(), (float)12);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 4, false, false));
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§2Titan's Grip!"), false);
		setCooldown(player);
	}
}
