package com.libjam.neonrain.vabrant.action.actions.shake;

import com.libjam.neonrain.vabrant.action.actions.VBActionable;

public interface VBShakable extends VBActionable{
	public void setShake(float x, float y, float rotation);
	public float getShakeX();
	public float getShakeY();
	public float getShakeRotation();
}
