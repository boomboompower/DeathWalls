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

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.boomboompower.deathwalls.utils.Logging;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.AbstractMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * A modified version of 'chuky025' code on a thread from spigot.
 * https://www.spigotmc.org/threads/186646/
 */
public class SimpleScoreboard {
    private Objective obj;
    private Scoreboard scoreboard;
    private String title;
    private Map<String, Integer> scores;
    private List<Team> teams;

    public SimpleScoreboard(String title) {
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = Logging.colored(title);
        this.scores = Maps.newLinkedHashMap();
        this.teams = Lists.newArrayList();
    }

    public void blankLine() {
        add(" ");
    }

    public void add(String text) {
        add(text, null);
    }

    public void add(String text, Integer score) {
        Preconditions.checkArgument(text.length() < 48, "text cannot be over 48 characters in length");
        text = fixDuplicates(Logging.colored(text));
        this.scores.put(Logging.colored(text), score);
    }

    private String fixDuplicates(String text) {
        while (this.scores.containsKey(text)) {
            text = Logging.colored(text + "&r");
        }
        if (text.length() > 48) {
            text = text.substring(0, 47);
        }
        return text;
    }

    private Map.Entry<Team, String> createTeam(String text) {
        String result;
        if (text.length() <= 16) return new AbstractMap.SimpleEntry(null, text);
        Team team = this.scoreboard.registerNewTeam("text-" + this.scoreboard.getTeams().size());
        Iterator<String> iterator = Splitter.fixedLength(16).split(text).iterator();
        team.setPrefix(iterator.next());
        result = iterator.next();
        if (text.length() > 32) team.setSuffix(iterator.next());
        this.teams.add(team);
        return new AbstractMap.SimpleEntry(team, result);
    }

    public void build() {
        obj = this.scoreboard.registerNewObjective(this.title.length() > 16 ? this.title.substring(0, 15) : this.title, "dummy");
        obj.setDisplayName(this.title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        int index = this.scores.size();
        for (Map.Entry<String, Integer> text : this.scores.entrySet()) {
            Map.Entry<Team, String> team = createTeam(text.getKey());
            Integer score = text.getValue() != null ? text.getValue() : index;
            String player = team.getValue();
            if (team.getKey() != null) team.getKey().addPlayer(Bukkit.getOfflinePlayer(player));
            obj.getScore(player).setScore(score);
            index--;
        }
    }

    public void reset() {
        this.title = null;
        this.scores.clear();
        for (Team t : this.teams) {
            t.unregister();
        }
        this.teams.clear();
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public void send(Player... players) {
        Player[] arrayOfPlayer;
        int j = (arrayOfPlayer = players).length;
        for (int i = 0; i < j; i++) {
            Player p = arrayOfPlayer[i];
            p.setScoreboard(this.scoreboard);
        }
    }

    public void update(String text, Integer score) {
        if (scores.containsKey(text)) {
            scores.put(text, score);
            for (Team t : teams) {
                if (t.getName().equals(text)) {
                    t.unregister();
                }
            }
            Map.Entry<Team, String> team = createTeam(text);
            OfflinePlayer player = Bukkit.getOfflinePlayer(team.getValue());
            if (team.getKey() != null) team.getKey().addPlayer(player);
            obj.getScore(player).setScore(score);
        }
    }
}