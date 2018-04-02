package com.libjam.neonrain.vabrant.action.actions.scale;

public class VBDefaultScalable implements VBScalable {
	
	private float _scaleX = 1;
	private float _startScaleX = 1;
	private float _endScaleX = 1;
	private float _scaleY = 1;
	private float _startScaleY = 1;
	private float _endScaleY = 1;

	@Override
	public void setStartScaleX(float scale) {
		_startScaleX = scale;
	}

	@Override
	public void setScaleX(float scale) {
		_scaleX = scale;
	}

	@Override
	public void setEndScaleX(float scale) {
		_endScaleX = scale;
	}

	@Override
	public void setStartScaleY(float scale) {
		_startScaleY = scale;
	}

	@Override
	public void setEndScaleY(float scale) {
		_endScaleY = scale;
	}

	@Override
	public void setScaleY(float scale) {
		_scaleY = scale;
	}

	@Override
	public float getStartScaleX() {
		return _startScaleX;
	}

	@Override
	public float getEndScaleX() {
		return _endScaleX;
	}

	@Override
	public float getScaleX() {
		return _scaleX;
	}

	@Override
	public float getStartScaleY() {
		return _startScaleY;
	}

	@Override
	public float getEndScaleY() {
		return _endScaleY;
	}

	@Override
	public float getScaleY() {
		return _scaleY;
	}
	
	public void setScale(float scale) {
		setScale(scale, scale);
	}
	
	public void setScale(float x, float y) {
		setScaleX(x);
		setScaleY(y);
	}

	
}
