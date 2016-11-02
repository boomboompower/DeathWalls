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

@KitInfo(name = "Farmer", creator = "boomboompower", version = "1.0")
public final class Farmer {

    public Farmer() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.GOLDEN_APPLE);
        maker.setUnbreakable(true);
        maker.setLore("&7Iron Leggings", "  &8- Projectile Protection III", "&7Egg&8 x64", "&7Gold Apple");
        maker.setName("&aFarmer");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setLeggings(getLeggings());
        inventory.setItem(0, new ItemStack(Material.EGG, 64));
        inventory.setItem(1, new ItemStack(Material.GOLDEN_APPLE));
        return inventory;
    }

    private ItemStack getLeggings() {
        ItemMaker maker = new ItemMaker(Material.IRON_LEGGINGS);
        maker.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
        return maker.getItemStack();
    }
}
