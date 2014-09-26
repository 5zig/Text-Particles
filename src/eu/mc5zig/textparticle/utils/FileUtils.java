package eu.mc5zig.textparticle.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

	private FileUtils() {
	}

	public static boolean copyFile(String source, File destination) {
		InputStream in = null;
		OutputStream out = null;
		try {
			destination.createNewFile();
			in = FileUtils.class.getResourceAsStream(source);
			out = new FileOutputStream(destination);
			byte[] buffer = new byte[8192];

			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (out != null) try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
