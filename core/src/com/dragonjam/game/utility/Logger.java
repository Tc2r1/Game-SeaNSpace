package com.dragonjam.game.utility;


public class Logger {
	
	
	public enum Severity {
		INFO, WARNING, ERROR
	}
	
	/**
	 * Class that should be used whenever printing
	 * out messages to the console / log. This will
	 * ensure a consistent and easily changeable way
	 * to display information and errors.
	 */
	public Logger() {
		
	}
	
	/**
	 * Method for logging information to the
	 * console.
	 * 
	 * @param cls
	 * the class calling this method. To obtain this,
	 * call <code>this.getClass()</code>
	 * @param methodName
	 * the name of the method in which <code>log()</code>
	 * is being called
	 * @param sev
	 * the Severity of the message
	 * @param msg
	 * the message to be printed
	 */
	public static void log(Class<?> cls, String methodName, Severity sev, String msg) {
		
		System.out.print("[" + cls.getName() + "]");
		System.out.print("[" + methodName + "]");
		
		if(sev.equals(Severity.INFO)) {
			System.out.print("[INFO]");
		} else if(sev.equals(Severity.WARNING)) {
			System.out.print("[WARNING]");
		} else {
			System.out.print("[ERROR]");
		}
		
		System.out.println(msg);
		
	}
	
}
