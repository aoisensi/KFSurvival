package info.aoisensi.KFSurvival;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class KFSurvival extends JavaPlugin {
	Logger log;
	
	private KFSurvivalCommandExecutor myExecutor;
	@Override
	
	public void onEnable(){
		
		myExecutor = new KFSurvivalCommandExecutor(this);
		getCommand("kfr").setExecutor(myExecutor);
		getCommand("test").setExecutor(myExecutor);
		
		log = this.getLogger();
		log.info("\u001b[31mKFSurvival has been enabled!\u001b[m");
	}
 
	public void onDisable(){
		log.info("\u001b[31mKFSurvival has been disabled!\u001b[m");
	}
}
