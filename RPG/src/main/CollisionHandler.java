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
	
    public void playerCollision(String dir, Rectangle hb) {
    	int worldLeftX = hb.x + p.worldX;
    	int worldRightX = hb.x + hb.width + p.worldX;
    	int worldTop = hb.y + p.worldY;
    	int worldBottom = hb.y + hb.height + p.worldY;
    	
    	int nicl = worldLeftX / gp.tileSize;
    	int nicr = worldRightX / gp.tileSize;
    	int niwt = worldTop / gp.tileSize;
    	int niwb = worldBottom / gp.tileSize;
    	
    	switch(dir) {
    	case "up":
    		niwt -= p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicl][niwt]].collision == true || tm.tile[tm.mapTileNum[nicr][niwt]].collision == true)
    			p.collisionOn = true;
    		break;
    	case "down":
    		niwb += p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicl][niwb]].collision == true || tm.tile[tm.mapTileNum[nicr][niwb]].collision == true)
    			p.collisionOn = true;
    		break;
    	case "left":
    		nicl -= p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicl][niwt]].collision == true || tm.tile[tm.mapTileNum[nicl][niwb]].collision == true)
    			p.collisionOn = true;
    		break;
    	case "right":
    		nicr += p.speed / gp.tileSize;
    		if(tm.tile[tm.mapTileNum[nicr][niwt]].collision == true || tm.tile[tm.mapTileNum[nicr][niwb]].collision == true)
    			p.collisionOn = true;
    		break;
    	}
    }
}
