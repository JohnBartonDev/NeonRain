package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.libjam.neonrain.Constants.Direction;

public class EnemyDrop extends RainDrop implements Poolable{
	
	@Override
	public void updateDrop(float delta) {
		if(outOfBounds()) {
			kill();
		}
	}

	public void setup(float x, Color color, int length, int speed, Direction direction) {
		super.setup(x, 320 - 10, color, length, speed, direction);
	}
	
	public boolean outOfBounds() {
		boolean out = true;
		
		int length = getLength();
		Rectangle[] body = getBody();
		
		for(int i = 0; i < length; i++) {
			Rectangle r = body[i];
			if(getDirection() == Direction.LEFT || getDirection() == Direction.RIGHT) {
				if(r.x >= 0 && r.x <= 480 - 10) {
					out = false;
					break;
				}
			}
			else {
				if(r.y >= 0 && r.y <= 320 - 10) {
					out = false;
					break;
				}
			}
		}
		return out;
	}

	@Override
	public void reset() {
	}

}
