package eu.mc5zig.particletext.characters;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import eu.mc5zig.particletext.Main;
import eu.mc5zig.particletext.utils.MathUtils;
import eu.mc5zig.particletext.utils.Sprite;
import eu.mc5zig.particletext.utils.SpriteSheet;

public class CharacterManager {

	private Main plugin;

	private Sprite[] sprites;
	private String characters = "ABCDEFGHIJKLM" //
			+ "NOPQRSTUVWXYZ" //
			+ "abcdefghijklm" //
			+ "nopqrstuvwxyz";

	public CharacterManager(Main plugin) {
		this.plugin = plugin;

		setup();
	}

	private void setup() {
		SpriteSheet sheet = new SpriteSheet("/text.png");
		Sprite[] sprites = sheet.split(16);
		this.sprites = sprites;
	}

	public void draw(String string, Player player) {
		Location loc = player.getLocation();
		loc.setPitch(0.0f);
		float yaw = loc.getYaw();
		Vector vec = loc.getDirection();
		vec.multiply(10);
		loc.add(vec);
		loc.add(0, 8, 0);

		int f = MathUtils.getFByYaw(yaw);
		System.out.println(f);

		int xOff = 0;
		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			int index = characters.indexOf(character);
			if (index == -1) continue;
			Sprite sprite = sprites[index];
			for (int x = 0; x < sprite.getWidth(); x++) {
				for (int y = 0; y < sprite.getHeight(); y++) {
					int col = sprite.pixels[x + y * sprite.getWidth()];
					Location l = new Location(loc.getWorld(), loc.getX() + x + xOff, loc.getY() - y, loc.getZ());
					if (col != -1 && col != 0xffff00ff) l.getBlock().setType(Material.STONE);
				}
			}
			xOff += 20;
		}
	}
}
