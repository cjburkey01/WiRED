package com.cjburkey.games.WiRED.gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.mod.GameMod;
import com.cjburkey.games.WiRED.mod.ModManager;
import com.cjburkey.games.WiRED.obj.CableNode;
import com.cjburkey.games.WiRED.obj.Node;
import com.cjburkey.games.WiRED.render.Render;
import com.cjburkey.games.WiRED.render.gui.GUIItem;

public class GameStateLevel extends GameState {
	
	public void tick() {
		for(int i = 0; i < GameLogic.nodeCount(); i ++) {
			Node n = GameLogic.getNode(i);
			if(n != null) {
				n.tick();
			}
		}
		
		for(int i = 0; i < ModManager.modCount(); i ++) {
			GameMod mod = ModManager.getMod(i);
			mod.tick();
		}
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Render.drawPane.getWidth(), Render.drawPane.getHeight());
		
		g.setColor(Color.GRAY);
		for(int x = 0; x < Render.drawPane.getWidth(); x += GameLogic.nodeSize) {
			g.drawLine(x, 0, x, Render.drawPane.getHeight());
		}
		for(int y = 0; y < Render.drawPane.getHeight(); y += GameLogic.nodeSize) {
			g.drawLine(0, y, Render.drawPane.getWidth(), y);
		}
		
		for(int i = 0; i < GameLogic.nodeCount(); i ++) {
			Node n = GameLogic.getNode(i);
			n.render(g);
		}
		
		for(int i = 0; i < ModManager.modCount(); i ++) {
			GameMod mod = ModManager.getMod(i);
			mod.render(g);
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Render.drawPane.getWidth(), GameLogic.nodeSize);
		g.fillRect(0, GameLogic.nodeSize, GameLogic.nodeSize, Render.drawPane.getHeight());
		g.fillRect(Render.drawPane.getWidth() - GameLogic.nodeSize, GameLogic.nodeSize, GameLogic.nodeSize, Render.drawPane.getHeight());
		g.fillRect(0, Render.drawPane.getHeight() - GameLogic.nodeSize, Render.drawPane.getWidth(), GameLogic.nodeSize);
		
		g.setColor(Color.GRAY);
		g.drawLine(Render.drawPane.getWidth() - GameLogic.nodeSize, GameLogic.nodeSize, Render.drawPane.getWidth() - GameLogic.nodeSize, Render.drawPane.getHeight() - GameLogic.nodeSize);
		g.drawLine(GameLogic.nodeSize, Render.drawPane.getHeight() - GameLogic.nodeSize, Render.drawPane.getWidth() - GameLogic.nodeSize, Render.drawPane.getHeight() - GameLogic.nodeSize);
		
		for(int i = 0; i < Render.gui.size(); i ++) {
			GUIItem item = Render.gui.get(i);
			item.render(g);
		}
		
		Render.drawPane.setCursor(Render.cursorPlace);
	}
	
	public void leftClick(Point pos) {
		int x = (int) (pos.getX() / GameLogic.nodeSize);
		int y = (int) (pos.getY() / GameLogic.nodeSize);
		int width = (int) (Render.windowSize.getWidth() / GameLogic.nodeSize);
		int height = (int) (Render.windowSize.getHeight() / GameLogic.nodeSize);
		if(pos != null) {
			if(!GameLogic.isNodeAt(x, y) && y > 0 && x > 0 && y < height - 1 && x < width - 1) {
				GameLogic.addNode(new CableNode(x, y));
			} else if(y == 0) {
				if(Render.getItemFromPoint(pos) != null) {
					Render.getItemFromPoint(pos).click();
				}
			}
		}
	}
	
	public void rightClick(Point pos) {
		int x = (int) (pos.getX() / GameLogic.nodeSize);
		int y = (int) (pos.getY() / GameLogic.nodeSize);
		if(pos != null) {
			if(GameLogic.isNodeAt(x, y) && y > 0 && GameLogic.getNodeAt((int) x, y).deleteable) {
				GameLogic.removeNode(GameLogic.getNodeAt((int) x, y));
			}
		}
	}
	
}