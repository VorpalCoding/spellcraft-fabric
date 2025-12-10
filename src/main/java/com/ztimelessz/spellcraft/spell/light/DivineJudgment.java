package com.ztimelessz.spellcraft.spell.light;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

/**
 * Light Spell - Ability 3: Divine Judgment (Enhanced)
 */
public class DivineJudgment extends BaseAbility {
	
	public DivineJudgment() {
		super("Divine Judgment", "Enhanced - Ultimate light purification", 700);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) {
			return;
		}
		
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		
		player.heal(8);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0, false, false));
		
		Box box = new Box(
			player.getX() - 25, player.getY() - 25, player.getZ() - 25,
			player.getX() + 25, player.getY() + 25, player.getZ() + 25
		);
		
		serverWorld.getOtherEntities(player, box).forEach(entity -> {
			if (entity instanceof net.minecraft.entity.LivingEntity && entity != player) {
				net.minecraft.entity.LivingEntity living = (net.minecraft.entity.LivingEntity) entity;
				living.damage(serverWorld, player.getDamageSources().magic(), 15.0f);
				living.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 150, 0, false, false));
			}
		});
		
		player.sendMessage(net.minecraft.text.Text.literal("§eDivine Judgment!"), false);
		setCooldown(player);
	}
}
