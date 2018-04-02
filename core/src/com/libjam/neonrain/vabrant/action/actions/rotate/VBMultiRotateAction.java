package com.libjam.neonrain.vabrant.action.actions.rotate;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;

public class VBMultiRotateAction extends VBBaseRotateAction<VBMultiBackEndActionable<VBRotatable>, VBMultiRotateAction> {

	public VBMultiRotateAction() {
		super(new VBMultiBackEndActionable<VBRotatable>());
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		setMulti(getBackEndAction(), duration, offset, reverseBackToStart, interpolation);
	}
	
	public Array<VBRotatable> getItems(){
		return getBackEndAction().getItems();
	}
}
