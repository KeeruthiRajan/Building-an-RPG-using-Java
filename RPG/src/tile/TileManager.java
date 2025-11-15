package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    public Tile tile[];
    public int mapTileNum[][];
    GamePanel gp;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[7];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        setTiles();
        loadMap();
    }

    public void setTiles() {
        try {
            //System.out.println("Image loading started");
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/o1.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/o2.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/o3.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/o4.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/o5.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
            tile[6].collision = true;
            //System.out.println("Image loading finished.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map_1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) break;

                String nums[] = line.trim().split("\\s+");
                int col = 0;
                while (col < gp.maxWorldCol) {
                    if (col < nums.length) {
                        try {
                            mapTileNum[col][row] = Integer.parseInt(nums[col]);
                        } catch (NumberFormatException e) {
                            mapTileNum[col][row] = 0;
                        }
                    } else {
                        mapTileNum[col][row] = 0;
                    }
                    col++;
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int row = 0;
        while (row < gp.maxWorldRow) {
            int col = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                int worldX = gp.tileSize * col;
                int worldY = gp.tileSize * row;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                int tileIndex = mapTileNum[col][row];
                if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                	g2.drawImage(tile[tileIndex].image, screenX, screenY, gp.tileSize, gp.tileSize, null);                    
                    
                }

                col++;
            }
            row++;
        }
    }
}
