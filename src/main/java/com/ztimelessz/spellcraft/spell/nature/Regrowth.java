package com.ztimelessz.spellcraft.spell.nature;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class Regrowth extends BaseAbility {
	public Regrowth() {
		super("Regrowth", "Heal with nature's power", 400);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		if (!(player instanceof net.minecraft.server.entity.ServerPlayerEntity)) return;
		net.minecraft.server.entity.ServerPlayerEntity serverPlayer = (net.minecraft.server.entity.ServerPlayerEntity) player;
		net.minecraft.server.world.ServerWorld world = serverPlayer.getServerWorld();
		
		player.heal(6);
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1, false, false));
		player.sendMessage(net.minecraft.text.Text.literal("§2Regrowth!"), false);
		setCooldown(player);
	}
}
