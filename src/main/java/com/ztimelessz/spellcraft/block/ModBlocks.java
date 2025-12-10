package com.ztimelessz.spellcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Custom blocks for the spell mod
 */
public class ModBlocks {
	
	// Spell gem ore blocks
	public static final Block WARDEN_ORE = new Block(Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE));
	public static final Block FIRE_ORE = new Block(Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE));
	public static final Block ICE_ORE = new Block(Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE));
	public static final Block LIGHTNING_ORE = new Block(Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE));
	public static final Block VOID_ORE = new Block(Block.Settings.copy(Blocks.DEEPSLATE_DIAMOND_ORE));
	
	// Spell gem blocks (crafted)
	public static final Block WARDEN_BLOCK = new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK));
	public static final Block FIRE_BLOCK = new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK));
	public static final Block ICE_BLOCK = new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK));
	public static final Block LIGHTNING_BLOCK = new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK));
	public static final Block VOID_BLOCK = new Block(Block.Settings.copy(Blocks.DIAMOND_BLOCK));
	
	public static void registerBlocks() {
		// Register ore blocks
		registerBlock("warden_ore", WARDEN_ORE);
		registerBlock("fire_ore", FIRE_ORE);
		registerBlock("ice_ore", ICE_ORE);
		registerBlock("lightning_ore", LIGHTNING_ORE);
		registerBlock("void_ore", VOID_ORE);
		
		// Register gem blocks
		registerBlock("warden_block", WARDEN_BLOCK);
		registerBlock("fire_block", FIRE_BLOCK);
		registerBlock("ice_block", ICE_BLOCK);
		registerBlock("lightning_block", LIGHTNING_BLOCK);
		registerBlock("void_block", VOID_BLOCK);
	}
	
	private static void registerBlock(String name, Block block) {
			Registry.register(Registries.BLOCK, Identifier.of("spellcraft", name), block);
		ModItems.registerBlockItem(name, block);
	}
}
