package net.towerquest.util;

//* worst logger ever

public class Logger {
	private final long startTime;
	private boolean showLineNumber = true;
	public static final Logger instance = new Logger();
	public Logger() {
		startTime = System.nanoTime();
		log("Logger instantiated", 2);
	}
	public void log(String s) {
		log(s, 2);
	}
	public void log(String s, int depth) {
		System.out.print("[" + String.format("%.7f", (double)(System.nanoTime() - startTime) / 1000000000) + "] " + s);
		if(showLineNumber)
			System.out.println(" @ " + String.valueOf(Thread.currentThread().getStackTrace()[depth + 1].toString()));
		else
			System.out.println();
	}
	/*public static void staticLog(String s) {
		instance.log(s, 2);
	}*/
}
