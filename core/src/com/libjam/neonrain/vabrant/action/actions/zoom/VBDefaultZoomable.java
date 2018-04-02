package com.libjam.neonrain.vabrant.action.actions.zoom;

public class VBDefaultZoomable implements VBZoomable{

	private float _startZoom = 1;
	private float _endZoom = 1;
	private float _zoom = 1;
	
	@Override
	public void setZoom(float zoom) {
		_zoom = zoom;
	}

	@Override
	public void setStartZoom(float zoom) {
		_startZoom = zoom;
	}

	@Override
	public void setEndZoom(float zoom) {
		_endZoom = zoom;
	}

	@Override
	public float getStartZoom() {
		return _startZoom;
	}

	@Override
	public float getEndZoom() {
		return _endZoom;
	}

	@Override
	public float getZoom() {
		return _zoom;
	}

}
