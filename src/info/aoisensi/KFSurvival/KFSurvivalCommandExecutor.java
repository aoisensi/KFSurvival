package info.aoisensi.KFSurvival;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class KFSurvivalCommandExecutor implements CommandExecutor {
	
	private KFSurvival plugin;
 
	public KFSurvivalCommandExecutor(KFSurvival plugin) {
		this.plugin = plugin;
	}
	
	public boolean onPlayerQuit(PlayerQuitEvent e) {
		String playername = e.getPlayer().getDisplayName();
		if(KFSurvivalGameInfo.CheckJoinGame(playername)) {
			KFSurvivalGameInfo.RemoveGame(playername);
		}
		return false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}
	    
		if (command.getName().equalsIgnoreCase("kfr")) {
		
			if (player != null) {
				if(KFSurvivalGameInfo.IsStart()) {
					player.sendMessage("˜4[KFSurvival]˜C What are you doing?");
					player.sendMessage("˜4[KFSurvival]˜C Our battle has already begun!");
				} else {
					String playername = player.getDisplayName();
					if(KFSurvivalGameInfo.CheckJoinGame(playername)) {
						sender.sendMessage("˜4[KFSurvival]˜6You are unready...");
						KFSurvivalGameInfo.RemoveGame(playername);
						for(Player otherplayer:Bukkit.getOnlinePlayers()){
							if(otherplayer.getDisplayName() != player.getDisplayName()) {
								otherplayer.sendMessage("˜4[KFSurvival]˜6\""+player.getDisplayName()+"\"is unready...");
							}
						}
					} else {
						sender.sendMessage("˜4[KFSurvival]˜2 You are ready!!");
						KFSurvivalGameInfo.JoinGame(playername);
						for(Player otherplayer:Bukkit.getOnlinePlayers()){
							if(otherplayer.getDisplayName() != player.getDisplayName()) {
								if(KFSurvivalGameInfo.CheckJoinGame(otherplayer.getDisplayName())) {
									otherplayer.sendMessage("˜4[KFSurvival]˜2 \""+player.getDisplayName()+"\"is ready!!");
								} else {
									otherplayer.sendMessage("˜4[KFSurvival]˜2 \""+player.getDisplayName()+"\"is ready!! Are you ready yet?");
								}
							}
						}
						if(KFSurvivalGameInfo.Number() == Bukkit.getOnlinePlayers().length) {
							for(Player allplayer:Bukkit.getOnlinePlayers()) {
								allplayer.sendMessage("˜4[KFSurvival]˜C Was the beginning of the game!");
								allplayer.sendMessage("˜4[KFSurvival]˜C Be careful everyone!!");
							}
							KFSurvivalGameInfo.Start();
						}
					}
				}
			}
			return true;
		} else if (command.getName().equalsIgnoreCase("test")) {
			if(player.getDisplayName() == "aoisensi_MGSLove");
				sender.sendMessage("˜00˜11˜22˜33˜44˜55˜66˜77˜88˜99˜AA˜BB˜CC˜DD˜EE˜FF");
				sender.sendMessage(String.valueOf(KFSurvivalGameInfo.Number()));
				sender.sendMessage(String.valueOf(Bukkit.getOnlinePlayers().length));
			return true;
		}
		return false;
	}
}