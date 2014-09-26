package eu.mc5zig.textparticles.utils;

public class MathUtils {

	private MathUtils() {
	}

	public static int getFByYaw(float yaw) {
		float f = (yaw * 4.0f / 360.0f);
		if (f < 0) {
			f = 4 - Math.abs(f);
		}
		return Math.round(f);
	}
	
	public static float getYawByF(int f) {
		return f * 360.0f / 4.0f;
	}

	public static int getOffset(String string, int characterOff) {
		return string.length() * characterOff;
	}

}
