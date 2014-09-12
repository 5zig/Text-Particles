package eu.mc5zig.particletext.characters;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import eu.mc5zig.particletext.Main;
import eu.mc5zig.particletext.utils.Sprite;
import eu.mc5zig.particletext.utils.SpriteSheet;

public class CharacterManager {

	private Main plugin;

	private Sprite[] sprites;
	private String characters = "ABCDEFGHIKLMN" //
			+ "OPQRSTUVWXYZ" //
			+ "abcdefghijklmn" //
			+ "opqrstuvwxyz";

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
		Sprite sprite = sprites[0];
		Location loc = player.getLocation().add(-2, 1, 0);
		for (int x = 0; x < sprite.getWidth(); x++) {
			for (int y = 0; y < sprite.getHeight(); y++) {
				int col = sprite.pixels[x + y * sprite.getWidth()];
				Location l = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y, loc.getZ());
				if (col != -1) l.getBlock().setType(Material.STONE);
			}
		}
	}
}
