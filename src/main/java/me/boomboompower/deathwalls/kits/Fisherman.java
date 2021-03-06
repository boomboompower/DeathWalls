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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@KitInfo(name = "Fisherman", creator = "boomboompower", version = "1.0")
public final class Fisherman {

    public Fisherman() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.FISHING_ROD);
        maker.setUnbreakable(true);
        maker.setLore("&7Fishing Rod", "  &8- Knockback I", "  &8- Luck of the Sea X", "  &8- Lure VII", "&7Fish&8 x16");
        maker.setName("&aFisherman");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(0, getRod());
        inventory.setItem(1, new ItemStack(Material.COOKED_FISH, 16));
        return inventory;
    }

    private ItemStack getRod() {
        ItemMaker maker = new ItemMaker(Material.FISHING_ROD);
        maker.addEnchantment(Enchantment.DURABILITY, 10);
        maker.addEnchantment(Enchantment.KNOCKBACK, 1);
        maker.addEnchantment(Enchantment.LUCK, 10);
        maker.addEnchantment(Enchantment.LURE, 7);
        return maker.getItemStack();
    }
}
