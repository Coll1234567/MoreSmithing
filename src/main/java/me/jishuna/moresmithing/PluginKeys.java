package me.jishuna.moresmithing;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

public enum PluginKeys {
	CUSTOM_ITEM_KEY("custom_item"), ITEM_SET("item_set"), DURABILITY("durability"), MAX_DURABILITY("max_durability");

	private final String name;
	private NamespacedKey key;

	private PluginKeys(String name) {
		this.name = name;
	}

	public static void initialize(Plugin plugin) {
		for (PluginKeys plguinKey : PluginKeys.values()) {
			plguinKey.key = new NamespacedKey(plugin, plguinKey.name);
		}
	}

	public NamespacedKey getKey() {
		return this.key;
	}

}
