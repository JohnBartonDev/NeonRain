package com.libjam.neonrain.vabrant.application;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.libjam.neonrain.vabrant.action.actions.VBActionController;
import com.libjam.neonrain.vabrant.action.actions.VBActionTag;
import com.libjam.neonrain.vabrant.action.actions.shake.VBDefaultShakable;
import com.libjam.neonrain.vabrant.action.actions.shake.VBSingleShakeAction;

public class VBCamera {
	
	public enum ShakeType{
		NONE, 
		BIG,
		ALL
	}

	private ExtendViewport _viewport;
	private VBWorldCamera _worldCamera;
	private VBCameraRenderer _renderer;
	private VBDefaultShakable _shake;
	private VBActionTag _normalShakeTag;
	private VBActionTag _bigShakeTag;
	private VBActionController _actionController;
	private Vector3 _touch;
	private SpriteBatch _batch;
	
	public VBCamera() {
		_batch = new SpriteBatch();
		_actionController = new VBActionController();
		_normalShakeTag = new VBActionTag();
		_bigShakeTag = new VBActionTag();
		_shake = new VBDefaultShakable();
		_renderer = new VBCameraRenderer();
		_viewport = new ExtendViewport(480, 320);
		_worldCamera = new VBWorldCamera();
		_touch = new Vector3();
	}
	
	public SpriteBatch getBatch() {
		return _batch;
	}
	
	public void setCameraSize(float width, float height) {
		_viewport.setMinWorldWidth(width);
		_viewport.setMinWorldHeight(height);
		_worldCamera.setSize(width, height);
	}
	
	public Matrix4 getCombined() {
		return _viewport.getCamera().combined;
	}
	
	public float getGutterWidth() {
		return _viewport.getWorldWidth() - _viewport.getMinWorldWidth();
	}
	
	public float getGutterHeight() {
		return _viewport.getWorldHeight() - _viewport.getMinWorldHeight();
	}
	
	public Viewport getViewport() {
		return _viewport;
	}
	
	public VBWorldCamera getWorldCamera() {
		return _worldCamera;
	}
	
	protected VBCameraRenderer getCameraRenderer() {
		return _renderer;
	}
	
	public boolean isNormalShakeShaking() {
		return _normalShakeTag.isRunning();
	}
	
	public boolean isBigShakeShaking() {
		return _bigShakeTag.isRunning();
	}
	
	public float getShakeX() {
		return _shake.getShakeX();
	}
	
	public float getShakeY() {
		return _shake.getShakeY();
	}
	
	public float getShakeAngle() {
		return _shake.getShakeRotation();
	}
	
	public void resize(int width, int height) {
		_viewport.update(width, height, true);
		_worldCamera.setPosition(_viewport.getWorldWidth(), _viewport.getWorldHeight());
		_batch.setProjectionMatrix(getViewport().getCamera().combined);
	}
	
	public void update(float delta) {
		_actionController.update(delta);
		_worldCamera.update(delta);
	}
	
	public Vector3 unproject(int x, int y) {
		float width = getViewport().getScreenWidth();
		float height = getViewport().getScreenHeight();
		float xOffset = getGutterWidth();
		float yOffset = getGutterHeight();
		return _viewport.getCamera().unproject(_touch.set(x,y,0), xOffset, yOffset, width, height);
	}
	
	public void normalShake(float duration, float shakeAmount, float offset, float rotateAmount) {
		if(isBigShakeShaking() || isNormalShakeShaking()) return;
		VBSingleShakeAction action = Pools.obtain(VBSingleShakeAction.class);
		action.setTag(_normalShakeTag);
		action.set(duration, false, Interpolation.linear);
		action.shake(_shake, shakeAmount, offset, rotateAmount);
		_actionController.add(action);
	}
	
	public void bigShake(float duration, float shakeAmount, float offset, float rotateAmount) {
		if(isBigShakeShaking()) return;
		if(_normalShakeTag.isRunning()) _normalShakeTag.end();
		VBSingleShakeAction action = Pools.obtain(VBSingleShakeAction.class);
		action.setTag(_bigShakeTag);
		action.set(duration, false, Interpolation.linear);
		action.shake(_shake, shakeAmount, offset, rotateAmount);
		_actionController.add(action);
	}
	
	public void draw(VBScreen screen, Texture texture, float x, float y, float width, float height, boolean drawToWorld, ShakeType type) {
		_renderer.draw(this, screen, texture, x, y, width, height, drawToWorld, type);
	}
	
	public void drawRegion(VBScreen screen, TextureRegion region, float x, float y, float width, float height, boolean drawToWorld, ShakeType type) {
		_renderer.draw(this, screen, region, x, y, width, height, drawToWorld, type);
	}
	
	public void drawRegion(VBScreen screen, TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean drawToWorld, ShakeType type) {
		_renderer.draw(this, screen, region, x, y, originX, originY, width, height, scaleX, scaleY, rotation, drawToWorld, type);
	}
	
	public void drawNinePatch(VBScreen screen, NinePatch patch, float x, float y, float width, float height, boolean drawToWorld, ShakeType shake) {
		_renderer.draw(this, screen, patch, x, y, width, height, drawToWorld, shake);
	}
	
	public void drawNinePatch(VBScreen screen, NinePatch patch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean drawToWorld, ShakeType shake) {
		_renderer.draw(this, screen, patch, x, y, originX, originY, width, height, scaleX, scaleY, rotation, drawToWorld, shake);
	}
	
	public void drawBitmapFont(VBScreen screen, BitmapFont font, GlyphLayout layout, float x, float y, boolean drawToWorld, ShakeType shake){
		_renderer.draw(this, screen, font, layout, x, y, drawToWorld, shake);
	}
	
	public void drawBitmapFont(VBScreen screen, BitmapFont font, float x, float y, String str, boolean drawToWorld, ShakeType shake) {
		_renderer.draw(this, screen, font, x, y, str, drawToWorld, shake);
	}
	
	public void debug(VBCamera camera, NinePatch debug) {
		debug.setColor(Color.BLACK);
		camera.drawNinePatch(null, debug, 0, 0, _viewport.getWorldWidth(), _viewport.getWorldHeight(), false, ShakeType.NONE);
		debug.setColor(Color.WHITE);
		camera.drawNinePatch(null, debug, _worldCamera.getX(), _worldCamera.getY(), _worldCamera.getWidth(), _worldCamera.getHeight(), false, ShakeType.NONE);
	}
}
