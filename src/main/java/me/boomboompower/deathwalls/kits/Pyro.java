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
import org.bukkit.potion.PotionEffectType;

@KitInfo(name = "Pyro", creator = "boomboompower", version = "1.0")
public final class Pyro {

    public Pyro() {}

    public ItemStack getIcon() {
        ItemMaker maker = new ItemMaker(Material.FLINT_AND_STEEL);
        maker.setUnbreakable(true);
        maker.setLore("&fIron Chestplate", "&fFlint and Steel", "  &7- Unbreaking X", "&fLava bucket &7x5", "&fSplash Potion of Fire Resistance");
        maker.setName("&aPyro");
        return maker.getItemStack();
    }

    public Inventory getInventory() {
        PlayerInventory inventory = (PlayerInventory) Bukkit.createInventory(null, InventoryType.PLAYER);
        inventory.setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
        inventory.setItem(0, getFlint());
        inventory.setItem(6, getPotion());
        for (int i = 1; i < 6; i++) {
            inventory.setItem(i, new ItemStack(Material.LAVA_BUCKET));
        }
        return inventory;
    }

    private ItemStack getPotion() {
        ItemMaker maker = new ItemMaker(Material.SPLASH_POTION);
        maker.addPotionEffect(PotionEffectType.FIRE_RESISTANCE.createEffect(Integer.MAX_VALUE, 0));
        return maker.getItemStack();
    }

    private ItemStack getFlint() {
        ItemMaker maker = new ItemMaker(Material.FLINT_AND_STEEL);
        maker.addEnchantment(Enchantment.DURABILITY, 10);
        return maker.getItemStack();
    }
}
