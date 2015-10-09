package com.cjburkey.games.WiRED.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.render.Render;
import com.cjburkey.games.WiRED.render.gui.GUIButton;
import com.cjburkey.games.WiRED.render.gui.GUIItem;
import com.cjburkey.games.WiRED.render.gui.GUILabel;

public class GameStateMenu extends GameState {
	
	public final ArrayList<GUIItem> menuItems = new ArrayList<GUIItem>();
	
	public GameStateMenu() {
		GUIButton playButton = new GUIButton(0, 0, 256, 256, Render.loadImg("/img/play.png")) {
			public void click() {
				GameLogic.state = new GameStateLevel();
				GameLogic.log("Click");
			}
		};
		
		GUIButton loadButton = new GUIButton(0, 256, 256, 256, Render.loadImg("/img/load.png")) {
			public void click() {
				
			}
		};
		GUILabel label = new GUILabel("TEMPORARY MENU LOL", Color.BLACK, 256, 128);
		
		menuItems.add(playButton);
		menuItems.add(loadButton);
		menuItems.add(label);
	}
	
	public void tick() {  }
	
	public void render(Graphics2D g) {
		for(int i = 0; i < menuItems.size(); i ++) {
			GUIItem it = menuItems.get(i);
			it.render(g);
		}
	}
	
	public void leftClick(Point pos) {
		if(pos != null && this.getMenuItemFromPoint(pos) != null) {
			this.getMenuItemFromPoint(pos).click();
		}
	}
	
	public final GUIItem getMenuItemFromPoint(Point p) {
		for(int i = 0; i < menuItems.size(); i ++) {
			GUIItem it = menuItems.get(i);
			Rectangle r = new Rectangle(it.x, it.y, it.width, it.height);
			if(r.contains(p)) {
				return it;
			}
		}
		
		return null;
	}
	
}