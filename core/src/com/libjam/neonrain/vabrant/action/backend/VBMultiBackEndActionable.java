package com.libjam.neonrain.vabrant.action.backend;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;

public class VBMultiBackEndActionable<T> extends VBBackEndActionable<T> {

	private final VBMultiBackEndAction<T> _action = new VBMultiBackEndAction<>();
	
	private float _offset;
	
	@Override
	public void add(T item) {
		_action.add(item);
	}
	
	@Override
	public void reset() {
		super.reset();
		_action.reset();
		_offset = 0;
	}
	
	public Array<T> getItems(){
		return _action.getItems();
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		set(duration, reverseBackToStart, interpolation);
		_offset = offset;
	}
	
	public boolean update(float delta) {
		Array<T> items = _action.getItems();

		_timer += delta;
		
		for(int i = 0, size = items.size; i < size; i++) {
			float time = _timer - (_offset * i);

			if(time < 0) {
				continue;
			}
			
			T item = items.get(i);
			if(time < _duration) {
				float percent = calculatePercent(time);
				_actionableComponent.percent(item, percent);
			}
			else {
				_actionableComponent.itemOver(item);
				if(i == items.size - 1) {
					return false;
				}
			}
		}
		return true;
	}
}
