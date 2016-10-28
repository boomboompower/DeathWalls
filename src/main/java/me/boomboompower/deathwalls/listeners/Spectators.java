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

import me.boomboompower.deathwalls.DeathWalls;
import me.boomboompower.deathwalls.events.PlayerSpectatorEvent;
import me.boomboompower.deathwalls.maker.Actionbar;
import me.boomboompower.deathwalls.utils.Commands;
import me.boomboompower.deathwalls.utils.Logging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Spectators implements Listener, CommandExecutor {

    private DeathWalls deathWalls;
    private final String command = "spectate";
    private final String permission = "deathwalls.spectate";
    private final ArrayList<Player> spectators = new ArrayList<Player>();

    public Spectators(DeathWalls main) {
        deathWalls = main;

        Commands.register(command, main, this);
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        try {
            if (isSpectator(player)) {
                event.setFormat(Logging.colored("&7[SPECTATOR] " + player.getName() + ": " + event.getMessage()));
                for (Player recipients : event.getRecipients()) {
                    if (!isSpectator(recipients)) {
                        event.getRecipients().remove(recipients);
                    }
                }
            }
        } catch (Exception ex) {}
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockBreak(BlockBreakEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBlockPlace(BlockPlaceEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onArmorStandManipulation(PlayerArmorStandManipulateEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onArrowPickup(PlayerPickupArrowEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) cancel((Player) event.getDamager(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBucket(PlayerBucketEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEvent(PlayerFishEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEvent(PlayerPortalEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onInteractEntity(PlayerInteractEntityEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onEditBook(PlayerEditBookEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onItemPickup(PlayerPickupItemEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onBedEnter(PlayerBedEnterEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onDropItem(PlayerDropItemEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onInteract(PlayerInteractEvent event) {
        cancel(event.getPlayer(), event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    private void onSpectate(PlayerSpectatorEvent event) {
        final Player player = event.getPlayer();
        player.setGameMode(GameMode.ADVENTURE);
        player.setAllowFlight(true);
        player.setPlayerListName(Logging.colored("&7" + player.getName()));
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(onlinePlayers);
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(deathWalls, new Runnable() {
            @Override
            public void run() {
                player.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(Integer.MAX_VALUE, 1));
                Actionbar bar = new Actionbar("You are spectating!");
                bar.setColor(ChatColor.WHITE);
                bar.setBold(true);
                bar.build();
                bar.send(player);
            }
        }, 0, 50);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Logging.colored("&cApologies, only a player can use this command!"));
        } else {
            Player player = (Player) sender;
            if (!Commands.hasPermissionSilent(player, permission)) {
                Logging.sendToPlayer(player, Commands.getDenyMessage());
            } else if (isSpectator(player)) {
                Logging.sendToPlayer(player, "&cSpectators cannot issue this command!");
            } else {
                Logging.sendToAll("&e" + player.getName() + "&f was eliminated.");
                addSpectator(player);
            }
        }
        return true;
    }

    private boolean isSpectator(Player player) {
        return spectators.contains(player);
    }

    private void addSpectator(Player player) {
        PlayerSpectatorEvent event = new PlayerSpectatorEvent(player);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled() && !spectators.contains(player)) {
            spectators.add(player);
        }
    }

    private void cancel(Player player, Cancellable event) {
        if (isSpectator(player)) event.setCancelled(true);
    }
}
