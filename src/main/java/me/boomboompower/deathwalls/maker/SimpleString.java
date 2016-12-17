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

import org.bukkit.ChatColor;

import java.util.Arrays;

public class SimpleString {

    private final String base;
    private boolean bold = false;
    private boolean magic = false;
    private boolean italic = false;
    private boolean underlined = false;
    private boolean strikethrough = false;
    private ChatColor color = ChatColor.WHITE;

    public SimpleString(String base) {
        this.base = ChatColor.translateAlternateColorCodes('&', base);
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public void setMagic(boolean magic) {
        this.magic = magic;
    }

    public void setColor(ChatColor color) {
        this.color = color != null ? color : ChatColor.WHITE;
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

    public void reset() {
        this.bold = false;
        this.magic = false;
        this.italic = false;
        this.underlined = false;
        this.strikethrough = false;
        this.color = ChatColor.WHITE;
    }

    public void removeFormatting() {
        reset();
        try {
            getClass().getField("base").setAccessible(true);
            getClass().getField("base").set(String.class, ChatColor.stripColor(base));
            getClass().getField("base").setAccessible(false);
            Logging.logToConsole(base, Logging.LogType.DEBUG);
        } catch (Exception ex) {
            Logging.logToConsole("An error occured whilst attempting to modify the \'base\' variable.", Logging.LogType.ERROR);
            ex.printStackTrace();
        }
    }

    public String get() {
        String fin = base;
        if (bold) fin = ChatColor.BOLD + fin;
        if (magic) fin = ChatColor.MAGIC + fin;
        if (italic) fin = ChatColor.ITALIC + fin;
        if (underlined) fin = ChatColor.ITALIC + fin;
        if (strikethrough) fin = ChatColor.STRIKETHROUGH + fin;
        Logging.logToConsole(color + fin, Logging.LogType.DEBUG); // PAIL DEBUG
        return color + fin;
    }

    public char[] getCharArray() {
        Logging.logToConsole(Arrays.toString(get().toCharArray()), Logging.LogType.DEBUG); // PAIL DEBUG
        return get().toCharArray();
    }
}
