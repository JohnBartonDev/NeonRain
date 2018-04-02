package com.libjam.neonrain.vabrant.action.actions.move;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;

public class VBMultiMoveAction extends VBBaseMoveAction<VBMultiBackEndActionable<VBMovable>, VBMultiMoveAction> {

	public VBMultiMoveAction() {
		super(new VBMultiBackEndActionable<VBMovable>());
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		setMulti(getBackEndAction(), duration, offset, reverseBackToStart, interpolation);
	}
}
