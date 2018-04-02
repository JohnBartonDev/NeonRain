package com.libjam.neonrain.vabrant.action.backend;

import com.badlogic.gdx.utils.Array;

public class VBMultiBackEndAction<T> implements VBBackEndAction<T> {
	
	private Array<T> _items = new Array<>();

	@Override
	public void add(T item) {
		_items.add(item);
	}
	
	@Override
	public void reset() {
		_items.clear();
	}
	
	public Array<T> getItems(){
		return _items;
	}
	
}
