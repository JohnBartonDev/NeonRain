package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.Constants.Direction;
import com.libjam.neonrain.vabrant.util.VBUtils;

public class RedDrop extends EnemyDrop{
	
	public enum RedDropType{
		SPEED_DEMON,
		WRONG_EXIT
	}
	
	private boolean _turnOver;
	private float _timer;
	private float _turnTime;
	private int _minSpeed;
	private int _maxSpeed;
	private RedDropType _type;
	
	public void setup(float x, int length) {
		_type = MathUtils.randomBoolean() ? RedDropType.SPEED_DEMON : RedDropType.WRONG_EXIT;
		
		switch(_type) {
			case SPEED_DEMON:
				_minSpeed = MathUtils.random(40, 60);
				_maxSpeed = MathUtils.random(150, 250);
				break;
			case WRONG_EXIT:
				_minSpeed = MathUtils.random(100, 200);
				_turnTime = MathUtils.random(0.5f, 2);
				break;
		}
		
		setup(x, Color.RED, length, _minSpeed, Direction.DOWN);
	}
	
	@Override
	public void updateDrop(float delta) {
		super.updateDrop(delta);

		_timer += delta;
		
		switch(_type) {
			case SPEED_DEMON:
				int speed = (int)VBUtils.map(_timer, 0, 1f, _minSpeed, _maxSpeed);
				setSpeed(speed);
				break;
			case WRONG_EXIT:
				if(_timer >= _turnTime && !_turnOver) {
					_turnOver = true;
					int index = MathUtils.random(0, 3);
					
					Direction direction = Direction.values()[index];
					
					if(direction == Direction.UP) {
						direction = Direction.DOWN;
					}
					
					setDirection(direction);
				}
				
				break;
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		_turnOver = false;
		_timer = 0;
	}

}
