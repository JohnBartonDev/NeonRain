package com.libjam.neonrain.vabrant.action.actions.rotate;

import com.libjam.neonrain.vabrant.action.actions.VBActionable;

public interface VBRotatable extends VBActionable {
	public void setRotation(float rotation);
	public void setStartRotation(float rotation);
	public void setEndRotation(float rotation);
	public float getRotation();
	public float getStartRotation();
	public float getEndRotation();
}
