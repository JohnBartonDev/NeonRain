package com.libjam.neonrain.vabrant.action.actions.color;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public class VBSingleColorAction extends VBBaseColorAction<VBSingleBackEndActionable<VBColorable>, VBSingleColorAction> {

	public VBSingleColorAction() {
		super(new VBSingleBackEndActionable<VBColorable>());
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		setSingle(getBackEndAction(), duration, reverseBackToStart, interpolation);
	}
}
