package com.libjam.neonrain.vabrant.action.actions.rotate;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public class VBSingleRotateAction extends VBBaseRotateAction<VBSingleBackEndActionable<VBRotatable>, VBSingleRotateAction> {

	public VBSingleRotateAction() {
		super(new VBSingleBackEndActionable<VBRotatable>());
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		setSingle(getBackEndAction(), duration, reverseBackToStart, interpolation);
	}
}
