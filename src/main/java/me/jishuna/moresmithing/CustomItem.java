package me.jishuna.moresmithing;

import java.util.Collection;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import me.jishuna.commonlib.items.ItemBuilder;

public abstract class CustomItem {
	
	private final ItemStack item;
	private final String key;
	
	private Material baseItem;
	private Material secondaryItem;
	
	private final Multimap<Class<? extends Event>, EventWrapper<? extends Event>> handlerMap = ArrayListMultimap
			.create();
	
	public CustomItem(String key, YamlConfiguration config, ItemBuilder builder) {
		this.key = key;
		this.item = builder.withPersistantData(PluginKeys.CUSTOM_ITEM_KEY.getKey(), PersistentDataType.STRING, key)
				.build();
		
		addRecipes();
	}
	
	public void addRecipes() {
		
	}

	public <T extends Event> void addEventHandler(Class<T> type, Consumer<T> consumer) {
		this.handlerMap.put(type, new EventWrapper<>(type, consumer));
	}

	public <T extends Event> Collection<EventWrapper<? extends Event>> getEventHandlers(Class<T> type) {
		return this.handlerMap.get(type);
	}

	public String getKey() {
		return key;
	}

	public ItemStack getItem() {
		return item.clone();
	}

	public Material getBaseItem() {
		return baseItem;
	}

	public Material getSecondaryItem() {
		return secondaryItem;
	}
	
	public void setBaseItem(Material baseItem) {
		this.baseItem = baseItem;
	}

	public void setSecondaryItem(Material secondaryItem) {
		this.secondaryItem = secondaryItem;
	}

	public Multimap<Class<? extends Event>, EventWrapper<? extends Event>> getHandlerMap() {
		return handlerMap;
	}

}
