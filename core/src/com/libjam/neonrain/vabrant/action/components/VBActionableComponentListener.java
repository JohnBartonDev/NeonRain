package com.libjam.neonrain.vabrant.action.components;

public interface VBActionableComponentListener<T> {
	public void percent(T item, float percent);
	public void itemOver(T item);
}
