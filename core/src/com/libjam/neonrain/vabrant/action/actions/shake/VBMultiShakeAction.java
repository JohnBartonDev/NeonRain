package com.libjam.neonrain.vabrant.action.actions.shake;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;

public class VBMultiShakeAction extends VBBaseShakeAction<VBMultiBackEndActionable<VBShakable>, VBMultiShakeAction>{

	public VBMultiShakeAction() {
		super(new VBMultiBackEndActionable<>());
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		setMulti(getBackEndAction(), duration, offset, reverseBackToStart, interpolation);
	}
}
