package com.libjam.neonrain.vabrant.action.actions.color;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;

public class VBMultiColorAction extends VBBaseColorAction<VBMultiBackEndActionable<VBColorable>, VBMultiColorAction> {

	public VBMultiColorAction() {
		super(new VBMultiBackEndActionable<VBColorable>());
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		setMulti(getBackEndAction(), duration, offset, reverseBackToStart, interpolation);
	}
}
