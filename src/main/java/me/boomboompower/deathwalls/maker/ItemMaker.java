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

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemMaker {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    public ItemMaker(Material material) {
        this(new ItemStack(material));
    }

    public ItemMaker(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemStack getItemStack() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void setLore(String... lore) {
        Validate.notNull(lore, "Lore cannot be null!");
        List<String> full = new ArrayList<String>();
        for (String s : lore) {
            full.add(Logging.colored(s));
        }
        itemMeta.setLore(full);
    }

    public void setAmount(int amount) {
        itemStack.setAmount(amount);
    }

    public void addEnchantment(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level > 0 ? level : 1, true);
    }

    public void addFlag(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
    }

    public void removeFlag(ItemFlag... itemFlags) {
        itemMeta.removeItemFlags(itemFlags);
    }

    public void addPotionEffect(PotionEffect potionEffect) {
        if (itemStack.getType() == Material.POTION || itemStack.getType() == Material.LINGERING_POTION || itemStack.getType() == Material.SPLASH_POTION) {
            ((PotionMeta) itemMeta).addCustomEffect(potionEffect, true);
        } else {
            throw new IllegalStateException("ItemStack must be a type of potion!");
        }
    }

    public void removePotionEffect(PotionEffectType potionEffectType) {
        if (itemStack.getType() == Material.POTION || itemStack.getType() == Material.LINGERING_POTION || itemStack.getType() == Material.SPLASH_POTION) {
            ((PotionMeta) itemMeta).removeCustomEffect(potionEffectType);
        } else {
            throw new IllegalStateException("ItemStack must be a type of potion!");
        }
    }

    public void clearEnchantments() {
        itemStack.getEnchantments().clear();
    }

    @Deprecated
    public void setDisplayName(String name) {
        this.setName(name, false);
    }

    public void setName(String name, boolean real) {
        if (real) itemMeta.setDisplayName(name != null ? Logging.colored(name) : "");
    }

    public void setUnbreakable(boolean unbreakable) {
        itemMeta.spigot().setUnbreakable(unbreakable);
        if (unbreakable) addFlag(ItemFlag.HIDE_UNBREAKABLE);
    }
}
