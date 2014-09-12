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
		characterManager = new CharacterManager();
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
					characterManager.draw(sb.toString(), player, 10);
				}
			}
		}
		return false;
	}

}
