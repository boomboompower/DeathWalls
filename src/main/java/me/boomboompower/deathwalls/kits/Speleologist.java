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

@KitInfo(name = "Speleologist", creator = "boomboompower", version = "1.0")
public final class Speleologist {

    public Speleologist() {}

    @Deprecated
    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.IRON_PICKAXE);
        maker.setUnbreakable(true);
        maker.setLore("&7Iron Pickaxe", "  &8- Sharpness I", "  &8- Efficiency III", "  &8- Unbreaking III", "&7Stone&8 x16");
        maker.setName("&aSpeleologist");
        return maker.getItemStack();
    }

    @Deprecated
    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(0, getPickaxe());
        inventory.setItem(1, new ItemStack(Material.STONE, 16));
        return inventory;
    }

    private ItemStack getPickaxe() {
        ItemMaker maker = new ItemMaker(Material.IRON_PICKAXE);
        maker.addEnchantment(Enchantment.DIG_SPEED, 3);
        maker.addEnchantment(Enchantment.DURABILITY, 3);
        maker.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        return maker.getItemStack();
    }
}
