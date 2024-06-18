package likia.likianetwork.survival.commands;

import de.tr7zw.nbtapi.NBTItem;
import likia.likianetwork.survival.main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetHeldItemNBT implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.isOp() && main.isDebugOn) {
            //get player
            Player player = (Player) sender;
            //get held item in NBTITEM API
            NBTItem helditem = new NBTItem(player.getInventory().getItem(player.getInventory().getHeldItemSlot()));
            //arg0 = type
            //arg1 = key
            if (args[0].equals("String")) {
                sender.sendMessage(ChatColor.YELLOW + "获取的字符串NBT: " + ChatColor.AQUA + '"' + helditem.getString(args[1]) + '"');
            } else if (args[0].equals("Integer")) {
                sender.sendMessage(ChatColor.YELLOW + "获取的整数NBT: " + ChatColor.AQUA + helditem.getInteger(args[1]));
            } else if (args[0].equals("Boolean")) {
                sender.sendMessage(ChatColor.YELLOW + "获取的布尔值NBT: " + ChatColor.AQUA + helditem.getBoolean(args[1]));
            } else if (args[0].equals("Double")) {
                sender.sendMessage(ChatColor.YELLOW + "获取的Double NBT: " + ChatColor.AQUA + helditem.getDouble(args[1]));
            }
        }else {
            if(sender.isOp()){sender.sendMessage("§c开发者模式未开启！");}
        }
        return false;
    }
}
