package com.libjam.neonrain.vabrant.action.actions;

import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndAction;
import com.libjam.neonrain.vabrant.action.components.VBActionComponent;

public class VBDelayAction extends VBActionComponent<VBSingleBackEndAction<VBActionable>, VBActionable, VBDelayAction> {

	private float _timer;
	private float _duration;
	
	public VBDelayAction() {
		super(new VBSingleBackEndAction<>());
	}

	public void set(float duration) {
		_duration = duration;
	}
	
	@Override
	public void add(VBActionable item) {
	}
	
	@Override
	protected void startAction() {
		_timer = 0;
	}

	@Override
	protected void endAction() {
		
	}

	@Override
	public void reset() {
		super.reset();
		getBackEndAction().reset();
		_duration = 0;
	}

	@Override
	protected boolean updateAction(float delta) {
		if(_timer >= _duration) return false;
		_timer += delta;
		return true;
	}
}

