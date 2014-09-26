package eu.mc5zig.particletext;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import eu.mc5zig.particletext.characters.CharacterManager;
import eu.mc5zig.particletext.utils.TPLogger;

public class Main extends JavaPlugin {

	private static TPLogger logger;

	private CharacterManager characterManager;

	@Override
	public void onEnable() {
		logger = new TPLogger(getLogger());
		characterManager = new CharacterManager(this);
		logger().log(Level.INFO, "Plugin enabled!");
	}

	@Override
	public void onDisable() {

	}

	public CharacterManager getManager() {
		return characterManager;
	}

	public static TPLogger logger() {
		return logger;
	}

}