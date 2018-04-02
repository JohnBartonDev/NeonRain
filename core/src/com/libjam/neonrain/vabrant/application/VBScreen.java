package com.libjam.neonrain.vabrant.application;

import com.libjam.neonrain.vabrant.application.VBPauseScreen.PauseCallback;

@SuppressWarnings("rawtypes")
public class VBScreen<A extends VBApplication, B extends VBBackground> extends VBBaseScreen<A, B> implements PauseCallback{

	private VBMultiScreen _multiScreen;
	private VBPauseScreen _pauseScreen;

	public VBScreen(A app) {
		super(app);
	}
	
	protected void setMultiScreen(VBMultiScreen multiScreen) {
		if(multiScreen == null) throw new NullPointerException("MultiScreen can't be null.");
		_multiScreen = multiScreen;
	}
	
	public VBMultiScreen getMultiScreen() {
		return _multiScreen;
	}
	
	public void setPauseScreen(VBPauseScreen pauseScreen) {
		if(pauseScreen == null) throw new NullPointerException("PauseScreen can't be null.");
		_pauseScreen = pauseScreen;
		_pauseScreen.setListener(this);
	}
	
	public VBPauseScreen getPauseScreen() {
		return _pauseScreen;
	}

	@Override
	public void pauseOver() {
		if(!isPaused()) return;
		super.resumeScreen();
	}
	
	@Override
	protected void resumeScreen() {
		if(getPauseScreen() != null && getPauseScreen().useCallbackforMainScreen()) {
			getPauseScreen().hideScreen();
			return;
		}
		super.resumeScreen();
	}
	
	@Override
	protected void pauseScreen() {
		super.pauseScreen();
		if(getPauseScreen() != null) getPauseScreen().showScreen();
	}
	
	@Override
	protected void disposeScreen() {
		super.disposeScreen();
		if(getPauseScreen() != null) getPauseScreen().disposeScreen();
	}
	
	@Override
	protected void updateScreen(float delta) {
		super.updateScreen(delta);
		if(getPauseScreen() != null && isPaused()) getPauseScreen().updateScreen(delta);
	}
	
	@Override
	protected void renderScreen(VBCamera camera) {
		super.renderScreen(camera);
		if(getPauseScreen() != null && isPaused()) getPauseScreen().renderScreen(camera);
	}
	
	@Override
	protected void resizeScreen(VBCamera camera) {
		super.resizeScreen(camera);
		if(getPauseScreen() != null) _pauseScreen.resizeScreen(camera);
	}
	
	@Override
	protected void keyDownScreen(int keycode) {
		if(getPauseScreen() != null && isPaused()) {
			getPauseScreen().keyDownScreen(keycode);
			if(getPauseScreen().restrictMainScreenInputWhenPaused()) return;
		}
		super.keyDownScreen(keycode);
	}
	
	@Override
	protected void keyUpScreen(int keycode) {
		if(getPauseScreen() != null && isPaused()) {
			getPauseScreen().keyUpScreen(keycode);
			if(getPauseScreen().restrictMainScreenInputWhenPaused()) return;
		}
		super.keyUpScreen(keycode);
	}
	
	@Override
	protected void touchDownScreen(int x, int y, int pointer, int button) {
		if(getPauseScreen() != null && isPaused()) {
			getPauseScreen().touchDownScreen(x, y, pointer, button);
			if(getPauseScreen().restrictMainScreenInputWhenPaused()) return;
		}
		super.touchDownScreen(x, y, pointer, button);
	}
	
	@Override
	protected void touchUpScreen(int x, int y, int pointer, int button) {
		if(getPauseScreen() != null && isPaused()) {
			getPauseScreen().touchUpScreen(x, y, pointer, button);
			if(getPauseScreen().restrictMainScreenInputWhenPaused()) return;
		}
		super.touchUpScreen(x, y, pointer, button);
	}
	
	@Override
	protected void flingScreen(int x, int y) {
		if(getPauseScreen() != null && isPaused()) {
			getPauseScreen().flingScreen(x, y);
			if(getPauseScreen().restrictMainScreenInputWhenPaused()) return;
		}
		super.flingScreen(x, y);
	}
	
}
