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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Logging {

    private Logging() {}

    public static void sendToAll(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendToPlayer(player, message);
        }
    }

    public static void sendToPlayer(Player player, String message) {
        player.sendMessage(colored(message));
    }

    @Deprecated
    public static void logToConsole(String message) {
        logToConsole(message, LogType.REGULAR);
    }

    public static void logToConsole(String message, LogType type) {
        Bukkit.getConsoleSender().sendMessage(colored(type.getMessage() + message));
    }

    public static String colored(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public enum LogType {
        REGULAR("&f"),
        WARNING("&6[WARNING] "),
        ERROR("&c[ERROR] "),
        UNKNOWN("&7[?] ");

        protected String message;

        LogType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
