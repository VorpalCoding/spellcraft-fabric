package com.ztimelessz.spellcraft.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

/**
 * Registry for all custom items in the mod
 */
public class ModItems {
	// Tool material for spell swords (netherite-tier)
	public static final ToolMaterial SPELL_SWORD_MATERIAL = new ToolMaterial() {
		@Override
		public int getDurability() {
			return 2031;
		}

		@Override
		public float getMiningSpeedMultiplier() {
			return 9.0f;
		}

		@Override
		public float getAttackDamage() {
			return 4.0f;
		}

		@Override
		public int getMiningLevel() {
			return 4;
		}

		@Override
		public int getEnchantability() {
			return 15;
		}

		@Override
		public Ingredient getRepairIngredient() {
			return Ingredient.ofItems(net.minecraft.item.Items.NETHERITE_INGOT);
		}
	};

	// Spell swords
	public static final SpellSwordItem WARDEN_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "warden");
	public static final SpellSwordItem FIRE_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "fire");
	public static final SpellSwordItem ICE_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "ice");
	public static final SpellSwordItem LIGHTNING_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "lightning");
	public static final SpellSwordItem HEALING_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "healing");
	public static final SpellSwordItem DRAGON_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "dragon");
	public static final SpellSwordItem EARTH_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "earth");
	public static final SpellSwordItem LIGHT_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "light");
	public static final SpellSwordItem NATURE_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "nature");
	public static final SpellSwordItem NECROMANCER_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "necromancer");
	public static final SpellSwordItem POTION_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "potion");
	public static final SpellSwordItem SHIELD_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "shield");
	public static final SpellSwordItem TIME_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "time");
	public static final SpellSwordItem VOID_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "void");
	public static final SpellSwordItem WIND_SWORD = new SpellSwordItem(SPELL_SWORD_MATERIAL, "wind");

	// Spell gems
	public static final SpellGemItem WARDEN_GEM = new SpellGemItem("warden", "Warden");
	public static final SpellGemItem FIRE_GEM = new SpellGemItem("fire", "Fire");
	public static final SpellGemItem ICE_GEM = new SpellGemItem("ice", "Ice");
	public static final SpellGemItem LIGHTNING_GEM = new SpellGemItem("lightning", "Lightning");
	public static final SpellGemItem HEALING_GEM = new SpellGemItem("healing", "Healing");
	public static final SpellGemItem DRAGON_GEM = new SpellGemItem("dragon", "Dragon");
	public static final SpellGemItem EARTH_GEM = new SpellGemItem("earth", "Earth");
	public static final SpellGemItem LIGHT_GEM = new SpellGemItem("light", "Light");
	public static final SpellGemItem NATURE_GEM = new SpellGemItem("nature", "Nature");
	public static final SpellGemItem NECROMANCER_GEM = new SpellGemItem("necromancer", "Necromancer");
	public static final SpellGemItem POTION_GEM = new SpellGemItem("potion", "Potion");
	public static final SpellGemItem SHIELD_GEM = new SpellGemItem("shield", "Shield");
	public static final SpellGemItem TIME_GEM = new SpellGemItem("time", "Time");
	public static final SpellGemItem VOID_GEM = new SpellGemItem("void", "Void");
	public static final SpellGemItem WIND_GEM = new SpellGemItem("wind", "Wind");

	public static void registerItems() {
		// Register swords
		registerItem("warden_sword", WARDEN_SWORD);
		registerItem("fire_sword", FIRE_SWORD);
		registerItem("ice_sword", ICE_SWORD);
		registerItem("lightning_sword", LIGHTNING_SWORD);
		registerItem("healing_sword", HEALING_SWORD);
		registerItem("dragon_sword", DRAGON_SWORD);
		registerItem("earth_sword", EARTH_SWORD);
		registerItem("light_sword", LIGHT_SWORD);
		registerItem("nature_sword", NATURE_SWORD);
		registerItem("necromancer_sword", NECROMANCER_SWORD);
		registerItem("potion_sword", POTION_SWORD);
		registerItem("shield_sword", SHIELD_SWORD);
		registerItem("time_sword", TIME_SWORD);
		registerItem("void_sword", VOID_SWORD);
		registerItem("wind_sword", WIND_SWORD);
		
		// Register gems
		registerGem("warden_gem", WARDEN_GEM);
		registerGem("fire_gem", FIRE_GEM);
		registerGem("ice_gem", ICE_GEM);
		registerGem("lightning_gem", LIGHTNING_GEM);
		registerGem("healing_gem", HEALING_GEM);
		registerGem("dragon_gem", DRAGON_GEM);
		registerGem("earth_gem", EARTH_GEM);
		registerGem("light_gem", LIGHT_GEM);
		registerGem("nature_gem", NATURE_GEM);
		registerGem("necromancer_gem", NECROMANCER_GEM);
		registerGem("potion_gem", POTION_GEM);
		registerGem("shield_gem", SHIELD_GEM);
		registerGem("time_gem", TIME_GEM);
		registerGem("void_gem", VOID_GEM);
		registerGem("wind_gem", WIND_GEM);
	}

	private static void registerItem(String name, SpellSwordItem item) {
		Registry.register(Registries.ITEM, new Identifier("spellcraft", name), item);
	}
	
	private static void registerGem(String name, SpellGemItem item) {
		Registry.register(Registries.ITEM, new Identifier("spellcraft", name), item);
	}
	
	public static void registerBlockItem(String name, net.minecraft.block.Block block) {
		net.minecraft.item.BlockItem blockItem = new net.minecraft.item.BlockItem(block, new net.minecraft.item.Item.Settings());
		Registry.register(Registries.ITEM, new Identifier("spellcraft", name), blockItem);
	}
}
