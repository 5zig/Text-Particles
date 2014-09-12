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
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.width = image.getWidth();
			this.height = image.getHeight();
			this.size = width > height ? width : height;
			this.pixels = new int[width * height];
			image.getRGB(0, 0, width, height, pixels, 0, size);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Sprite[] split(int size) {
		Sprite[] res = new Sprite[width * height];

		int index = 0;
		for (int x = 0; x < width; x += size) {
			for (int y = 0; y < height; y += size) {
				int[] pixels = new int[size * size];
				for (int xx = 0; xx < size; xx++) {
					for (int yy = 0; yy < size; yy++) {
						pixels[xx + yy * size] = this.pixels[x * xx + y * yy * width];
					}
				}
				res[index++] = new Sprite(pixels, size, size);
			}
		}
		return res;
	}
}
