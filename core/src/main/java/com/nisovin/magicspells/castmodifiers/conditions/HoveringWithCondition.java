package com.nisovin.magicspells.castmodifiers.conditions;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import com.nisovin.magicspells.castmodifiers.Condition;
import com.nisovin.magicspells.util.magicitems.MagicItem;
import com.nisovin.magicspells.util.magicitems.MagicItems;

public class HoveringWithCondition extends Condition {

    private ItemStack item;

    @Override
    public boolean setVar(String var) {
        if (var == null || var.isEmpty()) return false;

        MagicItem magicItem = MagicItems.getMagicItemFromString(var);
        if (magicItem == null) return false;

        ItemStack itemStack = magicItem.getItemStack();
        if (itemStack == null) return false;

        item = itemStack;
        return true;
    }

    @Override
    public boolean check(LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player)) return false;
        Player player = (Player) livingEntity;
        ItemStack itemStack = player.getOpenInventory().getCursor();
        return itemStack != null && itemStack.isSimilar(item);
    }

    @Override
    public boolean check(LivingEntity livingEntity, LivingEntity target) {
        return check(target);
    }

    @Override
    public boolean check(LivingEntity livingEntity, Location location) {
        return false;
    }

}
