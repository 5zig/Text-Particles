package eu.mc5zig.particletext.characters;

public abstract class Character {

	protected int[] matrix;

	public Character() {
		matrix = new int[16 * 16];
	}

	protected abstract int[] getArray();

}
