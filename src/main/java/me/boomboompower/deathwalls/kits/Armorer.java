/*
 *     Copyright (C) 2016 boomboompower
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.boomboompower.deathwalls.kits;

import me.boomboompower.deathwalls.maker.ItemMaker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Armorer {

    public Armorer() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.GOLD_CHESTPLATE);
        maker.setUnbreakable(true);
        maker.setLore("&fGolden chestplate", "&fGolden leggings", "&fGolden boots");
        maker.setName("&bArmorer");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
        inventory.setLeggings(new ItemStack(Material.GOLD_CHESTPLATE));
        inventory.setBoots(new ItemStack(Material.GOLD_BOOTS));
        return inventory;
    }
}
