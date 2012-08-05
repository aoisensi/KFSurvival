package info.aoisensi.KFSurvival;

import java.util.*;

public class KFSurvivalGameInfo {
	private static List<String> onthegame = new ArrayList<String>();
	private static boolean isgamestart = false;
	public static void JoinGame(String username) {
		
		onthegame.add(username);
	}
	
	public static void RemoveGame(String username) {
		
		onthegame.remove(username);
	}
	
	public static boolean CheckJoinGame(String username) {
		for(int i = 0;i<onthegame.size();++i) {
			if( onthegame.get(i) == username) {
				return true;
			}
		}
		return false;
	}
	
	public static int Number() {
		return onthegame.size();
	}
	public static boolean IsStart() {
		return isgamestart;
	}
	public static void Start() {
		isgamestart = true;
	}
}
