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

package me.boomboompower.deathwalls.listeners;

import com.google.common.collect.Maps;

import me.boomboompower.deathwalls.DeathWalls;
import me.boomboompower.deathwalls.maker.SimpleScoreboard;
import me.boomboompower.deathwalls.utils.Logging;
import me.boomboompower.deathwalls.utils.Title;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Players implements Listener {

    private DeathWalls deathWalls;
    private ArrayList<Player> players;
    private HashMap<Player, File> data;

    public Players(DeathWalls main) {
        deathWalls = main;
        players = new ArrayList<Player>();
        data = Maps.newHashMap();
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.LOG) {
            if (block.getData() == 1 || block.getData() == 5 || block.getData() == 9) {
                block.setData((byte) 13);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockBreak(BlockBreakEvent event) {
        Location location = event.getBlock().getLocation();
        for (int i = 0; i < 6; i++) {
            event.getPlayer().playEffect(new Location(location.getWorld(), location.getX() + 0.6, location.getY() + 0.6, location.getZ() + 0.6), Effect.COLOURED_DUST, 0);
            event.getPlayer().playEffect(new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5), Effect.COLOURED_DUST, 0);
            event.getPlayer().playEffect(new Location(location.getWorld(), location.getX() + 0.4, location.getY() + 0.4, location.getZ() + 0.4), Effect.COLOURED_DUST, 0);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerDeath(PlayerDeathEvent event) {
        sendScoreboard(event.getEntity().getKiller());
        Title.sendTitle(event.getEntity(),
                "&c&bYOU DIED",
                "&fYou have been eliminated from the game",
                10,
                60,
                10
        );
        for (Player player : players) {
            Logging.sendToPlayer(player,
                    "&e" + event.getEntity().getName() + "&f has been eliminated by &e" + event.getEntity().getKiller().getName() + "&f!");
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerDamage(EntityDamageEvent event) {
        try {
            sendScoreboard((Player) event.getEntity());
        } catch (Exception ex) {}
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerJoin(final PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getActivePotionEffects().clear();
        if (Bukkit.getOnlinePlayers().size() >= 8) {
            player.kickPlayer("&cThe game is full!");
        } else {
            players.add(player);
            sendScoreboard(player);
            File data = new File(deathWalls.getDataFolder(), player.getUniqueId().toString());
            if (!data.exists()) {
                data.mkdirs();
            }

            this.data.put(player, data);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        Entity shooter = (Entity) entity.getShooter();
        String m = "&2%p just threw a %e";

        if (entity instanceof Egg) m = m.replace("%e", "egg");
        if (entity instanceof Arrow) m = m.replace("%e", "arrow");
        if (entity instanceof Snowball) m = m.replace("%e", "snowball");
        if (entity instanceof EnderPearl) m = m.replace("%e", "ender pearl");

        if (shooter instanceof Player) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(Logging.colored(m.replace("%p", shooter.getName())));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onChat(PlayerChatEvent event) {
        event.setFormat(Logging.colored("&f" + event.getPlayer().getName() + ": " + event.getMessage()));
    }

    private void sendScoreboard(Player player) {
        SimpleScoreboard board = new SimpleScoreboard("&c&lDeathWalls");
        board.blankLine();
        board.add("&fPlayers Online: &a" + Bukkit.getOnlinePlayers().size() + "&7/8");
        board.add("&fKills: &a" + player.getStatistic(Statistic.PLAYER_KILLS));
        board.add("&fHealth: &7" + (player.getHealth() / 2) + " &c\u2764");
        board.build();
        board.send(player);
    }
}
