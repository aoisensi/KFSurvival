package info.aoisensi.KFSurvival;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class KFSurvival extends JavaPlugin implements Listener {
	Logger log;

	@Override
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		log = this.getLogger();
		log.info("\u001b[31mKFSurvival has been enabled!\u001b[m");
	}
 
	public void onDisable(){
		KFSurvivalGameInfo.Reset();
		log.info("\u001b[31mKFSurvival has been disabled!\u001b[m");
	}
	
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
			sender.sendMessage("˜00˜11˜22˜33˜44˜55˜66˜77˜88˜99˜AA˜BB˜CC˜DD˜EE˜FF");
			sender.sendMessage(String.valueOf(KFSurvivalGameInfo.Number()));
			sender.sendMessage(String.valueOf(Bukkit.getOnlinePlayers().length));
			sender.sendMessage(String.valueOf(KFSurvivalGameInfo.IsStart()));
			return true;
		}
		return false;
	}
	
	public boolean onPlayerQuit(PlayerQuitEvent e) {
		System.out.print("out");
		String playername = e.getPlayer().getDisplayName();
		if(KFSurvivalGameInfo.CheckJoinGame(playername)) {
			KFSurvivalGameInfo.RemoveGame(playername);
		}
		File file = new File(File.separator+"\\world\\players\\"+playername+".dat");
		file.delete();
		System.out.print(file.getPath());
		if(Bukkit.getOfflinePlayers().length == 0) {
			KFSurvivalGameInfo.End();
		}
		return false;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if(!KFSurvivalGameInfo.IsStart()) {
			if(e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
				e.setTo(e.getFrom());
				e.getPlayer().sendMessage("˜4[KFSurvival]˜C Can not be moved");
				e.getPlayer().sendMessage("˜4[KFSurvival]˜C because it is not our battle has begun.");
			}
		}
		return;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(!KFSurvivalGameInfo.IsStart()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("˜4[KFSurvival]˜C Can not be break blocks");
			e.getPlayer().sendMessage("˜4[KFSurvival]˜C because it is not our battle has begun.");
		}
	}


}
