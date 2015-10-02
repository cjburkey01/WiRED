package com.cjburkey.games.WiRED.mod;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.swing.JOptionPane;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.WiRED;
import com.cjburkey.games.WiRED.render.Render;

public class ModManager {
	
	private static final ArrayList<GameMod> mods = new ArrayList<GameMod>();
	
	public static final void reloadMods() throws Exception {
		mods.clear();
		File modsFolder = new File(WiRED.modsLoc);
		File[] folder = modsFolder.listFiles();
		
		for(int i = 0; i < folder.length; i ++) {
			File f = folder[i];
			
			if(!f.isDirectory() && f.getName().endsWith(".jar")) {
				JarFile jarFile = new JarFile(f);
				Enumeration<JarEntry> e = jarFile.entries();
				
				URL[] urls = { new URL("jar:file:" + f + "!/") };
				URLClassLoader loader = URLClassLoader.newInstance(urls);
				
				while(e.hasMoreElements()) {
					JarEntry je = e.nextElement();
					if(je.isDirectory() || !je.getName().endsWith(".class")) {
						continue;
					}
					
					String className = je.getName().substring(0, je.getName().length() - 6);
					className = className.replace('/', '.');
					Class<?> c = loader.loadClass(className);
					
					try {
						Object o = c.newInstance();
						
						try {
							Method run = c.getMethod("create");
							try {
								run.invoke(o);
							} catch(Exception err) {
								GameLogic.error(err);
								err.printStackTrace();
							}
						} catch(Exception err) {
							System.out.println("Skipping...");
						}
					} catch(Exception err) {
						GameLogic.log("Couldn't load a class.  Assuming it was a support class");
					}
				}
				
				jarFile.close();
			}
		}
		
		if(mods.size() > 0) {
			GameLogic.log("Running with " + mods.size() + " mods.");
			int val = JOptionPane.showConfirmDialog(Render.wiredFrame, "Mods can harm your computer\n\nCJ Burkey is not responsible for any damage caused by the usage of mods.\n\nDo you still want to run the game?", "Mods", JOptionPane.YES_NO_OPTION);
			if(val == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		} else {
			GameLogic.log("No mods found.");
		}
	}
	
	public static final void addMod(GameMod mod) {
		mods.add(mod);
	}
	
	public static final int modCount() {
		return mods.size();
	}
	
	public static final GameMod getMod(int i) {
		return mods.get(i);
	}
	
}