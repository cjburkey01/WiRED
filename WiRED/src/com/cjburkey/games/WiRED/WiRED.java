package com.cjburkey.games.WiRED;

import java.awt.Toolkit;
import java.io.File;

public final class WiRED {
	
	public static final Toolkit tools = Toolkit.getDefaultToolkit();
	public static final String version = "pa0.4";
	
	public static final GameLoop loop = new GameLoop();
	public static final Thread thread = new Thread(loop);
	
	public static final String fileSep = System.getProperty("file.separator");
	public static final String userHome = System.getProperty("user.home");
	
	public static final String gameLoc = userHome + fileSep + "WiRED" + fileSep;
	public static final String modsLoc = gameLoc + "mods" + fileSep;
	
	public static void main(String[] args) {
		
		Handler handler = new Handler();
		Thread.setDefaultUncaughtExceptionHandler(handler);
		
		new File(gameLoc).mkdirs();
		new File(modsLoc).mkdirs();
		
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