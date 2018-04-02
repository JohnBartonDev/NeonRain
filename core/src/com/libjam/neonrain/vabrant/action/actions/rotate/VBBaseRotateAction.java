package com.libjam.neonrain.vabrant.action.actions.rotate;

import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponent;

public class VBBaseRotateAction<A extends VBBackEndActionable<VBRotatable>, L extends VBBaseRotateAction> extends VBActionableComponent<A, VBRotatable, L>{
	
	private boolean _cap;
	
	public VBBaseRotateAction(A action) {
		super(action);
	}
	
	public void capAt360(boolean b) {
		_cap = b;
	}
	
	public void rotateBy(VBRotatable rotate, float amount) {
		rotate.setStartRotation(rotate.getRotation());
		rotate.setEndRotation(rotate.getStartRotation() + amount);
		add(rotate);
	}
	
	public void rotateTo(VBRotatable rotate, float end) {
		rotate.setStartRotation(rotate.getRotation());
		rotate.setEndRotation(end);
		add(rotate);
	}

	@Override
	public void percent(VBRotatable type, float percent) {
		float rotation = MathUtils.lerp(type.getStartRotation(), type.getEndRotation(), percent);
		type.setRotation(rotation);
	}

	@Override
	public void itemOver(VBRotatable type) {
		if(getBackEndAction().reverseBackToStart()) {
			float rotation = type.getStartRotation();
			if(_cap) rotation %= 360;
			type.setRotation(rotation);
		}
		else {
			float rotation = type.getEndRotation();
			if(_cap) rotation %= 360;
			type.setRotation(rotation);
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		_cap = false;
	}
}
