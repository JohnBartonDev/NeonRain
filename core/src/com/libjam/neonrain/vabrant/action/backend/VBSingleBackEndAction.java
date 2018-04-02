package com.libjam.neonrain.vabrant.action.backend;

public class VBSingleBackEndAction<T> implements VBBackEndAction<T>{

	private T _item;

	@Override
	public void add(T item) {
		_item = item;
	}
	
	@Override
	public void reset() {
		_item = null;
	}
	
	public T getItem() {
		return _item;
	}
	
}
