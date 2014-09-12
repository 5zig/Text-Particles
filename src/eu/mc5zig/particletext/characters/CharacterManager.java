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
		final int defCharacterOff = 20;
		Location loc = player.getLocation();
		loc.setPitch(0.0f);
		float yaw = loc.getYaw();
		Vector vec = loc.getDirection();
		vec.multiply(20);
		loc.add(vec);
		loc.add(0, 8, 0);

		int f = MathUtils.getFByYaw(yaw);

		int offset = MathUtils.getOffset(string, defCharacterOff);

		int characterOff = -offset / 2;
		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			int index = characters.indexOf(character);
			if (index == -1) continue;
			Sprite sprite = sprites[index];
			for (int x = 0; x < sprite.getWidth(); x++) {
				for (int y = 0; y < sprite.getHeight(); y++) {
					int col = sprite.pixels[x + y * sprite.getWidth()];
					Location l = null;
					switch (f) {
					case 1:
						l = new Location(loc.getWorld(), loc.getX(), loc.getY() - y, loc.getZ() - x - characterOff);
						break;
					case 2:
						l = new Location(loc.getWorld(), loc.getX() + x + characterOff, loc.getY() - y, loc.getZ());
						break;
					case 3:
						l = new Location(loc.getWorld(), loc.getX(), loc.getY() - y, loc.getZ() + x + characterOff);
						break;
					case 4:
						l = new Location(loc.getWorld(), loc.getX() - x - characterOff, loc.getY() - y, loc.getZ());
						break;
					}
					if (col != -1 && col != 0xffff00ff) l.getBlock().setType(Material.STONE);
				}
			}
			characterOff += defCharacterOff;
		}
	}
}
