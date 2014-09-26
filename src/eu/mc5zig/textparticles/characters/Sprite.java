package eu.mc5zig.textparticles.characters;

public class Sprite {

	public int[] pixels;
	private int width, height;

	public Sprite(int[] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getRealWidth() {
		int c = 0;
		for (int x = 1; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int col = pixels[x + y * width];
				if (col == -1 || col == 0xffff00ff) c++;
			}
			if (c == height) return x;
			c = 0;
		}
		return width;
	}

}
