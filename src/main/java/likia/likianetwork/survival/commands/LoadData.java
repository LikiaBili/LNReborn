package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class LoadData implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp()) {
            if (args[0].equals("get")) {
                sender.sendMessage(ChatColor.GREEN + "返回文件数据: " + ChatColor.YELLOW + main.getData(args[1], args[3], Bukkit.getServer().getPlayer(args[2])));
            }
            if (args[0].equals("set")) {
                if (args[2].equals("*reDEFAULT") && args[1].equals("datamain.yml")) {
                    //FORBIDDEN
                } else {
                    main.setData(args[1],args[3],Bukkit.getServer().getPlayer(args[2]),args[4]);
                    if (main.getData(args[1], args[3], Bukkit.getServer().getPlayer(args[2])).equals(args[4])) {
                        sender.sendMessage(ChatColor.GREEN + "保存OK!");
                    } else {
                        sender.sendMessage(ChatColor.RED + ":(");
                    }

                }
            }
        }else{
            sender.sendMessage(ChatColor.RED+"你没有权限使用该指令！");
        }
        return false;
    }
}
