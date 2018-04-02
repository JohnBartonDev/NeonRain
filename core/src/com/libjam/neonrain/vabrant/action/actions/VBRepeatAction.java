package com.libjam.neonrain.vabrant.action.actions;

import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndAction;
import com.libjam.neonrain.vabrant.action.components.VBActionComponent;

public class VBRepeatAction extends VBActionComponent<VBSingleBackEndAction<VBActionComponent>, VBActionComponent, VBRepeatAction> {

	private boolean _continuous;
	private int _timesToRepeat;
	
	public VBRepeatAction() {
		super(new VBSingleBackEndAction<>());
	}
	
	public void set(int amount) {
		_timesToRepeat = amount;
	}
	
	public void setContinuous() {
		_continuous = true;
	}
	
	@Override
	protected void startAction() {
		VBActionComponent action = getBackEndAction().getItem();
		action.start();
	}
	
	public VBActionComponent getItem() {
		return getBackEndAction().getItem();
	}

	@Override
	protected void endAction() {
		getItem().end();
	}

	@Override
	public void reset() {
		super.reset();
		getItem().reset();
		_timesToRepeat = 0;
		_continuous = false;
	}

	@Override
	protected boolean updateAction(float delta) {
		VBActionComponent action = getItem();
		if(!action.update(delta)) {
			if(_timesToRepeat > 0 || _continuous) {
				if(!_continuous)_timesToRepeat--;
				action.start();
			}
			else {
				end();
				return false;
			}
		}
		return true;
	}

}
