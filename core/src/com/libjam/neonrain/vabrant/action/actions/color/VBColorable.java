package com.libjam.neonrain.vabrant.action.actions.color;

import com.badlogic.gdx.graphics.Color;
import com.libjam.neonrain.vabrant.action.actions.VBActionable;

public interface VBColorable extends VBActionable {
	public void setColor(Color c);
	public void setColor(float r, float g, float b, float a);
	public void setStartColor(Color c);
	public void setStartColor(float r, float g, float b, float a);
	public void setEndColor(Color c);
	public void setEndColor(float r, float g, float b, float a);
	public Color getColor();
	public Color getStartColor();
	public Color getEndColor();
	public default void setAlpha(float a) {};
	public float getAlpha();
}
