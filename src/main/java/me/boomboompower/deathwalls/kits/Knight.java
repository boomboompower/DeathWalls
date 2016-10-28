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

@KitInfo(name = "Knight", creator = "boomboompower", version = "1.0")
public final class Knight {

    public Knight() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.WOOD_SWORD);
        maker.setUnbreakable(true);
        maker.setLore("&7Golden Helmet", "  &8- Protection I", "&7Golden Sword", "  &8- Knockback I");
        maker.setName("&aKnight");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setHelmet(getHelmet());
        inventory.setItem(0, getSword());
        return inventory;
    }

    private ItemStack getHelmet() {
        ItemMaker maker = new ItemMaker(Material.GOLD_HELMET);
        maker.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
        return maker.getItemStack();
    }

    private ItemStack getSword() {
        ItemMaker maker = new ItemMaker(Material.GOLD_SWORD);
        maker.addEnchantment(Enchantment.KNOCKBACK, 1);
        return maker.getItemStack();
    }
}
