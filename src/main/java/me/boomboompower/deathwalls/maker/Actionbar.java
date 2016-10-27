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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created entirely by boomboompower
 *
 * @author boomboompower
 */
public class Actionbar {

    private String last;
    private String message;
    private boolean bold = false;
    private boolean magic = false;
    private boolean built = false;
    private boolean italic = false;
    private boolean underlined = false;
    private boolean strikethrough = false;
    private ChatColor color = ChatColor.WHITE;

    public Actionbar(String message) {
        this.message = message;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setMagic(boolean magic) {
        this.magic = magic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public void setUnderlined(boolean underlined) {
        this.underlined = underlined;
    }

    public void setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
    }

    public void build() {
        String full = "";
        if (strikethrough) full = ChatColor.STRIKETHROUGH + full;
        if (underlined) full = ChatColor.UNDERLINE + full;
        if (italic) full = ChatColor.ITALIC + full;
        if (magic) full = ChatColor.MAGIC + full;
        if (bold) full = ChatColor.BOLD + full;
        last = color + full + message.replace('"', '\"');
        built = true;
    }

    public void send(Player player) {
        if (!built) {
            throw new RuntimeException("You must build the message first!");
        } else {
            try {
                Object actionBarJSON = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + last + "\"}");
                Object packet = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), byte.class).newInstance(actionBarJSON, (byte) 2);

                Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);

                playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private Class<?> getNMSClass(String name) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
