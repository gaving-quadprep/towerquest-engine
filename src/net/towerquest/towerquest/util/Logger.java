package net.towerquest.towerquest.util;

import java.io.PrintWriter;
import java.io.StringWriter;

//* second worst logger ever 

public class Logger {
	public static enum Level {
		TRACE,
		DEBUG,
		INFO,
		WARN,
		ERROR,
		FATAL
	}
	private final long startTime;
	public boolean showLineNumber = true;
	public Level level = Level.INFO;
	public static final Logger instance = new Logger();
	public Logger() {
		startTime = System.nanoTime();
		log(Level.INFO, "Logger instantiated", 2);
	}
	private void _log(Level l, String s) {
		String[] lines = s.split(System.lineSeparator());
		for (int i = 0; i < lines.length; i++) {
			System.out.print("[" + String.format("%.7f", (double)(System.nanoTime() - startTime) / 1000000000) + "] "
					+ lines[i]);
			if (i + 1 < lines.length)
				System.out.println();
		}
	}
	public void log(Level l, String s) {
		log(l, s, 2);
	}
	public void log(Level l, String s, int depth) {
		if (l.ordinal() < level.ordinal())
			return;
		_log(l, s);
		if(showLineNumber)
			System.out.println(" @ " + String.valueOf(Thread.currentThread().getStackTrace()[depth + 1].toString()));
		else
			System.out.println();
	}
	public void logWithoutLineNumber(Level l, String s) {
		if (l.ordinal() < level.ordinal())
			return;
		_log(l, s);
	}
	
	public void logException(Level l, Throwable e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		printWriter.flush();
		log(level, printWriter.toString(), 2);
	}
	
	public void logException(Level l, String str, Throwable e) {
		log(l, str, 2);
		logException(l, e);
	}
}
