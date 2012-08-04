package info.aoisensi.KFSurvival;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class KFSurvival extends JavaPlugin {
	Logger log;
	 
	public void onEnable(){
		log = this.getLogger();
		log.info("Your plugin has been enabled!");
	}
 
	public void onDisable(){
		log.info("Your plugin has been disabled.");
	}
}
