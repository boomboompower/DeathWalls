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
import me.boomboompower.interfaces.KitInfo;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@KitInfo(name = "Rookie", creator = "boomboompower", version = "1.0")
public final class Rookie {

    public Rookie() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.GLASS);
        maker.setUnbreakable(true);
        maker.setLore("&7Wooden Sword", "&7Leather Helmet", "&7Leather Chestplate", "&7Leather Leggings", "&7Leather Boots", "&7Glass Blocks&7 x16", "&7Steak");
        maker.setName("&aRookie");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setHelmet(new ItemStack(Material.LEATHER_HELMET));
        inventory.setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
        inventory.setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
        inventory.setBoots(new ItemStack(Material.LEATHER_BOOTS));
        inventory.setItem(0, new ItemStack(Material.WOOD_SWORD));
        inventory.setItem(1, new ItemStack(Material.GLASS, 16));
        inventory.setItem(2, new ItemStack(Material.COOKED_BEEF));
        return inventory;
    }
}
