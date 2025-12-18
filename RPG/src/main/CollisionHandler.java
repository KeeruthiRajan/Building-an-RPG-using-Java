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
	
    public void playerCollision(String dir, Rectangle hb) { // current status: sticky edge problem eliminated for 4 cardinal directions, haven't resolved for diagonal movement.
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
    	
    	
    	switch(dir) {
    	case "up":
    		niwt -= p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicl][niwt]].collision == true) 
    			p.collisionOn[0] = true;
    			
    		if(tm.tile[tm.mapTileNum[nixm][niwt]].collision == true)
    			p.collisionOn[1] = true;
    		
    		if(tm.tile[tm.mapTileNum[nicr][niwt]].collision == true)
    			p.collisionOn[2] = true;
    		
    		break;
    		
    	case "down":
    		niwb += p.speed/gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicl][niwb]].collision == true) 
    			p.collisionOn[5] = true;
    			
    		if(tm.tile[tm.mapTileNum[nixm][niwb]].collision == true)
    			p.collisionOn[6] = true;
    		
    		if(tm.tile[tm.mapTileNum[nicr][niwb]].collision == true)
    			p.collisionOn[7] = true;
    		break;
    		
    	case "left":
    		nicl -= p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicl][niwt]].collision == true) 
    			p.collisionOn[0] = true;
    			
    		if(tm.tile[tm.mapTileNum[nicl][niym]].collision == true)
    			p.collisionOn[3] = true;
    		
    		if(tm.tile[tm.mapTileNum[nicl][niwb]].collision == true)
    			p.collisionOn[5] = true;
    		break;
    		
    	case "right":
    		nicr += p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicr][niwt]].collision == true) 
    			p.collisionOn[2] = true;
    			
    		if(tm.tile[tm.mapTileNum[nicr][niym]].collision == true)
    			p.collisionOn[4] = true;
    		
    		if(tm.tile[tm.mapTileNum[nicr][niwb]].collision == true)
    			p.collisionOn[7] = true;
    		break;
    	}
    }
}
