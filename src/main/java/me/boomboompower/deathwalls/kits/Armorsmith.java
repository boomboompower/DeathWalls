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

@KitInfo(name = "Armorsmith", creator = "boomboompower", version = "1.0")
public final class Armorsmith {

    public Armorsmith() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.ANVIL);
        maker.setUnbreakable(true);
        maker.setLore("&7Anvil", "&7Enchanted Book", "  &8- Protection IV", "  &8- Sharpness I", "&7Bottle o' Enchanting&8 x64", "&7Diamond Helmet");
        maker.setName("&aArmorsmith");
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
