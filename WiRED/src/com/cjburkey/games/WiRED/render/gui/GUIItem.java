package com.cjburkey.games.WiRED.render.gui;

import java.awt.Graphics2D;

public class GUIItem {
	
	public int x, y, width, height;
	public String text;
	
	public GUIItem(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
	}
	
	public void hover() {
		
	}
	
	public void click() {
		
	}
	
	public void render(Graphics2D g) {  }
	
}