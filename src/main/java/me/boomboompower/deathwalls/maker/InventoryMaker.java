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

package me.boomboompower.deathwalls.maker;

import me.boomboompower.deathwalls.utils.Logging;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryMaker {

    private Inventory inventory = Bukkit.createInventory(null, 54, "Inventory");

    public InventoryMaker(String name, int size) {
        this.inventory = Bukkit.createInventory(null, size * 9, Logging.colored(name));
    }

    public void setItem(ItemStack item, int slot) {
        this.inventory.setItem(slot, item);
    }

    public void removeItem(ItemStack item) {
        this.inventory.remove(item);
    }

    public void removeAll() {
        this.inventory.clear();
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
