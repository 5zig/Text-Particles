package eu.mc5zig.textparticles.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TPLogger {

	private Logger logger;

	public TPLogger(Logger logger) {
		this.logger = logger;
		this.logger.setLevel(Level.ALL);
	}

	public void log(Level level, String message) {
		logger.log(level, message);
	}

	public void fine(String message) {
		log(Level.FINE, message);
	}

	public void info(String message) {
		log(Level.INFO, message);
	}

	public void warn(String message) {
		log(Level.WARNING, message);
	}

	public void servere(String message) {
		log(Level.SEVERE, message);
	}

}
