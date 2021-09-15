package me.jishuna.moresmithing.listeners;

import java.util.Optional;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.jishuna.moresmithing.CustomItem;
import me.jishuna.moresmithing.CustomItemManager;
import me.jishuna.moresmithing.DurabilityItem;
import me.jishuna.moresmithing.PluginKeys;

public class DurabilityListener implements Listener {

	private final CustomItemManager manager;

	public DurabilityListener(CustomItemManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onDurabilityChance(PlayerItemDamageEvent event) {
		ItemStack item = event.getItem();
		int current = getCustomDurability(item);
		if (current == -1)
			return;

		int amount = event.getDamage();

		if (current - amount < 0)
			return;

		int max = -1;
		
		Optional<CustomItem> itemOptional = manager.getCustomItemOptional(item);
		
		if (itemOptional.isPresent() && itemOptional.get() instanceof DurabilityItem durabilityItem) {
			max = durabilityItem.getMaxDurability();
		}
		
		if (max == -1)
			return;

		int durability = current - amount;

		ItemMeta meta = item.getItemMeta();

		if (!(meta instanceof Damageable damageable))
			return;

		event.setDamage(0);		
		meta.getPersistentDataContainer().set(PluginKeys.DURABILITY.getKey(), PersistentDataType.INTEGER, durability);
		damageable.setDamage((int) Math.max(0, Math.ceil(item.getType().getMaxDurability() - item.getType().getMaxDurability() * ((double)durability / max))));
		
		item.setItemMeta(meta);
	}

	private int getCustomDurability(ItemStack item) {
		if (item == null || item.getItemMeta() == null)
			return -1;
		
		ItemMeta itemMeta = item.getItemMeta();
		return itemMeta.getPersistentDataContainer().getOrDefault(PluginKeys.DURABILITY.getKey(),
				PersistentDataType.INTEGER, -1);
	}
}
