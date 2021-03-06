package com.cjburkey.games.WiRED.obj;

import java.awt.Graphics2D;
import java.io.Serializable;

public class Node implements Serializable {
	
	private static final long serialVersionUID = -4260696381927419865L;
	
	private int x, y;
	private String coreName = this.getClass().getName();
	
	public int powerLevel = 0;
	public int maxPower = 255;
	
	public boolean deleteable = true;
	public boolean connectable = true;
	
	protected Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {  }
	public void render(Graphics2D g) {  }
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getCoreName() {
		return this.coreName;
	}
	
}