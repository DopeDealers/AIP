package net.dopedealers.spigot.listeners;

import net.dopedealers.spigot.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.potion.*;
import org.bukkit.inventory.*;

public class PotionListener implements Listener
{
    public Registry plugin;

    @EventHandler
    public void onSplash(final PotionSplashEvent event) {
        if (event.getEntity() != null) {
            for (final PotionEffect effect : event.getEntity().getEffects()) {
                if (effect != null && effect.getType() != null && effect.getType().equals((Object)PotionEffectType.INVISIBILITY) && ((Player)event.getEntity().getShooter()).hasPermission("aip.blockinvis")) {
                    event.setCancelled(true);
                    ((Player)event.getEntity().getShooter()).getItemInHand().setType(Material.AIR);
                    ((Player)event.getEntity().getShooter()).sendMessage(Registry.config.getString("Messages.InvisSplash").replaceAll("&", "§"));
                }
            }
        }
    }

    @EventHandler
    public void onConsume(final PlayerItemConsumeEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.POTION && event.getItem().getDurability() != 0 && event.getPlayer().hasPermission("aip.blockinvis")) {
            final Potion potion = Potion.fromItemStack(event.getItem());
            if (potion != null) {
                final PotionType type = potion.getType();
                if (type != null && type == PotionType.INVISIBILITY) {
                    event.setCancelled(true);
                    event.setItem((ItemStack)null);
                    event.getPlayer().sendMessage(Registry.config.getString("Messages.InvisDrink").replaceAll("&", "§"));
                }
            }
        }
    }
}
