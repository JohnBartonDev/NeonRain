package com.libjam.neonrain.vabrant.action.actions.color;

import com.badlogic.gdx.graphics.Color;

public class VBDefaultColorable implements VBColorable{
	
	private Color _color;
	private Color _startColor;
	private Color _endColor;

	public VBDefaultColorable() {
		_color = new Color(Color.WHITE);
		_startColor = new Color();
		_endColor = new Color();
	}
	
	@Override
	public void setColor(float r, float g, float b, float a) {
		_color.set(r,g,b,a);
	}
	
	@Override
	public void setColor(Color c) {
		_color.set(c);
	}

	@Override
	public void setStartColor(Color c) {
		_startColor.set(c);
	}
	
	@Override 
	public void setStartColor(float r, float g, float b, float a) {
		_startColor.set(r,g,b,a);
	}

	@Override
	public void setEndColor(Color c) {
		_endColor.set(c);
	}
	
	@Override
	public void setEndColor(float r, float g, float b, float a) {
		_endColor.set(r,g,b,a);
	}

	@Override
	public Color getColor() {
		return _color;
	}
	
	@Override
	public Color getStartColor() {
		return _startColor;
	}

	@Override
	public Color getEndColor() {
		return _endColor;
	}
	
	@Override
	public float getAlpha() {
		return _color.a;
	}

}
