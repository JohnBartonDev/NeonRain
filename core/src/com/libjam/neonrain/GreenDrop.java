package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.libjam.neonrain.Constants.Direction;

public class GreenDrop extends EnemyDrop{

	public void setup(float x, int length) {
		setup(x, Color.GREEN, length, 150, Direction.DOWN);
	}
}
