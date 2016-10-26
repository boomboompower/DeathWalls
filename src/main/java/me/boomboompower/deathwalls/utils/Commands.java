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

import org.bukkit.entity.Player;

public class Commands {

    private Commands() {}

    public static boolean hasPermissionSilent(Player player, String permission) {
        return player.hasPermission(permission);
    }

    public static String getDenyMessage() {
        return Logging.colored("&cI\'m sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error.");
    }

    private String getArguments(int start, String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < args.length; i++) {
            builder.append(args[i]);
            builder.append(" ");
        }
        return builder.toString().trim();
    }
}
