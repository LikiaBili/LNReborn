package likia.likianetwork.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class ChamberOpen implements CommandExecutor{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp() == true){
            if(args.length >= 1){
                Bukkit.getServer().getWorld("hub").getBlockAt(175,65,358).setBlockData(Bukkit.createBlockData(Material.AIR));
                Player player = (Player) sender;
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,999,1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,999,1));
            }else {
                Bukkit.getServer().getWorld("hub").getBlockAt(175,65,358).setBlockData(Bukkit.createBlockData(Material.IRON_BLOCK));
                Player player = (Player) sender;
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        }
        return false;
    }
}
