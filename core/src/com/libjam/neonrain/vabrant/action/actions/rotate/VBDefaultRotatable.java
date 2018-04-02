package com.libjam.neonrain.vabrant.action.actions.rotate;

public class VBDefaultRotatable implements VBRotatable{
	
	private float _rotation;
	private float _startRotation;
	private float _endRotation;

	@Override
	public void setRotation(float rotation) {
		_rotation = rotation;
	}

	@Override
	public void setStartRotation(float rotation) {
		_startRotation = rotation;
	}

	@Override
	public void setEndRotation(float rotation) {
		_endRotation = rotation;
	}

	@Override
	public float getRotation() {
		return _rotation;
	}

	@Override
	public float getStartRotation() {
		return _startRotation;
	}

	@Override
	public float getEndRotation() {
		return _endRotation;
	}

}
