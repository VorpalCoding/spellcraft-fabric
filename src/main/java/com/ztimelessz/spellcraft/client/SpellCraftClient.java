package com.ztimelessz.spellcraft.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import com.ztimelessz.spellcraft.client.gui.SpellHudRenderer;

public class SpellCraftClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(new SpellHudRenderer());
		System.out.println("[Spell Craft] Client initialized!");
	}
}
