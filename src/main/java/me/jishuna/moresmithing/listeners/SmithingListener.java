package me.jishuna.moresmithing.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.inventory.ItemStack;

import me.jishuna.moresmithing.CustomItem;
import me.jishuna.moresmithing.CustomItemManager;

public class SmithingListener implements Listener {

	private final CustomItemManager manager;

	public SmithingListener(CustomItemManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onPrepareSmith(PrepareSmithingEvent event) {
		ItemStack base = event.getInventory().getItem(0);
		ItemStack addition = event.getInventory().getItem(1);

		if (base == null || addition == null)
			return;

		for (CustomItem customItem : manager.getAllItems()) {
			if (customItem.getBaseItem() == base.getType() && customItem.getSecondaryItem() == addition.getType()) {
				event.setResult(customItem.getItem());
				break;
			}
		}
	}

}
