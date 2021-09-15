package me.jishuna.moresmithing;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.persistence.PersistentDataType;

import me.jishuna.commonlib.items.ItemBuilder;

public class CustomArmor extends CustomItem implements DurabilityItem {
	
	private final String set;
	private final int maxDurability;

	public CustomArmor(String key, String set, YamlConfiguration config, ItemBuilder builder, int durability) {
		super(key, config, set == null ? builder : builder.withPersistantData(PluginKeys.ITEM_SET.getKey(), PersistentDataType.STRING, set));
		this.set = set;
		this.maxDurability = durability;
	}
	
	@Override
	public int getMaxDurability() {
		return maxDurability;
	}

	public String getSet() {
		return set;
	}

}
