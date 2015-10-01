package com.cjburkey.games.WiRED.render;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.obj.Node;
import com.cjburkey.games.WiRED.render.gui.GUIItem;

public final class DrawPane extends JPanel {
	
	private static final long serialVersionUID = 7703193660030888408L;
	
	public DrawPane() {
		this.setPreferredSize(Render.windowSize);
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics gg) {
		
		Graphics2D g = (Graphics2D) gg;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.GRAY);
		for(int x = 0; x < this.getWidth(); x += GameLogic.nodeSize) {
			g.drawLine(x, 0, x, this.getHeight());
		}
		for(int y = 0; y < this.getHeight(); y += GameLogic.nodeSize) {
			g.drawLine(0, y, this.getWidth(), y);
		}
		
		for(int i = 0; i < GameLogic.nodeCount(); i ++) {
			Node n = GameLogic.getNode(i);
			n.render(g);
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), GameLogic.nodeSize);
		g.fillRect(0, GameLogic.nodeSize, GameLogic.nodeSize, this.getHeight());
		g.fillRect(this.getWidth() - GameLogic.nodeSize, GameLogic.nodeSize, GameLogic.nodeSize, this.getHeight());
		g.fillRect(0, this.getHeight() - GameLogic.nodeSize, this.getWidth(), GameLogic.nodeSize);
		
		g.setColor(Color.GRAY);
		g.drawLine(this.getWidth() - GameLogic.nodeSize, GameLogic.nodeSize, this.getWidth() - GameLogic.nodeSize, this.getHeight() - GameLogic.nodeSize);
		g.drawLine(GameLogic.nodeSize, this.getHeight() - GameLogic.nodeSize, this.getWidth() - GameLogic.nodeSize, this.getHeight() - GameLogic.nodeSize);
		
		for(int i = 0; i < Render.gui.size(); i ++) {
			GUIItem item = Render.gui.get(i);
			item.render(g);
		}
		
		this.setCursor(Render.cursorPlace);
		
	}
	
}