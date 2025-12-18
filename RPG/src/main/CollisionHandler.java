package main;

import java.awt.Rectangle;

import entity.Player;
import tile.TileManager;

public class CollisionHandler {	
	public Player p;
	public GamePanel gp;
	public TileManager tm;
	
	public CollisionHandler(GamePanel gp, Player p, TileManager tm) {
		this.gp = gp;
		this.p = p;
		this.tm = tm;
	}
	
    public void playerCollision(String dir, Rectangle hb) { // FIXED STICKY EDGE BY CHANCE 🙏🏻
    	int worldLeftX = hb.x + p.worldX;
    	int worldRightX = hb.x + hb.width + p.worldX;
    	int worldTop = hb.y + p.worldY;
    	int worldBottom = hb.y + hb.height + p.worldY;
    	int worldMidX = (worldLeftX + worldRightX) / 2;
    	int worldMidY = (worldTop + worldBottom) / 2;
    	
    	int nicl = worldLeftX / gp.tileSize;
    	int nicr = worldRightX / gp.tileSize;
    	int niwt = worldTop / gp.tileSize;
    	int niwb = worldBottom / gp.tileSize;
    	int nixm = worldMidX / gp.tileSize;
    	int niym = worldMidY / gp.tileSize;
    	
    	
    	
    	if(tm.tile[tm.mapTileNum[nicl][niwt]].collision == true) 
			p.collisionOn[0][0] = true;
			
		if(tm.tile[tm.mapTileNum[nixm][niwt]].collision == true)
			p.collisionOn[0][1] = true;
		
		if(tm.tile[tm.mapTileNum[nicr][niwt]].collision == true)
			p.collisionOn[0][2] = true;
		
		if(tm.tile[tm.mapTileNum[nicl][niym]].collision == true)
			p.collisionOn[1][0] = true;
		
		if(tm.tile[tm.mapTileNum[nicr][niym]].collision == true)
			p.collisionOn[1][2] = true;
		
		if(tm.tile[tm.mapTileNum[nicl][niwb]].collision == true)
			p.collisionOn[2][0] = true;
		
		if(tm.tile[tm.mapTileNum[nixm][niwb]].collision == true)
			p.collisionOn[2][1] = true;
		
		if(tm.tile[tm.mapTileNum[nicr][niwb]].collision == true)
			p.collisionOn[2][2] = true;
		
		
    }
}
