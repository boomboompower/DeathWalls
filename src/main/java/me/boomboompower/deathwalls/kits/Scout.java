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
import org.bukkit.potion.PotionEffectType;

@KitInfo(name = "Scout", creator = "boomboompower", version = "1.0")
public final class Scout {

    public Scout() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.POTION);
        maker.addPotionEffect(PotionEffectType.SPEED.createEffect(40 * 20, 0));
        maker.setUnbreakable(true);
        maker.setLore("&7Stone Sword", "&7Potion of Speed&8x3");
        maker.setName("&aScout");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setItem(0, new ItemStack(Material.STONE_SWORD));
        inventory.setItem(0, getPotion());
        return inventory;
    }

    private ItemStack getPotion() {
        ItemMaker maker = new ItemMaker(Material.POTION);
        maker.setAmount(3);
        maker.addPotionEffect(PotionEffectType.SPEED.createEffect(40 * 20, 1));
        return maker.getItemStack();
    }
}
