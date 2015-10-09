package com.cjburkey.games.WiRED.obj;

import java.awt.Color;
import java.awt.Graphics2D;
import com.cjburkey.games.WiRED.GameLogic;

public class PowerBlock extends Node {
	
	private static final long serialVersionUID = -6141911278645028968L;

	public PowerBlock(int x, int y) {
		super(x, y);
		this.powerLevel = 255;
		this.maxPower = 255;
		this.deleteable = false;
	}
	
	public void tick() {  }
	
	public void render(Graphics2D g) {
		
		int x = this.getX() * GameLogic.nodeSize;
		int y = this.getY() * GameLogic.nodeSize;
		int w = GameLogic.nodeSize;
		
		g.setColor(new Color(powerLevel, 0, 0));
		g.fillRect(x, y, w, w);
		
	}
	
}