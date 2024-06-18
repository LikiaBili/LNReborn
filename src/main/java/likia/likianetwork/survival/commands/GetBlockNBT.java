package likia.likianetwork.survival.commands;

import de.tr7zw.nbtapi.NBTBlock;
import likia.likianetwork.survival.main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.logging.Logger;

public class GetBlockNBT implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp() && main.isDebugOn) {
            if (args.length == 5 || args[3].equals("Material")) {
                try {
                    //new location
                    Location location = new Location(((Player) sender).getWorld(), Integer.valueOf(args[0]), Integer.valueOf(args[1]), Integer.valueOf(args[2]));
                    //get nbt
                    if (args[3].equals("String")) {
                        sender.sendMessage("§a返回值：§e" + new NBTBlock(location.getBlock()).getData().getString(args[4]));
                    } else if (args[3].equals("Material")) {
                        sender.sendMessage("§a返回值：§e" + location.getBlock().getType());
                    } else if (args[3].equals("Integer")) {
                        sender.sendMessage("§a返回值：§e" + new NBTBlock(location.getBlock()).getData().getInteger(args[4]));
                    } else {
                        sender.sendMessage("§c不可用类型！");
                    }
                } catch (Exception nFe) {
                    System.out.println(nFe.getMessage());
                }
            } else {
                sender.sendMessage("§c请输入方块坐标和指定NBT!");
            }
        }else {
            if(sender.isOp()){sender.sendMessage("§c开发者模式未开启！");}
        }
        return false;
    }
}
