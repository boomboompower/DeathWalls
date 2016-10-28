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

import me.boomboompower.deathwalls.maker.InventoryMaker;

import org.bukkit.inventory.Inventory;

public final class Selector {

    private Inventory selector;

    // TODO organization of the kits
    public Selector() {
        InventoryMaker maker = new InventoryMaker("Kit Selector", 2);
        maker.setItem(new Default().getIcon(), 0);
        maker.setItem(new Armorsmith().getIcon(), 1);
        maker.setItem(new Scout().getIcon(), 2);
        maker.setItem(new Armorer().getIcon(), 3);
        maker.setItem(new Pyro().getIcon(), 4);
        maker.setItem(new Fisherman().getIcon(), 5);
        maker.setItem(new Knight().getIcon(), 6);
        selector = maker.getInventory();
    }

    public Inventory getSelector() {
        return selector;
    }
}
