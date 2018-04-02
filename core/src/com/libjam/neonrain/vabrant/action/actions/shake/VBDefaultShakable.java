package com.libjam.neonrain.vabrant.action.actions.shake;

public class VBDefaultShakable implements VBShakable{

	private float _shakeX;
	private float _shakeY;
	private float _shakeRotation;
	
	@Override
	public void setShake(float x, float y, float rotation) {
		_shakeX = x;
		_shakeY = y;
		_shakeRotation = rotation;
	}
	
	@Override
	public float getShakeX() {
		return _shakeX;
	}
	
	@Override
	public float getShakeY() {
		return _shakeY;
	}
	
	@Override
	public float getShakeRotation() {
		return _shakeRotation;
	}
	
}
