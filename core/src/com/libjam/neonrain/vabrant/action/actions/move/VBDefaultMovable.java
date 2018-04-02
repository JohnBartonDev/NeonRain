package com.libjam.neonrain.vabrant.action.actions.move;

public class VBDefaultMovable implements VBMovable{
	
	private float _x;
	private float _startX;
	private float _endX;
	private float _y;
	private float _startY;
	private float _endY;
	
	@Override
	public void setPosition(float x, float y) {
		_x = x;
		_y = y;
	}

	@Override
	public void setX(float x) {
		_x = x;
	}

	@Override
	public void setStartX(float x) {
		_startX = x;
	}

	@Override
	public void setEndX(float x) {
		_endX = x;
	}

	@Override
	public void setY(float y) {
		_y = y;
	}

	@Override
	public void setStartY(float y) {
		_startY = y;
	}

	@Override
	public void setEndY(float y) {
		_endY = y;
	}

	@Override
	public float getX() {
		return _x;
	}

	@Override
	public float getStartX() {
		return _startX;
	}

	@Override
	public float getEndX() {
		return _endX;
	}

	@Override
	public float getY() {
		return _y;
	}

	@Override
	public float getStartY() {
		return _startY;
	}

	@Override
	public float getEndY() {
		return _endY;
	}
	
	public void reverseX() {
		float tmp = getStartX();
		setStartX(getEndX());
		setEndX(tmp);
	}
	
	public void reverseY() {
		float tmp = getStartY();
		setStartY(getEndY());
		setEndY(tmp);
	}

}
