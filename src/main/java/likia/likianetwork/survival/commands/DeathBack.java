package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.stream.ImageInputStream;
import java.io.File;
import java.io.IOException;

public class DeathBack implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @Nullable String[] args) {
        Player player = (Player) sender;
        if ((Boolean) main.getData("main","death.Saved",sender.getName())){
            double deathPitch = (Double) main.getData("main","death.Pitch",sender.getName());
            double deathYaw = (Double) main.getData("main","death.Yaw",sender.getName());
            Location location = new Location(Bukkit.getWorld((String) main.getData("main","death.World",sender.getName())),(Double) main.getData("main","death.X",sender.getName()),
                    (Double) main.getData("main","death.Y",sender.getName()),(Double) main.getData("main","death.Z",sender.getName()),(int)deathPitch,(int)deathYaw);
            sender.sendMessage(main.getLang(player,"ui_randomtp_done"));
            player.teleport(location);
            return true;
        }
        return false;
    }
}
