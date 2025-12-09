package com.ztimelessz.spellcraft.client.gui;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import com.ztimelessz.spellcraft.spell.PlayerSpellManager;
import com.ztimelessz.spellcraft.spell.Spell;
import com.ztimelessz.spellcraft.spell.BaseAbility;

public class SpellHudRenderer implements HudRenderCallback {
	private static final int HUD_X = 10;
	private static final int HUD_Y = 60; // Above hotbar
	private static final int BOX_WIDTH = 200;
	private static final int BOX_HEIGHT = 80;
	private static final int LINE_HEIGHT = 15;
	
	@Override
	public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
		MinecraftClient client = MinecraftClient.getInstance();
		if (client.player == null) {
			return;
		}
		
		PlayerEntity player = client.player;
		PlayerSpellManager manager = new PlayerSpellManager(player);
		var spells = manager.getActiveSpells();
		
		if (spells.isEmpty()) {
			return;
		}
		
		int yOffset = HUD_Y;
		
		for (Spell spell : spells) {
			renderSpellBox(drawContext, yOffset, spell, player);
			yOffset += BOX_HEIGHT + 10;
		}
	}
	
	private void renderSpellBox(DrawContext drawContext, int y, Spell spell, PlayerEntity player) {
		// Draw background box
		drawContext.fill(HUD_X, y, HUD_X + BOX_WIDTH, y + BOX_HEIGHT, 0xAA000000);
		
		// Draw border
		drawContext.drawHorizontalLine(HUD_X, HUD_X + BOX_WIDTH, y, 0xFFFFFFFF);
		drawContext.drawHorizontalLine(HUD_X, HUD_X + BOX_WIDTH, y + BOX_HEIGHT, 0xFFFFFFFF);
		drawContext.drawVerticalLine(HUD_X, y, y + BOX_HEIGHT, 0xFFFFFFFF);
		drawContext.drawVerticalLine(HUD_X + BOX_WIDTH, y, y + BOX_HEIGHT, 0xFFFFFFFF);
		
		// Draw spell name and enhancement status
		String spellText = spell.getSpellName() + (spell.isEnhanced() ? " (Enhanced)" : "");
		drawContext.drawText(MinecraftClient.getInstance().textRenderer, 
			Text.literal("§b" + spellText), HUD_X + 5, y + 5, 0xFFFFFF, false);
		
		// Draw abilities and cooldowns
		int abilityY = y + 20;
		int abilityIndex = 1;
		for (BaseAbility ability : spell.getAbilities()) {
			String cooldownText = "";
			if (ability.isOnCooldown(player)) {
				int remaining = ability.getRemainingCooldown(player);
				cooldownText = " §c[" + remaining + "t]";
			} else {
				cooldownText = " §a[Ready]";
			}
			
			String abilityText = abilityIndex + ". " + ability.getName() + cooldownText;
			drawContext.drawText(MinecraftClient.getInstance().textRenderer,
				Text.literal(abilityText), HUD_X + 10, abilityY, 0xFFFFFF, false);
			
			abilityY += LINE_HEIGHT;
			abilityIndex++;
		}
	}
}
