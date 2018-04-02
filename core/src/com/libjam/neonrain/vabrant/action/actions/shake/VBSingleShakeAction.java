package com.libjam.neonrain.vabrant.action.actions.shake;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public class VBSingleShakeAction extends VBBaseShakeAction<VBSingleBackEndActionable<VBShakable>, VBSingleShakeAction>{

	public VBSingleShakeAction() {
		super(new VBSingleBackEndActionable<>());
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		setSingle(getBackEndAction(), duration, reverseBackToStart, interpolation);
	}
}
