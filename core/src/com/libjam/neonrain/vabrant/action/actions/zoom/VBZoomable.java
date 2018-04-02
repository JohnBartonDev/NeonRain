package com.libjam.neonrain.vabrant.action.actions.zoom;

import com.libjam.neonrain.vabrant.action.actions.VBActionable;

public interface VBZoomable extends VBActionable {
	public void setZoom(float zoom);
	public void setStartZoom(float zoom);
	public void setEndZoom(float zoom);
	public float getStartZoom();
	public float getEndZoom();
	public float getZoom();
}
