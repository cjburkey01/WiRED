package com.cjburkey.games.WiRED.render.gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GUIButton extends GUIItem {
	
	public Font f = new Font("Arial", Font.PLAIN, 13);
	public BufferedImage img;
	
	public GUIButton(int x, int y, int width, int height, BufferedImage img) {
		super(x, y);
		this.width = width;
		this.height = height;
		this.img = img;
	}
	
	public void render(Graphics2D g) {
		g.setFont(f);
		g.drawImage(img, x, y, width, height, null);
	}
	
}