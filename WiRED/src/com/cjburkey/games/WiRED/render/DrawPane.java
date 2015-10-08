package com.cjburkey.games.WiRED.render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import com.cjburkey.games.WiRED.GameLogic;

public final class DrawPane extends JPanel {
	
	private static final long serialVersionUID = 7703193660030888408L;
	
	public DrawPane() {
		this.setPreferredSize(Render.windowSize);
		this.setFocusable(true);
	}
	
	public void paintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		GameLogic.state.render(g);
	}
	
}