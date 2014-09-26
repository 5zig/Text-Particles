package eu.mc5zig.textparticle.characters;

import java.io.File;

import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import eu.mc5zig.textparticle.utils.FileUtils;
import eu.mc5zig.textparticle.utils.MathUtils;
import eu.mc5zig.textparticles.TextParticles;

public class CharacterManager {

	private TextParticles plugin;
	private File spriteSheet;
	private Sprite[] sprites;
	private String characters = "ABCDEFGHIJKLM" //
			+ "NOPQRSTUVWXYZ" //
			+ "abcdefghijklm" //
			+ "nopqrstuvwxyz" //
			+ "0123456789!\"§" //
			+ "$%&/()[]{}=`´" //
			+ "?ÜüÄäÖö@€\\#+*" //
			+ "~'<>|-_.,;:^°";

	public CharacterManager(TextParticles plugin) {
		this.plugin = plugin;
		setup();
	}

	private void setup() {
		if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdir();
		spriteSheet = new File(plugin.getDataFolder(), "text.png");
		if (!spriteSheet.exists()) {
			TextParticles.logger().info("SpriteSheet not found! Creating new one!");
			if (!FileUtils.copyFile("/text.png", spriteSheet)) {
				TextParticles.logger().servere("Could not create new SpriteSheet!");
				TextParticles.logger().warn("Disabling plugin!");
				plugin.getPluginLoader().disablePlugin(plugin);
			} else {
				TextParticles.logger().info("Created new SpriteSheet!");
			}
		}
		SpriteSheet sheet = new SpriteSheet(spriteSheet);
		Sprite[] sprites = sheet.split(16);
		this.sprites = sprites;
	}

	public void draw(String message, Player player) {
		draw(message, player, 8);
	}

	public void draw(String message, Player... players) {
		for (Player player : players) draw(message, player);
	}
	
	public void draw(String message, Player player, int distance) {
		if (player == null) {
			throw new IllegalArgumentException("Player cannot be null!");
		}
		if (message == null || message.isEmpty()) {
			throw new IllegalArgumentException("Message can't be empty or null!");
		}
		if (characters == null) {
			throw new RuntimeException("Could not draw Text onto screen!");
		}
		int defCharacterOff = 2;
		double scale = 0.08;
		double charOff = 0.0;
		for (int i = 0; i < message.length(); i++) {
			char character = message.charAt(i);
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
		loc.add(0, scale * 16 + 0.2, 0);

		double characterOff = -charOff / 2;
		for (int i = 0; i < message.length(); i++) {
			char character = message.charAt(i);
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
