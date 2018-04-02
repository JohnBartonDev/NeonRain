package com.libjam.neonrain.vabrant.action.actions.zoom;

import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponent;

public class VBBaseZoomAction<A extends VBBackEndActionable<VBZoomable>, L extends VBBaseZoomAction> extends VBActionableComponent<A, VBZoomable, L> {
	
	public VBBaseZoomAction(A action) {
		super(action);
	}
	
	public void zoomTo(VBZoomable zoom, float end) {
		zoom.setStartZoom(zoom.getZoom());
		zoom.setEndZoom(end);
		add(zoom);
	}
	
	public void zoomBy(VBZoomable zoom, float amount) {
		zoom.setStartZoom(zoom.getZoom());
		zoom.setEndZoom(zoom.getStartZoom() + amount);
		add(zoom);
	}
	
	@Override
	public void percent(VBZoomable item, float percent) {
		float zoom = MathUtils.lerp(item.getStartZoom(), item.getEndZoom(), percent);
		item.setZoom(zoom);
	}

	@Override
	public void itemOver(VBZoomable item) {
		if(getBackEndAction().reverseBackToStart()) {
			item.setZoom(item.getStartZoom());
		}
		else {
			item.setZoom(item.getEndZoom());
		}
	}
	
}
