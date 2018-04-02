package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.libjam.neonrain.Constants.Direction;
import com.libjam.neonrain.vabrant.action.actions.VBDefaultActionable;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBScreen;

public abstract class RainDrop extends VBDefaultActionable{
	
	private boolean _isAlive;
	private boolean _isMoving;
	public final int _headSize = 10;
	private final int _eyeSize = 8;
	private final int _eyeOffset = (_headSize - _eyeSize) / 2;
	private int _speed;
	private Rectangle _hitBox;
	private Direction _direction = Direction.IDLE;
	
	private int _maxLength;
	private int _currentSnap = 0;
	private Rectangle[] _body;
	
	public RainDrop() {
		_body = new Rectangle[50];
		
		for(int i = 0; i < _body.length; i++) {
			_body[i] = new Rectangle();
			_body[i].setSize(_headSize);
		}
		_currentSnap = _maxLength;
		
		_hitBox = new Rectangle(getX(), getY(), _headSize, _headSize);
	}
	
	public void setup(float x, float y, Color color, int length, int speed, Direction direction) {
		_direction = direction;
		setPosition(x,y);
		setColor(color);
		_maxLength = length;
		resetSnaps();
		setSpeed(speed);
	}
	
	public void start() {
		_isAlive = true;
		_isMoving = true;
	}
	
	public void kill() {
		setMoving(false);
		_isAlive = false;
	}
	
	public int getLength() {
		return _maxLength;
	}
	
	public Rectangle[] getBody() {
		return _body;
	}
	
	public void resetSnaps() {
		for(int i = 0; i < _body.length; i++) {
			_body[i].setPosition(getX(), getY());
		}
	}
	
	public boolean isAlive() {
		return _isAlive;
	}
	
	public void setMoving(boolean moving) {
		_isMoving = moving;
	}
	
	public boolean isMoving() {
		return _isMoving;
	}
	
	private void snapBody() {
		++_currentSnap;
		if(_currentSnap >= _body.length || _currentSnap >= _maxLength) _currentSnap = 0;
		_body[_currentSnap].setPosition(getX(), getY());
	}
	
	private void drawBody(TextureRegion tex, VBScreen screen, VBCamera camera) {
		for(int i = 0; i < _maxLength; i++) {
			Rectangle snap = _body[i];
			camera.drawRegion(screen, tex, snap.x, snap.y, snap.width/2, snap.height/2, snap.width, snap.height, getScaleX(), getScaleY(), getRotation(), true, ShakeType.ALL);
		}
	}
	
	public Rectangle getHitBox() {
		return _hitBox;
	}
	
	public void drawHitBox(ShapeRenderer renderer) {
		renderer.setColor(Color.RED);
		renderer.rect(getX(), getY(), _hitBox.width, _hitBox.height);
	}
	
	public Direction getDirection() {
		return _direction;
	}
	
	public boolean setDirection(Direction direction) {
		switch(direction) {
			case LEFT:
				if(Constants.getOppositeDirection(getDirection()) == direction) return false;
				break;
			case RIGHT:
				if(Constants.getOppositeDirection(getDirection()) == direction) return false;
				break;
			case UP:
				if(Constants.getOppositeDirection(getDirection()) == direction) return false;
				break;
			case DOWN:
				if(Constants.getOppositeDirection(getDirection()) == direction) return false;
				break;
		}
		
		_direction = direction;
		return true;
	}
	
	public void setSpeed(int speed) {
		_speed = speed;
	}
	
	public int getSpeed() {
		return _speed;
	}
	
	public void update(float delta) {
		if(!isMoving() || getDirection() == Direction.IDLE) return;
		
		switch(_direction) {
			case LEFT:
				setX(getX() - (_speed * delta));
				break;
			case RIGHT:
				setX(getX() + (_speed * delta));
				break;
			case UP:
				setY(getY() + (_speed * delta));
				break;
			case DOWN:
				setY(getY() - (_speed * delta));
				break;
		}
		
		snapBody();
		_hitBox.setPosition(getX(), getY());
		updateDrop(delta);
	}
	
	public void draw(TextureRegion tex, VBScreen screen, VBCamera camera) {
		Color old = camera.getBatch().getColor();
		camera.getBatch().setColor(getColor());
		
		drawBody(tex, screen, camera);
		
		camera.drawRegion(screen, tex, getX(), getY(), _headSize/2, _headSize/2, _headSize, _headSize, getScaleX(), getScaleY(), getRotation(), true, ShakeType.ALL);
		camera.getBatch().setColor(Color.WHITE);
		camera.drawRegion(screen, tex, getX() + _eyeOffset, getY() + _eyeOffset, _eyeSize/2, _eyeSize/2, _eyeSize, _eyeSize, getScaleX(), getScaleY(), getRotation(), true, ShakeType.ALL);
		camera.getBatch().setColor(old);
		
	}
	
	public abstract void updateDrop(float delta);

}
