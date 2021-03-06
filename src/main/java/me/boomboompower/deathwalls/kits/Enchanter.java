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

@KitInfo(name = "Enchanter", creator = "boomboompower", version = "1.0")
public final class Enchanter {

    public Enchanter() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.BOOKSHELF);
        maker.setUnbreakable(true);
        maker.setLore("&7Enchanting Table", "&7Bottle o' Enchanting&8 x64", "&7Bookshelf&8 x8");
        maker.setName("&aEnchanter");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        inventory.setItem(0, new ItemStack(Material.ANVIL));
        inventory.setItem(1, getBook());
        inventory.setItem(2, new ItemStack(Material.EXP_BOTTLE, 64));
        return inventory;
    }

    private ItemStack getBook() {
        ItemMaker maker = new ItemMaker(Material.ENCHANTED_BOOK);
        maker.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        maker.addEnchantment(Enchantment.DAMAGE_ALL, 1);
        return maker.getItemStack();
    }
}
