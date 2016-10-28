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

package me.boomboompower.deathwalls;

import me.boomboompower.deathwalls.listeners.Players;
import me.boomboompower.deathwalls.listeners.Spectators;
import me.boomboompower.deathwalls.utils.Logging;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathWalls extends JavaPlugin {

    public static void main(String[] args) {}

    @Override
    public void onEnable() {
        Logging.logToConsole("&bUser: &3" + System.getProperty("user.name") + "&b, user home dir: &3" + System.getProperty("user.home"));
        Logging.logToConsole("&bJava version: &3" + System.getProperty("java.version") + "&b, vendor: &3" + System.getProperty("java.vendor"));
        Logging.logToConsole("&bJava home: &3" + System.getProperty("java.home"));
        Logging.logToConsole("&bOS name: &3" + System.getProperty("os.name") + "&b, OS version: &3" + System.getProperty("os.version") + "&b, OS arch: &3" + System.getProperty("os.arch") + "&b.");
        new Players(this);
        new Spectators(this);
    }
}
