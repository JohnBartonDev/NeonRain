package com.libjam.neonrain.vabrant.action.actions;

public interface VBActionListener<T> {
	public default void updateOver(T action) {};
	public default void actionOver(T action) {};
}
