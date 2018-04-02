package com.libjam.neonrain.vabrant.action.actions.zoom;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;

public class VBMultiZoomAction extends VBBaseZoomAction<VBMultiBackEndActionable<VBZoomable>, VBMultiZoomAction>{

	public VBMultiZoomAction() {
		super(new VBMultiBackEndActionable<VBZoomable>());
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		setMulti(getBackEndAction(), duration, offset, reverseBackToStart, interpolation);
	}
}
