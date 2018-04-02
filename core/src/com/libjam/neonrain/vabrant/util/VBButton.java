package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.libjam.neonrain.vabrant.action.actions.VBDefaultActionable;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBScreen;

public class VBButton extends VBDefaultActionable{

	private boolean _isTouchBoundsLocked;
	private boolean _isActive;
	private boolean _poll;
	private VBVector2 _size;
	private Rectangle _touchBounds;
	private TextureRegion _region;
	private VBButtonListener _listener;
	
	public VBButton(TextureRegion region) {
		this(region, 0, 0, region.getRegionWidth(), region.getRegionHeight());
	}
	
	public VBButton(TextureRegion region, float x, float y, float width, float height) {
		this(region, region.getRegionWidth(), region.getRegionHeight(), x, y, width, height);
	}
	
	public VBButton(TextureRegion region, float buttonWidth, float buttonHeight, float x, float y, float width, float height) {
		_region = region;
		_size = new VBVector2(buttonWidth, buttonHeight);
		_touchBounds = new Rectangle(x,y,width,height);
		setX(x);
		setY(y);
	}
	
	public void lockTouchBounds(boolean isLocked) {
		_isTouchBoundsLocked = isLocked;
	}
	
	public void setTouchBounds(float x, float y, float width, float height) {
		_touchBounds.set(x,y,width,height);
	}
	
	public void setActive(boolean active) {
		if(active) {
			if(_listener != null && !isActive()) _listener.activateButton();
		}
		else {
			if(_listener != null && isActive()) _listener.deactivateButton();
		}
		_isActive = active;
	}
	
	public boolean isActive() {
		return _isActive;
	}
	
	public void setPoll(boolean poll) {
		_poll = poll;
	}
	
	public boolean poll() {
		return _poll;
	}
	
	public void setListener(VBButtonListener listener) {
		if(listener == null) throw new IllegalArgumentException("Listener can not be null.");
		_listener = listener;
	}
	
	public void touchDown(int x, int y) {
		if(_listener != null) _listener.touchDown(this);
		if(contains(x, y)) {
			if(_listener != null) _listener.touchDownSelected(this);
		}
	}
	
	public void touchUp(int x, int y) {
		if(_listener != null) _listener.touchUp(this);
		if(contains(x, y)) {
			if(_listener != null) _listener.touchUpSelected(this);
		}
	}
	
	public boolean contains(float x, float y) {
		float buttonX;
		float buttonY;
		
		if(_isTouchBoundsLocked) {
			buttonX = _touchBounds.getX();
			buttonY = _touchBounds.getY();
		}
		else {
			buttonX = getX();
			buttonY = getY();
		}
		return buttonX <= x && buttonX + _touchBounds.getWidth() >= x && buttonY <= y && buttonY + _touchBounds.getHeight() >= y;
	}
	
	public void draw(VBScreen screen, VBCamera camera, boolean drawToWorld, ShakeType shake) {
		draw(screen, camera, 0, 0, 1, 1, drawToWorld, shake);
	}
	
	public void draw(VBScreen screen, VBCamera camera, float originX, float originY, float scaleX, float scaleY, boolean drawToWorld, ShakeType shake) {
		Color old = camera.getBatch().getColor();
		camera.getBatch().setColor(getColor());
		camera.drawRegion(screen, _region, getX(), getY(), _size.getWidth()/2, _size.getHeight()/2, _size.getWidth(), _size.getHeight(), getScaleX() * scaleX, getScaleY() * scaleY, getRotation(), drawToWorld, shake);
		camera.getBatch().setColor(old);
	}
	
	public void dispose() {}
		
	public interface VBButtonListener{
		public default void activateButton() {};
		public default void deactivateButton() {};
		public default void touchDownSelected(VBButton button) {};
		public default void touchDown(VBButton button) {};
		public default void touchUpSelected(VBButton button) {};
		public default void touchUp(VBButton button) {};
	}
	
}
