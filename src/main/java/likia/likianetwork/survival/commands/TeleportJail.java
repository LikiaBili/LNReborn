package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportJail implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.getName().equals("LikiaBili")){
            if(((Player)sender).getLocation().getWorld().getName().equalsIgnoreCase("world") || ((Player)sender).getLocation().getWorld().getName().equalsIgnoreCase("world_the_end") || ((Player)sender).getLocation().getWorld().getName().equalsIgnoreCase("world_nether")){
                Player player = (Player) sender;
                Location playerLocation = player.getLocation();

                main.setData("main","worldLocation.World",sender.getName(),playerLocation.getWorld().getName());
                main.setData("main","worldLocation.X",sender.getName(),playerLocation.getX());
                main.setData("main","worldLocation.Y",sender.getName(),playerLocation.getY());
                main.setData("main","worldLocation.Z",sender.getName(),playerLocation.getZ());
                main.setData("main","worldLocation.Yaw",sender.getName(),playerLocation.getYaw());
                main.setData("main","worldLocation.Pitch",sender.getName(),playerLocation.getPitch());
                main.setData("main","worldLocation.Saved",sender.getName(),true);
            }
            Player player = (Player) sender;
            player.teleport(new Location(Bukkit.getWorld("hub"), 78.5,64,234.5,0,0));
            sender.sendMessage(ChatColor.GREEN+"你已被传送至监狱！");
        }
        return false;
    }
}
