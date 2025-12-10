package com.ztimelessz.spellcraft.loot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.item.Items;

/**
 * Registers custom loot table drops for various mobs and structures
 */
public class CustomLootTables {
	
	public static void registerLootTables() {
		// Warden drops spell gems
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, supplier, setter) -> {
				if (id.equals(Identifier.of("minecraft", "entities/warden"))) {
				LootPool pool = new LootPool.Builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(Registries.ITEM.get(Identifier.of("spellcraft", "warden_ore")))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3))))
					.build();
				supplier.withPool(pool);
			}
			
			// Blazes drop fire gems
				if (id.equals(Identifier.of("minecraft", "entities/blaze"))) {
				LootPool pool = new LootPool.Builder()
					.rolls(ConstantLootNumberProvider.create(1))
					.with(ItemEntry.builder(Registries.ITEM.get(Identifier.of("spellcraft", "fire_ore")))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))))
					.build();
				supplier.withPool(pool);
			}
			
			// Striders drop void gems
				if (id.equals(Identifier.of("minecraft", "entities/enderman"))) {
				LootPool pool = new LootPool.Builder()
					.rolls(ConstantLootNumberProvider.create(1))
				.with(ItemEntry.builder(Registries.ITEM.get(Identifier.of("spellcraft", "void_ore")))
						.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))))
					.build();
				supplier.withPool(pool);
			}
		});
	}
}
