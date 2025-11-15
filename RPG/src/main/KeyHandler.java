package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	public boolean isLeft, isRight, isUp, isDown;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_LEFT) {
			isLeft = true;
		}
		if(code == KeyEvent.VK_RIGHT) {
			isRight = true;
		}
		if(code == KeyEvent.VK_UP) {
			isUp = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			isDown = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_LEFT) {
			isLeft = false;
		}
		if(code == KeyEvent.VK_RIGHT) {
			isRight = false;
		}
		if(code == KeyEvent.VK_UP) {
			isUp = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			isDown = false;
		}
	} 
}
