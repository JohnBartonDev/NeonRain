package com.libjam.neonrain.vabrant.action.actions.scale;

import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponent;

public class VBBaseScaleAction<A extends VBBackEndActionable<VBScalable>, L extends VBBaseScaleAction> extends VBActionableComponent<A, VBScalable, L>{
	
	private boolean _scaleX;
	private boolean _scaleY;
	
	public VBBaseScaleAction(A action) {
		super(action);
	}
	
	public void scaleBy(VBScalable scale, float amount) {
		setupScaleX(scale, scale.getScaleX() + amount);
		setupScaleY(scale, scale.getScaleY() + amount);
		add(scale);
	}
	
	public void scaleTo(VBScalable scale, float end) {
		setupScaleX(scale, end);
		setupScaleY(scale, end);
		add(scale);
	}
	
	public void scaleXBy(VBScalable scale, float amount) {
		setupScaleX(scale, scale.getScaleX() + amount);
		add(scale);
	}

	public void scaleXTo(VBScalable scale, float end) {
		setupScaleX(scale, end);
		add(scale);
	}

	public void scaleYBy(VBScalable scale, float amount) {
		setupScaleY(scale, scale.getScaleY() + amount);
		add(scale);
	}

	public void scaleYTo(VBScalable scale, float end) {
		setupScaleY(scale, end);
		add(scale);
	}
	
	public void setupScaleY(VBScalable scale, float end) {
		_scaleY = true;
		scale.setStartScaleY(scale.getScaleY());
		scale.setEndScaleY(end);
	}
	
	public void setupScaleX(VBScalable scale, float end) {
		_scaleX = true;
		scale.setStartScaleX(scale.getScaleX());
		scale.setEndScaleX(end);
	}
	
	@Override
	public void percent(VBScalable type, float percent) {
		checkX_percent(type, percent);
		checkY_percent(type, percent);
	}

	@Override
	public void itemOver(VBScalable type) {
		boolean reverseBack = getBackEndAction().reverseBackToStart();
		checkX_itemOver(type, reverseBack);
		checkY_itemOver(type, reverseBack);
	}
	
	@Override
	public void reset() {
		super.reset();
		_scaleX = false;
		_scaleY = false;
	}
	
	protected void checkX_percent(VBScalable type, float percent) {
		if(_scaleX) {
			float scale = MathUtils.lerp(type.getStartScaleX(), type.getEndScaleX(), percent);
			type.setScaleX(scale);
		}
	}
	
	protected void checkY_percent(VBScalable type, float percent) {
		if(_scaleY) {
			float scale = MathUtils.lerp(type.getStartScaleY(), type.getEndScaleY(), percent);
			type.setScaleY(scale);
		}
	}
	
	protected void checkX_itemOver(VBScalable type, boolean reverseBack) {
		if(_scaleX) {
			if(reverseBack) {
				type.setScaleX(type.getStartScaleX());
			}
			else {
				type.setScaleX(type.getEndScaleX());
			}
		}
	}
	
	protected void checkY_itemOver(VBScalable type, boolean reverseBack) {
		if(_scaleY) {
			if(reverseBack) {
				type.setScaleY(type.getStartScaleY());
			}
			else {
				type.setScaleY(type.getEndScaleY());
			}
		}
	}
	
}
