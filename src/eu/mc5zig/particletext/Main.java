package eu.mc5zig.particletext;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import eu.mc5zig.particletext.characters.CharacterManager;

public class Main extends JavaPlugin {

	private CharacterManager characterManager;

	@Override
	public void onEnable() {
		characterManager = new CharacterManager(this);
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("test")) {
			characterManager.draw("ABCDEFGHIJKLMNOPQRSTUVWXYZ", (Player) sender);
		}
		return false;
	}

}
