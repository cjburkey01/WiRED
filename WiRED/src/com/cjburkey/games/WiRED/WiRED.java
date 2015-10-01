package com.cjburkey.games.WiRED;

import java.awt.Toolkit;

public final class WiRED {
	
	public static final Toolkit tools = Toolkit.getDefaultToolkit();
	public static final String version = "pa0.1";
	
	public static final GameLoop loop = new GameLoop();
	public static final Thread thread = new Thread(loop);
	
	public static void main(String[] args) {
		
		Handler handler = new Handler();
		Thread.setDefaultUncaughtExceptionHandler(handler);
		
		try {
			thread.start();
		} catch(Exception e) {
			GameLogic.error(e);
		}
		
	}
	
	static final class Handler implements Thread.UncaughtExceptionHandler {
		public final void uncaughtException(Thread t, Throwable e) {
			GameLogic.error(e);
		}
	}
	
}