package com.libjam.neonrain.vabrant.action.backend;

import com.badlogic.gdx.math.Interpolation;

public class VBSingleBackEndActionable<T> extends VBBackEndActionable<T>{
	
	private final VBSingleBackEndAction<T> _action = new VBSingleBackEndAction<>();
	
	@Override
	public void add(T item) {
		_action.add(item);
	}
	
	@Override
	public void reset() {
		super.reset();
		_action.reset();
	}
	
	public T getItem() {
		return _action.getItem();
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		super.set(duration, reverseBackToStart, interpolation);
	}
	
	public boolean update(float delta) {
		_timer += delta;
		if(_timer < _duration) {
			float percent = calculatePercent(_timer);
			_actionableComponent.percent(_action.getItem(), percent);
			return true;
		}
		else {
			_actionableComponent.itemOver(_action.getItem());
			return false;
		}
	}
}
