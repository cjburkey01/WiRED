package com.cjburkey.games.WiRED.obj;

import java.awt.Color;
import java.awt.Graphics2D;
import com.cjburkey.games.WiRED.GameLogic;

public class CableNode extends Node {
	
	private static final long serialVersionUID = 2373736297740427991L;

	public CableNode(int x, int y) {
		super(x, y);
		this.powerLevel = 0;
		this.maxPower = 255;
		this.connectable = true;
	}
	
	public void tick() {
		int power = 0;
		
		Node node = GameLogic.getNodeAt(this.getX() + 1, this.getY());
		if(node != null && node.powerLevel > power && node.connectable && this.connectable) {
			power = node.powerLevel - 1;
		}
		
		node = GameLogic.getNodeAt(this.getX() - 1, this.getY());
		if(node != null && node.powerLevel > power && node.connectable && this.connectable) {
			power = node.powerLevel - 1;
		}
		
		node = GameLogic.getNodeAt(this.getX(), this.getY() + 1);
		if(node != null && node.powerLevel > power && node.connectable && this.connectable) {
			power = node.powerLevel - 1;
		}
		
		node = GameLogic.getNodeAt(this.getX(), this.getY() - 1);
		if(node != null && node.powerLevel > power && node.connectable && this.connectable) {
			power = node.powerLevel - 1;
		}
		
		this.powerLevel = power;
	}
	
	public void render(Graphics2D g) {
		int x = this.getX() * GameLogic.nodeSize + (GameLogic.nodeSize / 4);
		int y = this.getY() * GameLogic.nodeSize + (GameLogic.nodeSize / 4);
		int w = GameLogic.nodeSize - (GameLogic.nodeSize / 2);
		
		g.setColor(new Color(this.powerLevel, 0, 0));
		g.fillRect(x, y, w, w);
		
		Node n = GameLogic.getNodeAt(this.getX() + 1, this.getY());
		if(n != null && n.connectable && this.connectable) {
			g.fillRect(x + w, y, w / 2 + 1, w);
		}

		n = GameLogic.getNodeAt(this.getX() - 1, this.getY());
		if(n != null && n.connectable && this.connectable) {
			g.fillRect(x - w + w / 2, y, w / 2 + 1, w);
		}

		n = GameLogic.getNodeAt(this.getX(), this.getY() + 1);
		if(n != null && n.connectable && this.connectable) {
			g.fillRect(x, y + w, w, w / 2 + 1);
		}

		n = GameLogic.getNodeAt(this.getX(), this.getY() - 1);
		if(n != null && n.connectable) {
			g.fillRect(x, y - w + w / 2, w, w / 2 + 1);
		}
	}
	
}