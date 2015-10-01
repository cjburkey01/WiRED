package com.cjburkey.games.WiRED.render.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GUILabel extends GUIItem {
	
	public Color c;
	public Font f = new Font("Arial", Font.PLAIN, 13);
	
	public GUILabel(String text, Color c, int x, int y) {
		super(x, y);
		this.c = c;
		this.text = text;
	}
	
	public void render(Graphics2D g) {
		g.setFont(f);
		g.setColor(c);
		g.drawString(text, x, y);
	}
	
}