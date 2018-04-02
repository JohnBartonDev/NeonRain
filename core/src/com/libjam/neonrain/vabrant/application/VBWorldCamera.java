package com.libjam.neonrain.vabrant.application;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pools;
import com.libjam.neonrain.vabrant.action.actions.VBActionController;
import com.libjam.neonrain.vabrant.action.actions.VBActionTag;
import com.libjam.neonrain.vabrant.action.actions.zoom.VBDefaultZoomable;
import com.libjam.neonrain.vabrant.action.actions.zoom.VBSingleZoomAction;

public class VBWorldCamera {
	
	public enum XAlign{
		LEFT, CENTER, RIGHT;

		public float apply(float worldWidth, float width) {
			switch(this) {
				case LEFT:
					return 0;
				case RIGHT:
					return width - worldWidth;
				default:
				case CENTER:
					return (width - worldWidth) / 2;
			}
		}
	}
	
	public enum YAlign{
		TOP, CENTER, BOTTOM;
		
		public float apply(float worldHeight, float height) {
			switch(this) {
				case TOP:
					return height - worldHeight;
				case BOTTOM:
					return 0;
				default:
				case CENTER:
					return (height - worldHeight) / 2;
			}
		}
	}
	
	private XAlign _xAlign = XAlign.CENTER;
	private YAlign _yAlign = YAlign.CENTER;
	private Rectangle _bounds;
	private VBActionTag _zoomTag;
	private VBDefaultZoomable _zoomComponent;
	private VBActionController _actionController;
	
	public VBWorldCamera() {
		_actionController = new VBActionController();
		_zoomTag = new VBActionTag();
		_zoomComponent = new VBDefaultZoomable();
		_bounds = new Rectangle(0,0,480,320); 
	}
	
	public void setSize(float width, float height) {
		_bounds.setSize(width, height);
	}
	
	public void setPosition(float width, float height) {
		float x = _xAlign.apply(_bounds.width, width);
		float y = _yAlign.apply(_bounds.height, height);
		_bounds.setPosition(x, y);
		
	}
	
	public void update(float delta) {
		_actionController.update(delta);
	}
	
	public float getWidth() {
		return _bounds.width;
	}
	
	public float getHeight() {
		return _bounds.height;
	}
	
	public float getX() {
		return _bounds.x ;
	}
	
	public float getY() {
		return _bounds.y ;
	}
	
	public float getZoom() {
		return _zoomComponent.getZoom();
	}
	
	public void zoomTo(float to, float duration, boolean reverseBackToStart, Interpolation interpolation) {
		VBSingleZoomAction action = Pools.obtain(VBSingleZoomAction.class);
		action.set(duration, reverseBackToStart, interpolation);
		action.zoomTo(_zoomComponent, to);
		_actionController.add(action);
	}
	
	public void zoomBy(float by, float duration, boolean reverseBackToStart, Interpolation interpolation) {
		VBSingleZoomAction action = Pools.obtain(VBSingleZoomAction.class);
		action.set(duration, reverseBackToStart, interpolation);
		action.zoomBy(_zoomComponent, by);
		_actionController.add(action);
	}
	
}
