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
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

/**
 * A modified version of 'DecisionsYT' code on a thread from spigot.
 * https://www.spigotmc.org/threads/177503/
 */
public class Title {

    private Title() {}

    /**
     * Send a title to player
     * @param player Player to send the title to
     * @param title The text displayed in the title
     * @param subtitle The text displayed in the subtitle
     * @param fadeIn The time the title takes to fade in
     * @param stay The time the title is displayed
     * @param fadeOut The time the title takes to fade out
     */
    public static void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        try {
            title = Logging.colored(title.replace('"', '\"'));
            subtitle = Logging.colored(title.replace('"', '\"'));

            Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");

            Object enumSubtitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
            Object subtitleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
            Object titlePacket = titleConstructor.newInstance(enumTitle, titleChat, fadeIn, stay, fadeOut);
            Object subtitlePacket = titleConstructor.newInstance(enumSubtitle, subtitleChat, fadeIn, stay, fadeOut);

            sendPacket(player, titlePacket);
            sendPacket(player, subtitlePacket);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Send a packet to a player
     * @param player Player to send packet to
     * @param packet Packet to send to player
     */
    private static void sendPacket(Player player, Object packet) {
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Get NMS class using reflection
     * @param name ClassName
     * @return NMS Class
     */
    private static Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server" + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
