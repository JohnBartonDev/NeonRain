package com.libjam.neonrain.vabrant.action.components;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.actions.VBActionable;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public abstract class VBActionableComponent<A extends VBBackEndActionable<T>, T extends VBActionable, L extends VBActionComponent> extends VBActionComponent<A, T, L> implements VBActionableComponentListener<T>{
	
	public VBActionableComponent(A action) {
		super(action);
		action.setActionableListener(this);
	}

	protected void setSingle(VBSingleBackEndActionable<T> action, float duration, boolean reverseBackToStart, Interpolation interpolation) {
		action.set(duration, reverseBackToStart, interpolation);
	}
	
	protected void setMulti(VBMultiBackEndActionable<T> action, float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		action.set(duration, offset, reverseBackToStart, interpolation);
	}

	@Override
	protected void startAction() {
		getBackEndAction().resetTimer();
	}
	
	@Override
	protected void endAction() {
	}
	
	@Override
	protected boolean updateAction(float delta) {
		return getBackEndAction().update(delta);
	}
	
	@Override
	public void reset() {
		super.reset();
		getBackEndAction().reset();
	}
}
