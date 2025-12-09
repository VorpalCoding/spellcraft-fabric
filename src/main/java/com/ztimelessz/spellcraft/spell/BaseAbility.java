package com.ztimelessz.spellcraft.spell;

import net.minecraft.entity.player.PlayerEntity;
import java.util.HashMap;
import java.util.Map;

/**
 * Base class for all spell abilities
 */
public abstract class BaseAbility implements IAbility {
	protected String name;
	protected String description;
	protected int cooldown;
	protected static Map<String, Long> cooldowns = new HashMap<>();
	
	public BaseAbility(String name, String description, int cooldown) {
		this.name = name;
		this.description = description;
		this.cooldown = cooldown;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public int getCooldown() {
		return cooldown;
	}
	
	@Override
	public boolean isOnCooldown(PlayerEntity player) {
		String key = player.getUuid() + ":" + name;
		if (!cooldowns.containsKey(key)) {
			return false;
		}
		return System.currentTimeMillis() < cooldowns.get(key);
	}
	
	@Override
	public int getRemainingCooldown(PlayerEntity player) {
		if (!isOnCooldown(player)) {
			return 0;
		}
		String key = player.getUuid() + ":" + name;
		long remaining = cooldowns.get(key) - System.currentTimeMillis();
		return (int) Math.ceil(remaining / 50.0); // Convert ms to ticks (50ms per tick)
	}
	
	protected void setCooldown(PlayerEntity player) {
		String key = player.getUuid() + ":" + name;
		cooldowns.put(key, System.currentTimeMillis() + (cooldown * 50L));
	}
	
	@Override
	public abstract void execute(PlayerEntity player);
}
