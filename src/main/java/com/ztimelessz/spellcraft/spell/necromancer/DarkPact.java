package com.ztimelessz.spellcraft.spell.necromancer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class DarkPact extends BaseAbility {
	public DarkPact() {
		super("Dark Pact", "Exchange health for power", 350);
	}
	
	@Override
	public void execute(PlayerEntity player) {
		if (isOnCooldown(player)) return;
		World world = player.getWorld();
		if (world.isClient) return;
		
		if (player.getHealth() > 4) {
			player.damage(world.getDamageSources().magic(), 4);
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1, false, false));
		}
		player.sendMessage(net.minecraft.text.Text.literal("§5Dark Pact!"), false);
		setCooldown(player);
	}
}
