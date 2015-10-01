package com.cjburkey.games.WiRED;

import com.cjburkey.games.WiRED.obj.PowerBlock;
import com.cjburkey.games.WiRED.render.Render;

public final class GameLoop implements Runnable {
	
	public static final int fps = 500;
	
	public void run() {
		
		try {
			
			GameLogic.running = true;
			
			GameLogic.addNode(new PowerBlock(2, 2));
			Render.gui.add(Render.guiResetButton);
			
			while(GameLogic.running) {
				
				GameLogic.tick();
				GameLogic.render();
				
				try {
					Thread.sleep(1);
				} catch(Exception e) {
					GameLogic.error(e);
				}
				
			}
			
			System.exit(0);
			
		} catch(Exception e) {
			GameLogic.error(e);
		}
		
	}
	
}