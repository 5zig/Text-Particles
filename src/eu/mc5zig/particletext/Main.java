package eu.mc5zig.particletext;

import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("test")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length > 0) {
					StringBuffer sb = new StringBuffer();
					for (int i = 0; i < args.length - 1; i++)
						sb.append(args[i] + " ");
					sb.append(args[args.length - 1]);
					characterManager.draw(player, sb.toString());
				}
			}
		}
		return false;
	}

	public CharacterManager getManager() {
		return characterManager;
	}

	public static TPLogger logger() {
		return logger;
	}

}