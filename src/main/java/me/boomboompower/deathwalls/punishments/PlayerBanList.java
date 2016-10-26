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

package me.boomboompower.deathwalls.punishments;

import java.util.ArrayList;

public class PlayerBanList {

    private static ArrayList<String> ipBanList = new ArrayList<String>();
    private static ArrayList<String> nameBanList = new ArrayList<String>();

    private PlayerBanList() {}

    public static ArrayList<String> getNameBanList() {
        nameBanList.clear();
        nameBanList.add("Armorsmith");
        return nameBanList;
    }

    public static ArrayList<String> getIPBanList() {
        ipBanList.clear();
        ipBanList.add("0.0.0.0");
        return ipBanList;
    }
}
