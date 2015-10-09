package com.cjburkey.games.WiRED.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.WiRED;
import com.cjburkey.games.WiRED.obj.Node;

public class LevelLoader {
	
	@SuppressWarnings("unchecked")
	public static final void loadLevel(String name) {
		try {
			File f = new File(WiRED.levelsLoc + name + ".lvl");
			if(f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				ArrayList<Node> nodes = (ArrayList<Node>) ois.readObject();
				ois.close();
				
				GameLogic.reset();
				for(int i = 0; i < nodes.size(); i ++) {
					Node n = nodes.get(i);
					GameLogic.addNode(n);
				}
			} else {
				GameLogic.log("File doesn't exist, can't read it.");
			}
		} catch(Exception e) {
			GameLogic.error(e);
		}
	}
	
	public static final void saveLevel(String name) {
		try {
			File f = new File(WiRED.levelsLoc + name + ".lvl");
			if(f.exists()) {
				GameLogic.log("File exists, not writting.");
			} else {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(GameLogic.getArray());
				oos.close();
			}
		} catch(Exception e) {
			GameLogic.error(e);
		}
	}
	
}