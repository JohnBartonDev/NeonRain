package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.Constants.Direction;

public class YellowDrop extends EnemyDrop{

	public void setup(float x, int length) {
		int speed = MathUtils.random(80, 250);
		setup(x, Color.YELLOW, length, speed, Direction.DOWN);
	}
	
}
