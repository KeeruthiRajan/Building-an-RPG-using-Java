package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.CollisionHandler;
import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    TileManager tileM;
    CollisionHandler colH;
    public int screenX, screenY;
    public boolean collisionOn[][] = new boolean[3][3];

    public Player(GamePanel gp, KeyHandler keyH, TileManager tileM) {
        this.gp = gp;
        this.keyH = keyH;
        this.tileM = tileM;
        colH = new CollisionHandler(gp, this ,tileM);
        setDefaultValues(16, 5, 4);
        getPlayerImage();
    }

    public void setDefaultValues(int tileX, int tileY, int speed) {
        worldX = gp.tileSize * tileX;
        worldY = gp.tileSize * tileY;
        this.speed = speed;
        this.direction = "down";
        this.screenX = (gp.screenWidth - gp.tileSize) / 2;
        this.screenY = (gp.screenHeight - gp.tileSize) / 2;
        this.hitBox = new Rectangle(9, 21, 27, 21);
    }

    public void getPlayerImage() {
        try {
            u1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            u2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            d1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            d2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            l1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            l2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            r1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            r2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void collisionResetter(boolean b[][]) {
    	for(int i = 0; i < b.length; i++) {
    		for(int j = 0; j < b[0].length; j++)
    			b[i][j] = false;
    	}
    }

    public void update() {
        boolean moving = keyH.isLeft || keyH.isRight || keyH.isUp || keyH.isDown;
        if (moving) {
            if (keyH.isLeft) {
                if (!direction.equals("left")) direction = "left";
                if (collisionOn[1][0] == false) {
                	if(collisionOn[0][0] == false || collisionOn[2][0] == false)
                		worldX -= speed;
                }
            }
            if (keyH.isRight) {
                if (!direction.equals("right")) direction = "right";
                if (collisionOn[1][2] == false) {
                	if(collisionOn[0][2] == false || collisionOn[2][2] == false)
                		worldX += speed;
                }
            }
            if (keyH.isUp) {
                if (!direction.equals("up")) direction = "up";
                if (collisionOn[0][1] == false) {
                	if(collisionOn[0][0] == false || collisionOn[0][2] == false)
                		worldY -= speed;
                }
            }
            if (keyH.isDown) {
                if (!direction.equals("down")) direction = "down";
                if (collisionOn[2][1] == false) {
                	if(collisionOn[2][0] == false || collisionOn[2][2] == false)
                		worldY += speed;
                }
            }
            
            collisionResetter(collisionOn);
            colH.playerCollision(direction, hitBox);
            
            spriteCounter++;
            if (spriteCounter > 15) {
                spriteNum = ~(spriteNum);
                spriteCounter = 0;
            }
        } 
        else {
        	spriteNum = 0;
        	spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        if (direction.equals("down")) {
            if (spriteNum == 0) image = d1;
            else image = d2;
        } else if (direction.equals("up")) {
            if (spriteNum == 0) image = u1;
            else image = u2;
        } else if (direction.equals("left")) {
            if (spriteNum == 0) image = l1;
            else image = l2;
        } else if (direction.equals("right")) {
            if (spriteNum == 0) image = r1;
            else image = r2;
        }

        if (image != null) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
