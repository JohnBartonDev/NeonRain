package com.libjam.neonrain.vabrant.action.components;

import com.badlogic.gdx.utils.Pool.Poolable;
import com.libjam.neonrain.vabrant.action.actions.VBActionListener;
import com.libjam.neonrain.vabrant.action.actions.VBActionTag;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndAction;

@SuppressWarnings("rawtypes")
public abstract class VBActionComponent<A extends VBBackEndAction<T>, T, L extends VBActionComponent> implements Poolable{

	private boolean _isRunning;
	private A _action;
	protected VBActionListener _listener;
	private VBActionTag _tag;
	
	public VBActionComponent(A action) {
		_action = action;
	}
	
	public void setListener(VBActionListener<L> listener) {
		_listener = listener;
	}
	
	public void setTag(VBActionTag tag) {
		_tag = tag;
		_tag.setAction(this);
	}
	
	public void start() {
		_isRunning = true;
		startAction();
	}
	
	public void end() {
		_isRunning = false;
		if(_listener != null) _listener.actionOver(this);
		endAction();
	}
	
	public boolean update(float delta) {
		if(!isRunning()) return false;
		if(!updateAction(delta)) {
			end();
			return false;
		}
		else {
			if(_listener != null) _listener.updateOver(this);
			return true;
		}
	}

	public boolean isRunning() {
		return _isRunning;
	}
	
	public void add(T item) {
		_action.add(item);
	}
	
	@Override
	public void reset() {
		_listener = null;
		if(_tag != null) {
			_tag.reset();
			_tag = null;
		}
	}
	
	public A getBackEndAction() {
		return _action;
	}
	
	protected abstract void startAction();
	protected abstract void endAction();
//	protected abstract void resetAction();
	protected abstract boolean updateAction(float delta);
}
