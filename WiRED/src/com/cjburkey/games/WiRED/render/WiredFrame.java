package com.cjburkey.games.WiRED.render;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import com.cjburkey.games.WiRED.GameLogic;
import com.cjburkey.games.WiRED.WiRED;

public final class WiredFrame extends JFrame {
	
	private static final long serialVersionUID = 539245206083304442L;
	
	//--//
	
	public WiredFrame() {
		
		this.setTitle("WiRED - The Game - v" + WiRED.version);
		this.setLayout(new BorderLayout());
		this.add(Render.drawPane, BorderLayout.CENTER);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setFocusable(true);
		
		this.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {  }
			public void windowClosing(WindowEvent e) {
				GameLogic.quit();
			}
			public void windowClosed(WindowEvent e) {  }
			public void windowIconified(WindowEvent e) {  }
			public void windowDeiconified(WindowEvent e) {  }
			public void windowActivated(WindowEvent e) {  }
			public void windowDeactivated(WindowEvent e) {  }
		});
		
		Render.drawPane.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {  }
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == 1) {
					GameLogic.leftClick(e.getPoint());
				} else if(e.getButton() == 3) {
					GameLogic.rightClick(e.getPoint());
				}
			}
			public void mouseReleased(MouseEvent e) {  }
			public void mouseEntered(MouseEvent e) {  }
			public void mouseExited(MouseEvent e) {  }
		});
		
		Render.drawPane.requestFocus();
		
	}
	
}