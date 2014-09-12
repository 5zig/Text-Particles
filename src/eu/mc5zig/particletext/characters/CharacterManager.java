package eu.mc5zig.particletext.characters;

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

	public void draw(String string) {
		Sprite sprite = sprites[0];
		for (int i = 0; i < sprite.pixels.length; i++)
			System.out.println(sprite.pixels[i]);
	}

}
