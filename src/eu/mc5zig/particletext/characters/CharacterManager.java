package eu.mc5zig.particletext.characters;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import eu.mc5zig.particletext.utils.MathUtils;
import eu.mc5zig.particletext.utils.Sprite;
import eu.mc5zig.particletext.utils.SpriteSheet;

public class CharacterManager {

	private Sprite[] sprites;
	private String characters = "ABCDEFGHIJKLM" //
			+ "NOPQRSTUVWXYZ" //
			+ "abcdefghijklm" //
			+ "nopqrstuvwxyz" //
			+ "0123456789!\"§" //
			+ "$%&/()[]{}=`´" //
			+ "?ÜüÄäÖö@€\\#+" //
			+ "*~'<>|-_.,;:^";

	public CharacterManager() {
		setup();
	}

	private void setup() {
		SpriteSheet sheet = new SpriteSheet("/text.png");
		Sprite[] sprites = sheet.split(16);
		this.sprites = sprites;
	}

	public void draw(String string, Player player, int distance) {
		final int defCharacterOff = 2;
		Location loc = player.getLocation();
		loc.setPitch(0.0f);
		Vector vec = loc.getDirection();
		vec.multiply(distance);
		loc.add(vec);
		loc.add(0, 1, 0);

		int offset = MathUtils.getOffset(string, defCharacterOff);

		int characterOff = -offset / 2;
		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			int index = characters.indexOf(character);
			if (index == -1) {
				characterOff += defCharacterOff;
				continue;
			}
			Sprite sprite = sprites[index];
			for (int x = 0; x < sprite.getWidth(); x++) {
				for (int y = 0; y < sprite.getHeight(); y++) {
					int col = sprite.pixels[x + y * sprite.getWidth()];
					Location l = null;
					float yaw = loc.getYaw();
					int f = MathUtils.getFByYaw(yaw);

					double xl = loc.getX();
					double yl = loc.getY() - (double) y / 10.0;
					double zl = loc.getZ();

					double xp;
					double zp;
					switch (f) {
					case 1:
						zp = (double) x / 10.0 + characterOff;
						l = new Location(loc.getWorld(), xl, yl, zl - zp);
						break;
					case 2:
						xp = (double) x / 10.0 + characterOff;
						l = new Location(loc.getWorld(), xl + xp, yl, zl);
						break;
					case 3:
						zp = (double) x / 10.0 + characterOff;
						l = new Location(loc.getWorld(), xl, yl, zl + zp);
						break;
					case 4:
						xp = (double) x / 10.0 + characterOff;
						l = new Location(loc.getWorld(), xl - xp, yl, zl);
						break;
					case 0:
						xp = (double) x / 10.0 + characterOff;
						l = new Location(loc.getWorld(), xl - xp, yl, zl);
						break;
					}
					if (col != -1 && col != 0xffff00ff) {
						l.getWorld().playEffect(l, Effect.HEART, 0);
					}
				}
			}
			characterOff += defCharacterOff;
		}
	}
}
