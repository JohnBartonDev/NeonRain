package com.libjam.neonrain.vabrant.action.actions;

import com.libjam.neonrain.vabrant.action.components.VBActionComponent;

public class VBActionTag {
	
	VBActionComponent _action;
	
	public void setAction(VBActionComponent action) {
		_action = action;
	}
	
	public void reset() {
		_action = null;
	}
	
	public void end() {
		if(!isRunning()) return;
		_action.end();
	}
	
	public boolean isRunning() {
		if(_action == null) return false;
		return _action.isRunning();
	}
}
