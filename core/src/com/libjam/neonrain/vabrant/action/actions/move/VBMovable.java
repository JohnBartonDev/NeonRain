package com.libjam.neonrain.vabrant.action.actions.move;

import com.libjam.neonrain.vabrant.action.actions.VBActionable;

public interface VBMovable extends VBActionable {
	public void setPosition(float x, float y);
	public void setX(float x);
	public void setStartX(float x);
	public void setEndX(float x);
	public void setY(float y);
	public void setStartY(float y);
	public void setEndY(float y);
	public float getX();
	public float getStartX();
	public float getEndX();
	public float getY();
	public float getStartY();
	public float getEndY();
}
