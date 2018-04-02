package com.libjam.neonrain;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.libjam.neonrain.Constants.Direction;

public class PlayerDrop extends RainDrop{
	
	public PlayerDrop() {
		setup(100, 100, Color.BLACK, 20, 150, Direction.RIGHT);
	}
	
	public void keyDown(int keycode) {
		if(keycode == Keys.D || keycode == Keys.RIGHT) {
			setDirection(Direction.RIGHT);
		}
		
		if(keycode == Keys.A || keycode == Keys.LEFT) {
			setDirection(Direction.LEFT);
		}
		
		if(keycode == Keys.W || keycode == Keys.UP) {
			setDirection(Direction.UP);
		}
		
		if(keycode == Keys.S || keycode == Keys.DOWN) {
			setDirection(Direction.DOWN);
		}
	}
	
	@Override
	public void updateDrop(float delta) {
		checkForOutOfBounds();
	}
	
	public void checkForOutOfBounds() {
		if(getX() < 0 || getX() > 480 - 10 || getY() < 0 || getY() > 320 - 10) {
			kill();
		}
	}
	
}
