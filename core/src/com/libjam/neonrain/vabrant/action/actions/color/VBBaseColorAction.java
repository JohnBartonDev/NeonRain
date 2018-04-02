package com.libjam.neonrain.vabrant.action.actions.color;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponent;

public class VBBaseColorAction<A extends VBBackEndActionable<VBColorable>, L extends VBBaseColorAction> extends VBActionableComponent<A, VBColorable, L>{
	
	private Interpolation _rInterpolation = Interpolation.bounceOut;
	private Interpolation _gInterpolation = Interpolation.sine;
	private Interpolation _bInterpolation = Interpolation.elasticOut;
	private Interpolation _aInterpolation = Interpolation.swingOut;
	
	public VBBaseColorAction(A action) {
		super(action);
	}
	
	public void changeToColor(VBColorable color, Color end) {
		color.setStartColor(color.getColor());
		color.setEndColor(end);
		add(color);
	}
	
	public void fade(VBColorable color, float alpha) {
		Color c = color.getColor();
		color.setStartColor(color.getColor());
		color.setEndColor(c.r, c.g, c.b, MathUtils.clamp(alpha, 0, 1));
		add(color);
	}

	@Override
	public void percent(VBColorable type, float percent) {
		lerp(type, type.getStartColor(), type.getEndColor(), percent);
	}
	
	@Override
	public void itemOver(VBColorable type) {
		if(getBackEndAction().reverseBackToStart()) {
			type.setColor(type.getStartColor());
		}
		else {
			type.setColor(type.getEndColor());
		}
	}
	
	private void lerp(VBColorable type, Color start, Color end, float percent) {
		if(percent > 1) percent = 1;
		
		float r = start.r + (end.r - start.r) * percent;
		float g = start.g + (end.g - start.g) * percent;
		float b = start.b + (end.b - start.b) * percent;
		float a = start.a + (end.a - start.a) * percent;
		
		r = MathUtils.clamp(r, 0, 1);
		g = MathUtils.clamp(g, 0, 1);
		b = MathUtils.clamp(b, 0, 1);
		a = MathUtils.clamp(a, 0, 1);
		
		type.setColor(r, g, b, a);
	}
}
