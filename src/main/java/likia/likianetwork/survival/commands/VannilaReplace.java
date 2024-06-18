package likia.likianetwork.survival.commands;

import likia.likianetwork.survival.main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VannilaReplace  implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("whitelst")){
            if(sender.isOp()){
                if(args[0] != null){
                    try {
                        main.setData("main", "whitelisted",args[0], Boolean.valueOf(args[1]));
                        sender.sendMessage("§aDone!");
                    }catch (Exception e){
                        sender.sendMessage("§cERROR!");
                    }
                }else {
                    sender.sendMessage("§cThis player dose not exist!");
                }
            }else {
                sender.sendMessage(main.getLang((Player) sender,"message_command_error_notop"));
            }
        }else if(command.getName().equalsIgnoreCase("help")){
            sender.sendMessage(main.getLang((Player) sender,"message_command_help"));
        }
        return false;
    }
}
