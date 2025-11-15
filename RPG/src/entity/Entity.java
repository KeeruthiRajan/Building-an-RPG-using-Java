package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;
	public BufferedImage u1, u2, d1, d2, l1, l2, r1, r2;
	public String direction;
	public int spriteNum = 0;
	public int spriteCounter = 0;
	public Rectangle hitBox;
}
