package eu.mc5zig.textparticles;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import eu.mc5zig.textparticles.characters.CharacterManager;
import eu.mc5zig.textparticles.utils.TPLogger;

public class TextParticles extends JavaPlugin {

	private static TPLogger logger;

	private static CharacterManager characterManager;

	@Override
	public void onEnable() {
		logger = new TPLogger(getLogger());
		characterManager = new CharacterManager(this);
		logger().log(Level.INFO, "Plugin enabled!");
	}

	@Override
	public void onDisable() {

	}

	public static CharacterManager getManager() {
		return characterManager;
	}

	public static TPLogger logger() {
		return logger;
	}

}