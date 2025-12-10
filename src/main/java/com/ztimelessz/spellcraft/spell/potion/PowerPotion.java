package com.ztimelessz.spellcraft.spell.potion;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class PowerPotion extends BaseAbility {
	public PowerPotion() { super("Power Potion", "Enhanced - Ultimate boost", 600); }
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.network.ServerPlayerEntity)) return;
		net.minecraft.server.network.ServerPlayerEntity serverPlayer = (net.minecraft.server.network.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld serverWorld = serverPlayer.getServerWorld();
		player.heal(8);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 300, 2, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 2, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§6Power Potion!"), false);
		setCooldown(player);
	}
}
