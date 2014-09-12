package eu.mc5zig.particletext.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	private int[] pixels;
	private int width, height;
	private int size;

	public SpriteSheet(String path) {
		this.path = path;
		load();
	}

	private void load() {
		System.out.println("Loading SpriteSheet in " + path);
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.size = width > height ? width : height;
			this.pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, size);
			System.out.println("Loaded SpriteSheet in " + path);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Could not load SpriteSheet in " + path);
		}
	}

	public Sprite[] split(int size) {
		Sprite[] res = new Sprite[(width * height) / (size * size)];

		int index = 0;
		for (int y = 0; y < height; y += size) {
			for (int x = 0; x < width; x += size) {
				int[] pixels = new int[size * size];
				for (int yy = 0; yy < size; yy++) {
					int yo = y + yy;
					for (int xx = 0; xx < size; xx++) {
						int xo = x + xx;
						pixels[xx + yy * size] = this.pixels[xo + yo * width];
					}
				}
				res[index++] = new Sprite(pixels, size, size);
			}
		}
		return res;
	}
}
