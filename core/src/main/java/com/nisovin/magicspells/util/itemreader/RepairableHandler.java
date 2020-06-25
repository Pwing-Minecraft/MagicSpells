package com.nisovin.magicspells.util.itemreader;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.magicspells.util.magicitems.MagicItemData;

public class RepairableHandler {

	private static final String CONFIG_NAME = "repair-cost";

	public static ItemMeta process(ConfigurationSection config, ItemMeta meta, MagicItemData data) {
		if (!(meta instanceof Repairable)) return meta;
		if (!config.contains(CONFIG_NAME)) return meta;
		if (!config.isInt(CONFIG_NAME)) return meta;

		int repairCost = config.getInt(CONFIG_NAME);

		((Repairable) meta).setRepairCost(repairCost);
		if (data != null) data.setRepairCost(repairCost);

		return meta;
	}

	public static ItemMeta process(ItemMeta meta, MagicItemData data) {
		if (data == null) return meta;
		if (!(meta instanceof Repairable)) return meta;

		int repairCost = data.getRepairCost();
		if (data.getRepairCost() < 0) return meta;

		((Repairable) meta).setRepairCost(repairCost);
		return meta;
	}

	public static MagicItemData process(ItemStack itemStack, MagicItemData itemData) {
		if (itemData == null) return null;
		if (itemStack == null) return itemData;
		if (!(itemStack.getItemMeta() instanceof Repairable)) return itemData;

		itemData.setRepairCost(((Repairable) itemStack.getItemMeta()).getRepairCost());
		return itemData;
	}
	
}
