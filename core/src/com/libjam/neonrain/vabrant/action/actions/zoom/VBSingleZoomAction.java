package com.libjam.neonrain.vabrant.action.actions.zoom;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public class VBSingleZoomAction extends VBBaseZoomAction<VBSingleBackEndActionable<VBZoomable>, VBSingleZoomAction>{

	public VBSingleZoomAction() {
		super(new VBSingleBackEndActionable<VBZoomable>());
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		setSingle(getBackEndAction(), duration, reverseBackToStart, interpolation);
	}

}
