package com.libjam.neonrain.vabrant.action.actions.shake;

import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponent;

@SuppressWarnings("rawtypes")
public class VBBaseShakeAction<A extends VBBackEndActionable<VBShakable>, L extends VBBaseShakeAction> extends VBActionableComponent<A, VBShakable, L>{

	private float _shakeAmount;
	private float _maxX;
	private float _maxY;
	private float _maxAngle;
	float _maxTime;
	
	public VBBaseShakeAction(A action) {
		super(action);
	}
	
	public void shake(VBShakable shake, float shakeAmount, float maxOffset, float maxAngle) {
		_shakeAmount = shakeAmount;
		_maxX = maxOffset;
		_maxY = maxOffset;
		_maxAngle = maxAngle;
		_maxTime = 0.1f;
		add(shake);
	}

	@Override
	public void percent(VBShakable item, float percent) {
		if(percent == 1) return;
	
		float x = _maxX * _shakeAmount * MathUtils.random(-1, 1);
		float y = _maxY * _shakeAmount * MathUtils.random(-1, 1);
		float angle = _maxAngle * MathUtils.random(-1, 1);
		item.setShake(x,y,angle);
	}
	
	@Override
	public void itemOver(VBShakable item) {
		item.setShake(0, 0, 0);
	}
	
	@Override
	public void reset() {
		super.reset();
		_maxX = 0;
		_maxY = 0;
		_maxAngle = 0;
	}
	
}
