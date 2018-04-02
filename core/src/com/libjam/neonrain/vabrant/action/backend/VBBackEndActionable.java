package com.libjam.neonrain.vabrant.action.backend;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponentListener;

public abstract class VBBackEndActionable<T> implements VBBackEndAction<T>{
	
	protected boolean _reverseBackToStart;
	protected float _timer;
	protected float _duration;
	protected Interpolation _interpolation = Interpolation.linear;
	protected VBActionableComponentListener<T> _actionableComponent;
	
	public void setActionableListener(VBActionableComponentListener<T> component) {
		_actionableComponent = component;
	}
	
	public float getTime() {
		return _timer;
	}
	
	public float getDuration() {
		return _duration;
	}

	public void resetTimer() {
		_timer = 0;
	}
	
	@Override
	public void reset() {
		_reverseBackToStart = false;
		_timer = 0;
		_duration = 0;
		_interpolation = Interpolation.linear;
	}
	
	public boolean reverseBackToStart() {
		return _reverseBackToStart;
	}
	
	protected void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		_duration = duration;
		_reverseBackToStart = reverseBackToStart;
		_interpolation = interpolation;
	}
	
	public float calculatePercent(float time) {
		float percent = time / _duration;
		percent = _interpolation.apply(percent);
		if(_reverseBackToStart) {
			percent *= 2;
			
			if(percent >= 1f) {
				percent = 2 - percent;
			}
		}
		return percent;
	}
	
	public abstract boolean update(float delta);
}
