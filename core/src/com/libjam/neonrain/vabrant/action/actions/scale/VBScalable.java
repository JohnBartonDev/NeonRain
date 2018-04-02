package com.libjam.neonrain.vabrant.action.actions.scale;

import com.libjam.neonrain.vabrant.action.actions.VBActionable;

public interface VBScalable extends VBActionable{
	public void setScale(float x, float y);
	public void setScaleX(float scale);
	public void setStartScaleX(float scale);
	public void setEndScaleX(float scale);
	public void setScaleY(float scale);
	public void setStartScaleY(float scale);
	public void setEndScaleY(float scale);
	public float getStartScaleX();
	public float getEndScaleX();
	public float getScaleX();
	public float getStartScaleY();
	public float getEndScaleY();
	public float getScaleY();
}
