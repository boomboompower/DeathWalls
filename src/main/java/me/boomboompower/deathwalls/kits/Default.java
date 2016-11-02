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

@KitInfo(name = "Default", creator = "boomboompower", version = "1.1")
public final class Default {

    public Default() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.WOOD_SWORD);
        maker.setUnbreakable(true);
        maker.setLore("&7Wooden Axe", "&7Wooden Pickaxe", "&7Wooden Shovel");
        maker.setName("&aDefault");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(0, new ItemStack(Material.WOOD_AXE));
        inventory.setItem(1, new ItemStack(Material.WOOD_PICKAXE));
        inventory.setItem(2, new ItemStack(Material.WOOD_SPADE));
        return inventory;
    }
}
