package me.jishuna.moresmithing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class CustomItemManager {

	private final MoreSmithing plugin;
	private final Map<String, CustomItem> customItems = new HashMap<>();

	public CustomItemManager(MoreSmithing plugin) {
		this.plugin = plugin;
	}

	public void registerCustomItem(CustomItem item) {
		this.customItems.put(item.getKey(), item);
	}

	public CustomItem getCustomItem(String key) {
		return this.customItems.get(key);
	}

	public Optional<CustomItem> getCustomItemOptional(String key) {
		return Optional.ofNullable(getCustomItem(key));
	}

	public Optional<CustomItem> getCustomItemOptional(ItemStack item) {
		if (item == null || item.getItemMeta() == null)
			return Optional.empty();
		ItemMeta itemMeta = item.getItemMeta();

		String key = itemMeta.getPersistentDataContainer().getOrDefault(PluginKeys.CUSTOM_ITEM_KEY.getKey(),
				PersistentDataType.STRING, "");
		return Optional.ofNullable(getCustomItem(key));
	}

	public boolean isCustomItem(ItemStack item) {
		if (item == null || item.getItemMeta() == null)
			return false;
		ItemMeta itemMeta = item.getItemMeta();

		String key = itemMeta.getPersistentDataContainer().getOrDefault(PluginKeys.CUSTOM_ITEM_KEY.getKey(),
				PersistentDataType.STRING, "");
		return this.customItems.containsKey(key);
	}
	
	public Collection<CustomItem> getAllItems() {
		return this.customItems.values();
	}
}
