package com.libjam.neonrain.vabrant.application;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;
import com.libjam.neonrain.vabrant.action.actions.VBActionController;
import com.libjam.neonrain.vabrant.action.components.VBActionComponent;

public class VBApplication <B extends VBBackground, A extends AssetManager>implements ApplicationListener, InputProcessor{

	private static VBActionController _actionController;
	
	private boolean _switchScreens;
	private VBScreen _currentScreen;
	private VBScreen _nextScreen;
	private VBCamera _camera;
	private Color _clearColor;
	private A _assetManager;
	private B _background;
	
	@Override
	public void create() {
		_actionController = new VBActionController();
		_camera = new VBCamera();
		_clearColor = new Color(Color.WHITE);
		_assetManager = (A)new AssetManager();
		initApplication();
		Gdx.input.setInputProcessor(this);
	}
	
	public static void addAction(VBActionComponent action) {
		_actionController.add(action);
	}
	
	public void initApplication() {};
	
	public void setAssetManager(A manager) {
		if(_assetManager != null) {
			_assetManager.dispose();
			_assetManager = null;
		}
		_assetManager = manager;
	}
	
	public void setBackground(B background) {
		_background = background;
	}
	
	public B getBackground() {
		return _background;
	}
	
	public void setCameraSize(int width, int height) {
		_camera.setCameraSize(width, height);
	}
	
	public VBCamera getCamera() {
		return _camera;
	}
	
	public A getAssetManager() {
		return _assetManager;
	}

	@Override
	public void resize(int width, int height) {
		_camera.resize(width, height);
		if(_background != null) _background.resize(getCamera());
		if(_currentScreen != null) _currentScreen.resizeScreen(getCamera());
	}
	
	public void setClearColor(Color color) {
		_clearColor.set(color);
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(_clearColor.r, _clearColor.g, _clearColor.b, _clearColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//update
		float delta = Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f);
		getCamera().update(delta);
		_actionController.update(delta);
		if(_background != null) _background.update(delta);
		if(_currentScreen != null) _currentScreen.updateScreen(delta);
		
		//render
		getCamera().getBatch().begin();
		if(_background != null) _background.render(null, getCamera());
		if(_currentScreen != null) _currentScreen.renderScreen(getCamera());
		getCamera().getBatch().end();
		
		if(_currentScreen != null) _currentScreen.updateAfterRender();
		
		//switch screens
		if(_switchScreens) {
			switchScreens();
		}
	}
	
	public void setScreen(VBScreen next) {
		_nextScreen = next;
		_switchScreens = true;
	}
	
	private void switchScreens() {
		_switchScreens = false;
		
		if(_currentScreen != null) {
			_currentScreen.hideScreen();
			_currentScreen.disposeScreen();
		}
		
		_currentScreen = null;
		_currentScreen = _nextScreen;
		_nextScreen = null;
		
		_currentScreen.showScreen();
	}

	@Override
	public void pause() {
		if(_currentScreen != null) _currentScreen.pauseScreen();
	}

	@Override
	public void resume() {
		if(_currentScreen != null) _currentScreen.resumeScreen();
	}

	@Override
	public void dispose() {
		if(_currentScreen != null) _currentScreen.resumeScreen();
		getCamera().getBatch().dispose();
	}
	
	//input
	@Override
	public boolean keyDown(int keycode) {
		if(_currentScreen != null) _currentScreen.keyDownScreen(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(_currentScreen != null) _currentScreen.keyUpScreen(keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 touch = getCamera().unproject(screenX, screenY);
		if(_currentScreen != null) _currentScreen.touchDownScreen((int)touch.x, (int)touch.y, pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector3 touch = getCamera().unproject(screenX, screenY);
		if(_currentScreen != null) _currentScreen.touchUpScreen((int)touch.x, (int)touch.y, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector3 touch = getCamera().unproject(screenX, screenY);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
