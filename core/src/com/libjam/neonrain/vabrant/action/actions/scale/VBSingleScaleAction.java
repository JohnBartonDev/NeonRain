package com.libjam.neonrain.vabrant.action.actions.scale;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public class VBSingleScaleAction extends VBBaseScaleAction<VBSingleBackEndActionable<VBScalable>, VBSingleScaleAction> {
	
	public VBSingleScaleAction() {
		super(new VBSingleBackEndActionable<VBScalable>());
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		setSingle(getBackEndAction(), duration, reverseBackToStart, interpolation);
	}

}
