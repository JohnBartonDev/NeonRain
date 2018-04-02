package com.libjam.neonrain.vabrant.action.actions.scale;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pools;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndActionable;
import com.libjam.neonrain.vabrant.util.VBVector2;

public class VBMultiScaleAction extends VBBaseScaleAction<VBMultiBackEndActionable<VBScalable>, VBMultiScaleAction> {
	
	private boolean _useLocalX;
	private boolean _useLocalY;
	private ObjectMap<VBScalable, VBVector2> _xValues;
	private ObjectMap<VBScalable, VBVector2> _yValues;

	//VBMultiScaleAction can hold scale objects values locally
	//so scale objects can have multiple scale actions
	//Note: Each scale object has a start, end, and current value for x and y, if
	//the scale object is added to a new scale action it will override those values 
	
	public VBMultiScaleAction() {
		super(new VBMultiBackEndActionable<VBScalable>());
		_xValues = new ObjectMap<>();
		_yValues = new ObjectMap<>();
	}
	
	public void set(float duration, float offset, boolean reverseBackToStart, Interpolation interpolation) {
		setMulti(getBackEndAction(), duration, offset, reverseBackToStart, interpolation);
	}
	
	public void scaleTo(VBScalable scale, float start, float end) {
		_useLocalX = true;
		_useLocalY = true;
		
		VBVector2 xVector = Pools.obtain(VBVector2.class);
		VBVector2 yVector = Pools.obtain(VBVector2.class);
		xVector.set(start, end);
		yVector.set(start, end);
		_xValues.put(scale, xVector);
		_yValues.put(scale, yVector);
		add(scale);
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
	protected void checkX_percent(VBScalable type, float percent) {
		//check if we scale the local x
		if(_useLocalX) {
			//check if a key exists
			if(_xValues.containsKey(type)) {
				VBVector2 x = _xValues.get(type);
				float scale = MathUtils.lerp(x.getStart(), x.getEnd(), percent);
				type.setScaleX(scale);
				return;
			}
		}
		
		//check if we scale the normal x
		super.checkX_percent(type, percent);
	}
	
	@Override
	protected void checkY_percent(VBScalable type, float percent) {
		//check if we scale the local y
		if(_useLocalY) {
			//check if a key exists
			if(_yValues.containsKey(type)) {
				VBVector2 yValues = _yValues.get(type);
				float scale = MathUtils.lerp(yValues.getStart(), yValues.getEnd(), percent);
				type.setScaleY(scale);
				return;
			}
		}
		
		//check if we scale the normal y
		super.checkY_percent(type, percent);
	}
	
	@Override
	protected void checkX_itemOver(VBScalable type, boolean reverseBack) {
		//check the local
		if(_useLocalX) {
			//check if key exists
			if(_xValues.containsKey(type)) {
				if(reverseBack) {
					type.setScaleX(_xValues.get(type).getStart());
				}
				else {
					type.setScaleX(_xValues.get(type).getEnd());
				}
				return;
			}
		}
		super.checkX_itemOver(type, reverseBack);
	}
	
	protected void checkY_itemOver(VBScalable type, boolean reverseBack) {
		//check the local
		if(_useLocalY) {
			if(_yValues.containsKey(type)) {
				if(reverseBack) {
					type.setScaleY(_yValues.get(type).getStart());
				}
				else {
					type.setScaleY(_yValues.get(type).getEnd());
				}
				return;
			}
		}
		super.checkY_itemOver(type, reverseBack);
	}
	
	@Override
	public void reset() {
		super.reset();
		_useLocalX = false;
		_useLocalY = false;
		_xValues.clear();
		_yValues.clear();
	}

}
