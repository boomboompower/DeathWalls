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

package me.boomboompower.deathwalls.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * All entity-related utils.
 */
public class Entities {

    private Entities() {}

	/**
	 * Gets a list of entities from every world.
	 *
	 * @return A list of entities from all worlds
	 */
	public static Collection<? extends Entity> getAllEntities() {
	    List<Entity> entities = new ArrayList<Entity>();
		for (World world : Bukkit.getWorlds()) {
			entities.addAll(world.getEntities());
		}
		return entities;
	}

    /**
     * OOP technique for entity renaming.
     *
     * <p>If the entity has '<i>CustomNameVisible</i>' attribute off it will be toggled one</p>
     *
     * @param entity Entity to rename
     * @param name The name to be given to the entity. Supports colors
     */
	public void rename(Entity entity, String name) {
	    entity.setCustomName(Logging.colored(name));
	    if (!entity.isCustomNameVisible()) entity.setCustomNameVisible(true);
    }
}
