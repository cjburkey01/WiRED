package com.cjburkey.games.WiRED.render;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.WiRED;
import com.cjburkey.games.WiRED.gamestate.GameStateMenu;
import com.cjburkey.games.WiRED.render.gui.GUIButton;
import com.cjburkey.games.WiRED.render.gui.GUIItem;

public final class Render {

	public static final Dimension screenSize = WiRED.tools.getScreenSize();
	public static final Dimension windowSize = new Dimension(GameLogic.round(screenSize.getWidth() - 50), GameLogic.round(screenSize.getHeight() - 100));

	public static final ArrayList<GUIItem> gui = new ArrayList<GUIItem>();
	
	public static final DrawPane drawPane = new DrawPane();
	public static final WiredFrame wiredFrame = new WiredFrame();
	
	public static final Point mousePos = new Point(0, 0);
	
	public static final Cursor cursorDelete = createCursor("delete");
	public static final Cursor cursorNo = createCursor("no");
	public static final Cursor cursorPlace = createCursor("place");
	
	public static final GUIButton guiResetButton = new GUIButton(2, 2, GameLogic.nodeSize - 4, GameLogic.nodeSize - 4, loadImg("/img/reset.png")) {
		public void click() {
			GameLogic.reset();
		}
	};
	
	public static final GUIButton guiHomeButton = new GUIButton(2, 2, GameLogic.nodeSize - 4, GameLogic.nodeSize - 4, loadImg("/img/menu.png")) {
		public void click() {
			GameLogic.state = new GameStateMenu();
		}
	};
	
	public static final Cursor createCursor(String name) {
		return WiRED.tools.createCustomCursor(loadImg("/img/cur/" + name + ".png"), new Point(0, 0), name);
	}
	
	public static final void addToolBarButton(GUIButton button) {
		int x, width = GameLogic.nodeSize - 4;
		if(gui.size() >= 1) {
			x = gui.get(gui.size() - 1).x + width + 4;
		} else {
			x = 2;
		}
		
		button.x = x;
		button.y = 2;
		button.width = width;
		button.height = width;
		
		gui.add(button);
	}
	
	public static final BufferedImage loadImg(String path) {
		try {
			return ImageIO.read(Render.class.getResourceAsStream(path));
		} catch(Exception e) {
			GameLogic.error(e);
		}
		
		return null;
	}
	
	public static final GUIItem getItemFromPoint(Point p) {
		for(int i = 0; i < gui.size(); i ++) {
			GUIItem it = gui.get(i);
			Rectangle r = new Rectangle(it.x, it.y, it.width, it.height);
			if(r.contains(p)) {
				return it;
			}
		}
		
		return null;
	}
	
}