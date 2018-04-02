package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.libjam.neonrain.Constants.Direction;

public class BlueDrop extends EnemyDrop{
	
	private float _followTimer;
	private float _lifeTimer;
	private float _followGap = 0.5f;
	private float _lifeDuration = 5f;
	private PlayerDrop _player;
	
	public void setup(PlayerDrop player, float x, int length) {
		_player = player;
		setup(x, Color.BLUE, length, 125, Direction.DOWN);
	}
	
	@Override
	public void updateDrop(float delta) {
		super.updateDrop(delta);
		
		_lifeTimer += delta;
		_followTimer += delta;
		
		if(_lifeTimer >= _lifeDuration) {
			kill();
			return;
		}
		
		if(_followTimer >= _followGap) {
			_followTimer = 0;
			
			float speed = getSpeed() * delta;
			
			Direction oppositeDirection = Constants.getOppositeDirection(getDirection());
			
			float leftDst = Vector2.dst(_player.getX(), _player.getY(), getX() - speed, getY());
			float rightDst = Vector2.dst(_player.getX(), _player.getY(), getX() + speed, getY());
			float upDst = Vector2.dst(_player.getX(), _player.getY(), getX(), getY() + speed);
			float downDst = Vector2.dst(_player.getX(), _player.getY(), getX(), getY() - speed);

			switch(oppositeDirection) {
				case LEFT:
					leftDst = 99999999;
					break;
				case RIGHT:
					rightDst = 99999999;
					break;
				case UP:
					upDst = 99999999;
					break;
				case DOWN:
					downDst = 99999999;
					break;
			}
			
			float min = min(leftDst, rightDst, upDst, downDst);
			
			if(min == leftDst) {
				setDirection(Direction.LEFT);
				return;
			}
			
			if(min == rightDst) {
				setDirection(Direction.RIGHT);
				return;
			}
			
			if(min == upDst) {
				setDirection(Direction.UP);
				return;
			}
			
			if(min == downDst) {
				setDirection(Direction.DOWN);
				return;
			}
			
		}
	}
	
	public static float min(float... values){
        float min;
        if(values.length == 0) return 0;
        min = values[0];
        
        for(int i = 1; i < values.length; i++){
            if(min > values[i]){
                min = values[i];
            }
        }
        return min;
    } 
	
	@Override
	public void reset() {
		super.reset();
		_lifeTimer = 0;
		_followTimer = 0;
		_player = null;
	}
}
