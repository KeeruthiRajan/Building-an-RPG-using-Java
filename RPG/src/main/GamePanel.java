package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	final int originalTileSize = 16;
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;
	public final int maxWindowCol = 16;
	public final int maxWindowRow = 12;
	public final int maxWorldCol = 30;
	public final int maxWorldRow = 30;
	public final int screenWidth = maxWindowCol * tileSize;
	public final int screenHeight = maxWindowRow * tileSize;
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);

	int FPS = 60;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public Player player = new Player(this, keyH, tileM);
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double delta = 0;
		double drawInterval = 1000000000/FPS;
		long lastTime = System.nanoTime();
		long currTime;
		while(gameThread != null) {
			currTime = System.nanoTime();
			delta += (currTime - lastTime) / drawInterval;
			lastTime = currTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}
	
	public void update(){
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		player.draw(g2);
		g2.dispose();
	}
}
