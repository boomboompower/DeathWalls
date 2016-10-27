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
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathWalls extends JavaPlugin {

    private boolean offline = false;

    public static void main(String[] args) {

    }

    @Override
    public void onEnable() {
        if (!Bukkit.getServer().getOnlineMode()) {
            offline = true;
            Logging.logToConsole("DeathWalls does not support offline mode servers.", Logging.LogType.WARNING);
            Logging.logToConsole("Please switch to online mode to use this plugin!", Logging.LogType.WARNING);
            Bukkit.getPluginManager().disablePlugin(this);
        } else {
            Logging.logToConsole("&bJava version: &3" + System.getProperty("java.version") + "&b, vendor: &3" + System.getProperty("java.vendor"));
            Logging.logToConsole("&bJava home: &3" + System.getProperty("java.home"));
            Logging.logToConsole("&bOS name: &3" + System.getProperty("os.name") + "&b, version: &3" + System.getProperty("os.version") + "&b, arch: &3" + System.getProperty("os.arch") + "&b.");
            new Players(this);
            new Spectators(this);
        }
    }

    @Override
    public void onDisable() {
        if (offline) {
            Logging.logToConsole("DeathWalls was disabled for an unexpected reason", Logging.LogType.ERROR);
            Logging.logToConsole("Please check the console for any errors.", Logging.LogType.ERROR);
        }
    }
}
