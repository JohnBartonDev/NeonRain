package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.utils.Pool.Poolable;

public class VBVector2 implements Poolable{
	
	private float _value1;
	private float _value2;
	
	public VBVector2() {
		
	}
	
	public VBVector2(float value1, float value2){
		_value1 = value1;
		_value2 = value2;
	}
	
	public void set(float value1, float value2) {
		_value1 = value1;
		_value2 = value2;
	}
	
	//basic values
	public void setValue1(float value1) {_value1 = value1;}
	public void setValue2(float value2) {_value2 = value2;}
	public float getValue1() {return _value1;}
	public float getValue2() {return _value2;}
	
	//x and y values
	public void setX(float x) {_value1 = x;}
	public void setY(float y) {_value2 = y;}
	public float getX() {return _value1;}
	public float getY() {return _value2;}
	
	//start and end
	public void setStart(float start) {_value1 = start;}
	public void setEnd(float end) {_value2 = end;}
	public float getStart() {return _value1;}
	public float getEnd() {return _value2;}
	
	//width and height
	public void setWidth(float width) {_value1 = width;}
	public void setHeight(float height) {_value2 = height;}
	public float getWidth() {return _value1;}
	public float getHeight() {return _value2;}
	
	@Override
	public void reset() {
		_value1 = 0;
		_value2 = 0;
	}

}
