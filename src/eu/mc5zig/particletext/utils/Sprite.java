package eu.mc5zig.particletext.utils;

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

}
