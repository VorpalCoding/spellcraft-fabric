package com.ztimelessz.spellcraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import net.minecraft.server.command.CommandManager;
import com.ztimelessz.spellcraft.spell.SpellRegistry;
import com.ztimelessz.spellcraft.spell.SwordAbilityRegistry;
import com.ztimelessz.spellcraft.command.SpellCommand;
import com.ztimelessz.spellcraft.item.ModItems;
import com.ztimelessz.spellcraft.block.ModBlocks;
import com.ztimelessz.spellcraft.event.SwordAbilityHandler;

public class SpellCraftMod implements ModInitializer {
	
	public static final String MOD_ID = "spellcraft";
	public static final String MOD_NAME = "Spell Craft";

	@Override
	public void onInitialize() {
		SpellRegistry.registerSpells();
		SwordAbilityRegistry.registerSwordAbilities();
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		SwordAbilityHandler.register();
		
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			SpellCommand.register(dispatcher);
		});
		
		System.out.println("[Spell Craft] Mod initialized!");
	}
}
