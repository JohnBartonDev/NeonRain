package com.libjam.neonrain.vabrant.action.actions.move;

import com.badlogic.gdx.math.Interpolation;
import com.libjam.neonrain.vabrant.action.backend.VBSingleBackEndActionable;

public class VBSingleMoveAction extends VBBaseMoveAction<VBSingleBackEndActionable<VBMovable>, VBSingleMoveAction>{

	public VBSingleMoveAction() {
		super(new VBSingleBackEndActionable<VBMovable>());
	}
	
	public void set(float duration, boolean reverseBackToStart, Interpolation interpolation) {
		setSingle(getBackEndAction(), duration, reverseBackToStart, interpolation);
	}
	
}
