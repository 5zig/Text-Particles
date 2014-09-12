package eu.mc5zig.particletext.characters;

import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
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
			+ "?ÜüÄäÖö@€\\#+*" //
			+ "~'<>|-_.,;:^°";

	public CharacterManager() {
		setup();
	}

	private void setup() {
		SpriteSheet sheet = new SpriteSheet("/text.png");
		Sprite[] sprites = sheet.split(16);
		this.sprites = sprites;
	}

	public void draw(String string, Player player, int distance) {
		int defCharacterOff = 2;
		double scale = 0.08;
		double charOff = 0.0;
		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			int index = characters.indexOf(character);
			if (index == -1) {
				charOff += defCharacterOff * 2 * scale;
				continue;
			}
			Sprite sprite = sprites[index];
			charOff += (double) sprite.getRealWidth() * scale + 2 * scale;
		}

		Location loc = player.getLocation();
		loc.setPitch(0.0f);
		Vector vec = loc.getDirection();
		vec.multiply(distance);
		loc.add(vec);
		loc.add(0, scale * 16 + 0.5, 0);

		double characterOff = -charOff / 2;
		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			int index = characters.indexOf(character);
			if (index == -1) {
				characterOff += defCharacterOff * 2 * scale;
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
					double yl = loc.getY() - (double) y * scale;
					double zl = loc.getZ();

					double xp;
					double zp;
					switch (f) {
					case 1:
						zp = (double) x * scale + characterOff;
						l = new Location(loc.getWorld(), xl, yl, zl - zp);
						break;
					case 2:
						xp = (double) x * scale + characterOff;
						l = new Location(loc.getWorld(), xl + xp, yl, zl);
						break;
					case 3:
						zp = (double) x * scale + characterOff;
						l = new Location(loc.getWorld(), xl, yl, zl + zp);
						break;
					case 4:
						xp = (double) x * scale + characterOff;
						l = new Location(loc.getWorld(), xl - xp, yl, zl);
						break;
					case 0:
						xp = (double) x * scale + characterOff;
						l = new Location(loc.getWorld(), xl - xp, yl, zl);
						break;
					}
					if (col != -1 && col != 0xffff00ff) {
						((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutWorldParticles("fireworksSpark", (float) l.getX(), (float) l.getY(),
								(float) l.getZ(), 0.0f, 0.0f, 0.0f, 0.0f, 0));
					}
				}
			}
			characterOff += (double) sprite.getRealWidth() * scale + 2 * scale;
		}
	}
}
