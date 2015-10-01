package com.cjburkey.games.WiRED;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.cjburkey.games.WiRED.obj.CableNode;
import com.cjburkey.games.WiRED.obj.Node;
import com.cjburkey.games.WiRED.render.Render;

public final class GameLogic {
	
	private static final ArrayList<Node> nodes = new ArrayList<Node>();
	public static boolean running = false;
	public static final int nodeSize = 25;
	
	public static final void tick() {
		
		for(int i = 0; i < nodeCount(); i ++) {
			Node n = getNode(i);
			if(n != null) {
				n.tick();
			}
		}
		
	}
	
	public static final void render() {
		
		Render.drawPane.repaint();
		
	}
	
	public static final void addNode(Node n) {
		nodes.add(n);
	}
	
	public static final void removeNode(Node n) {
		nodes.remove(n);
	}
	
	public static final Node getNode(int i) {
		return nodes.get(i);
	}
	
	public static final int nodeCount() {
		return nodes.size();
	}
	
	public static final Node getNodeAt(int x, int y) {
		for(int i = 0; i < nodeCount(); i ++) {
			Node n = getNode(i);
			if(n != null) {
				if(n.getX() == x && n.getY() == y) {
					return n;
				}
			}
		}
		
		return null;
	}
	
	public static final boolean isNodeAt(int x, int y) {
		if(getNodeAt(x, y) != null) {
			return true;
		}
		
		return false;
	}
	
	public static final void error(Throwable e) {
		JOptionPane.showMessageDialog(Render.wiredFrame, "An error ocurred!\n\n'" + e.getMessage() + "'\n\nCheck console for error\n\nContact CJ Burkey if the problem persists.", "Error Code " + e.hashCode(), JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
		try {
			Thread.sleep(456);
		} catch(Exception err) {  }
		System.exit(e.hashCode());
	}
	
	public static final void quit() {
		nodes.clear();
		running = false;
	}
	
	public static final void log(Object obj) {
		System.out.println("[WiRED-LOG]" + obj);
	}
	
	public static final void leftClick(Point pos) {
		int x = (int) (pos.getX() / nodeSize);
		int y = (int) (pos.getY() / nodeSize);
		int width = (int) (Render.windowSize.getWidth() / nodeSize);
		int height = (int) (Render.windowSize.getHeight() / nodeSize);
		if(pos != null) {
			if(!isNodeAt(x, y) && y > 0 && x > 0 && y < height - 1 && x < width - 1) {
				addNode(new CableNode(x, y));
				log("Placed node at " + x + ", " + y);
			} else if(y == 0) {
				if(Render.getItemFromPoint(pos) != null) {
					Render.getItemFromPoint(pos).click();
				}
			}
		}
	}
	
	public static final void rightClick(Point pos) {
		int x = (int) (pos.getX() / nodeSize);
		int y = (int) (pos.getY() / nodeSize);
		if(pos != null) {
			if(isNodeAt(x, y) && y > 0 && getNodeAt((int) x, y).deleteable) {
				removeNode(getNodeAt((int) x, y));
				log("Removed node at " + x + ", " + y);
			}
		}
	}
	
	public static final void reset() {
		nodes.clear();
	}
	
	public static final int round(double num) {
		return (int) Math.floor(num / nodeSize) * nodeSize;
	}
	
}