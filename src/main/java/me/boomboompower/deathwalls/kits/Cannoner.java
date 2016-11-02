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

@KitInfo(name = "Cannoner", creator = "boomboompower", version = "1.0")
public final class Cannoner {

    public Cannoner() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.TNT);
        maker.setUnbreakable(true);
        maker.setLore("&7TNT&8 x16", "&7Redstone Block&8 x4", "&7Water Bucket", "&7Iron Boots", "  &8- Fall Protection III", "  &8- Blast Protection III");
        maker.setName("&aCannoner");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setBoots(getBoots());
        inventory.setItem(0, new ItemStack(Material.TNT, 16));
        inventory.setItem(1, new ItemStack(Material.REDSTONE_BLOCK, 4));
        inventory.setItem(2, new ItemStack(Material.WATER_BUCKET, 64));
        return inventory;
    }

    private ItemStack getBoots() {
        ItemMaker maker = new ItemMaker(Material.IRON_BOOTS);
        maker.addEnchantment(Enchantment.PROTECTION_FALL, 3);
        maker.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
        return maker.getItemStack();
    }
}
