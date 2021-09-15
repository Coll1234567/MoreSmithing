package me.jishuna.moresmithing;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.jishuna.commonlib.utils.FileUtils;
import me.jishuna.moresmithing.armor.cactus.CactusBoots;
import me.jishuna.moresmithing.armor.cactus.CactusChestplate;
import me.jishuna.moresmithing.armor.cactus.CactusHelmet;
import me.jishuna.moresmithing.armor.cactus.CactusLeggings;
import me.jishuna.moresmithing.listeners.DurabilityListener;
import me.jishuna.moresmithing.listeners.SmithingListener;

public class MoreSmithing extends JavaPlugin {
	private CustomItemManager customItemManager;
	private EventManager eventManager;

	@Override
	public void onEnable() {
		PluginKeys.initialize(this);

		this.customItemManager = new CustomItemManager(this);
		this.eventManager = new EventManager(this);

		Bukkit.getPluginManager().registerEvents(new DurabilityListener(this.customItemManager), this);
		Bukkit.getPluginManager().registerEvents(new SmithingListener(this.customItemManager), this);

		registerItems();

		getCommand("giveitem").setExecutor(new TestCommand(this));
	}

	private void registerItems() {
		CustomItemManager manager = this.customItemManager;

		FileUtils.loadResource(this, "items/armor/cactus.yml").ifPresent(config -> {
			manager.registerCustomItem(new CactusHelmet(config));
			manager.registerCustomItem(new CactusChestplate(config));
			manager.registerCustomItem(new CactusLeggings(config));
			manager.registerCustomItem(new CactusBoots(config));
		});
	}

	public CustomItemManager getCustomItemManager() {
		return customItemManager;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

}
