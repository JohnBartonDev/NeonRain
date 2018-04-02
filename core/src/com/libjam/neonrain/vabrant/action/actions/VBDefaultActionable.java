package com.libjam.neonrain.vabrant.action.actions;

import com.badlogic.gdx.graphics.Color;
import com.libjam.neonrain.vabrant.action.actions.color.VBColorable;
import com.libjam.neonrain.vabrant.action.actions.move.VBMovable;
import com.libjam.neonrain.vabrant.action.actions.rotate.VBRotatable;
import com.libjam.neonrain.vabrant.action.actions.scale.VBScalable;

public class VBDefaultActionable implements VBColorable, VBMovable, VBScalable, VBRotatable{
	
	//color
	private Color _color = new Color(Color.WHITE);
	private Color _startColor = new Color();
	private Color _endColor = new Color();
	
	@Override public void setColor(Color c) {_color.set(c);}
	
	@Override public void setColor(float r, float g, float b, float a) {_color.set(r,g,b,a);}

	@Override public void setStartColor(Color c) {_startColor.set(c);}
	
	@Override public void setStartColor(float r, float g, float b, float a) {_startColor.set(r,g,b,a);}

	@Override public void setEndColor(Color c) {_endColor.set(c);}

	@Override public void setEndColor(float r, float g, float b, float a) {_endColor.set(r,g,b,a);}
	
	@Override public Color getColor() {return _color;}

	@Override public Color getStartColor() {return _startColor;}

	@Override public Color getEndColor() {return _endColor;}
	
	@Override public float getAlpha() {return _color.a;}
	
	@Override public void setAlpha(float a) {getColor().a = a;}
	
	//move
	private float _x;
	private float _startX;
	private float _endX;
	private float _y;
	private float _startY;
	private float _endY;
	
	@Override public void setPosition(float x, float y){_x = x; _y = y;}
	
	@Override public void setX(float x) {_x = x;}
	
	@Override public void setStartX(float x) {_startX = x;}
	
	@Override public void setEndX(float x) {_endX = x;}
	
	@Override public void setY(float y) {_y = y;}
	
	@Override public void setStartY(float y) {_startY = y;}
	
	@Override public void setEndY(float y) {_endY = y;}
	
	@Override public float getX() {return _x;}
	
	@Override public float getStartX() {return _startX;}
	
	@Override public float getEndX() {return _endX;}
	
	@Override public float getY() {return _y;}
	
	@Override public float getStartY() {return _startY;}
	
	@Override public float getEndY() {return _endY;}
	
	//scale
	private float _scaleX = 1;
	private float _startScaleX = 1;
	private float _endScaleX = 1;
	private float _scaleY = 1;
	private float _startScaleY = 1;
	private float _endScaleY = 1;
	
	@Override public void setScale(float x, float y) {_scaleX = x; _scaleY = y;}
	
	@Override public void setScaleX(float x) {_scaleX = x;}
	
	@Override public void setStartScaleX(float x) {_startScaleX = x;}
	
	@Override public void setEndScaleX(float x) {_endScaleX = x;}
	
	@Override public void setScaleY(float y) {_scaleY = y;}
	
	@Override public void setStartScaleY(float y) {_startScaleY = y;}
	
	@Override public void setEndScaleY(float y) {_endScaleY = y;}
	
	@Override public float getScaleX() {return _scaleX;}
	
	@Override public float getStartScaleX() {return _startScaleX;}
	
	@Override public float getEndScaleX() {return _endScaleX;}
	
	@Override public float getScaleY() {return _scaleY;}
	
	@Override public float getStartScaleY() {return _startScaleY;}
	
	@Override public float getEndScaleY() {return _endScaleY;}
	
	//rotation
	private float _rotation;
	private float _startRotation;
	private float _endRotation;
	
	@Override public void setRotation(float rotation) {_rotation = rotation;}
	
	@Override public void setStartRotation(float rotation) {_startRotation = rotation;}
	
	@Override public void setEndRotation(float rotation) {_endRotation = rotation;}
	
	@Override public float getRotation() {return _rotation;}
	
	@Override public float getStartRotation() {return _startRotation;}
	
	@Override public float getEndRotation() {return _endRotation;}
	
}
