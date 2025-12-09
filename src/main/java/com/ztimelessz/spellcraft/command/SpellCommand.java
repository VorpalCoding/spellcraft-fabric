package com.ztimelessz.spellcraft.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.entity.player.PlayerEntity;
import com.ztimelessz.spellcraft.spell.SpellRegistry;
import com.ztimelessz.spellcraft.spell.Spell;
import com.ztimelessz.spellcraft.spell.PlayerSpellManager;

public class SpellCommand {
	
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		dispatcher.register(CommandManager.literal("wdspell")
			.then(CommandManager.argument("spell", StringArgumentType.word())
				.executes(context -> {
					ServerCommandSource source = context.getSource();
					String spellName = StringArgumentType.getString(context, "spell");
					
					try {
						PlayerEntity player = source.getPlayerOrThrow();
						return withdrawSpell(player, spellName);
					} catch (Exception e) {
						source.sendError(Text.literal("You must be a player to use this command"));
						return 0;
					}
				})
			)
		);
	}
	
	private static int withdrawSpell(PlayerEntity player, String spellName) {
		spellName = spellName.toLowerCase();
		
		if (!SpellRegistry.exists(spellName)) {
			player.sendMessage(Text.literal("§cUnknown spell: " + spellName), false);
			return 0;
		}
		
		PlayerSpellManager manager = new PlayerSpellManager(player);
		
		// Check if already has this spell
		if (manager.hasSpell(spellName)) {
			player.sendMessage(Text.literal("§cYou already have this spell!"), false);
			return 0;
		}
		
		// Get base spell
		Spell spell = SpellRegistry.getSpell(spellName, false);
		
		// Add spell
		if (manager.addSpell(spell)) {
			player.sendMessage(Text.literal("§aAbsorbed " + spell.getSpellName() + " spell!"), false);
			return 1;
		} else {
			player.sendMessage(Text.literal("§cYou can only have 2 active spells at a time!"), false);
			return 0;
		}
	}
}
