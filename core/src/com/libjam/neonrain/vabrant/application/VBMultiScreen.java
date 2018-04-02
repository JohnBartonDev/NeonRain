package com.libjam.neonrain.vabrant.application;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class VBMultiScreen<A extends VBApplication, B extends VBBackground> extends VBScreen<A, B>{

	private boolean _switchScreens;
	private ObjectMap<Class, VBScreen> _screens;
	private VBScreen _currentScreen;
	private VBScreen _nextScreen;
	
	public VBMultiScreen(VBScreen initScreen, A application) {
		super(application);
		_screens = new ObjectMap<>();
		
		//set the initial screen
		addScreen(initScreen);
		_currentScreen = initScreen;
	}
	
	public void addScreen(VBScreen screen) {
		if(screen == null) throw new NullPointerException("Screen can't be null.");
		_screens.put(screen.getClass(), screen);
		screen.setMultiScreen(this);
	}
	
	public void setScreen(Class screen) {
		if(!_screens.containsKey(screen)) throw new NullPointerException("Screen doesn't exist.");
		_nextScreen = _screens.get(screen);
		
		//check if the next screen is the current screen
		if(_nextScreen == _currentScreen) {
			Gdx.app.error(this.getClass().getSimpleName(), "Screen is already the current screen");
			return;
		}
		_switchScreens = true;
	}
	
	public boolean isScreen(Class screen) {
		if(getCurrentScreen().getClass() == screen) return true;
		return false;
	}
	
	public VBScreen getCurrentScreen() {
		return _currentScreen;
	}
	
	public VBScreen getScreen(Class screen) {
		if(!_screens.containsKey(screen)) throw new IllegalArgumentException("Screen doesn't exist.");
		return _screens.get(screen);
	}
	
	private void switchScreens() {
		_switchScreens = false;
		
		if(_currentScreen != null) {
			_currentScreen.hide();
		}
		
		_currentScreen = null;
		_currentScreen = _nextScreen;
		_nextScreen = null;
		
		_currentScreen.show();
	}
	
	@Override
	public void resize(VBCamera camera) {
		if(_currentScreen != null) _currentScreen.resizeScreen(camera);
	}
	
	@Override
	public void show() {
		if(_currentScreen != null) _currentScreen.showScreen();
	}
	
	@Override
	public void resume() {
		if(_currentScreen != null) _currentScreen.resumeScreen();
	}
	
	@Override
	public void pause() {
		if(_currentScreen != null) _currentScreen.pauseScreen();
	}
	
	@Override
	public void hide() {
		if(_currentScreen != null) _currentScreen.hideScreen();
	}
	
	@Override
	public void dispose() {
		//dispose of the current screen
		if(_currentScreen != null) _currentScreen.disposeScreen();

		//dispose of the other screens 
		Iterator<Entry<Class, VBScreen>> itr = _screens.iterator();
		while(itr.hasNext()){
			Entry<Class, VBScreen> entry = itr.next();
			if(entry.key != _currentScreen.getClass()) {entry.value.disposeScreen();}
		}
	}
	
	@Override
	public void keyDown(int keycode) {
		if(_currentScreen != null) _currentScreen.keyDownScreen(keycode);
	}
	
	@Override
	public void keyUp(int keycode) {
		 if(_currentScreen != null) _currentScreen.keyUpScreen(keycode);
	}
	 
	@Override
	public void touchDown(int x, int y, int pointer, int button) {
		if(_currentScreen != null) _currentScreen.touchDownScreen(x, y, pointer, button);
	}
	
	@Override
	public void touchUp(int x, int y, int pointer, int button) {
		if(_currentScreen != null) _currentScreen.touchUpScreen(x, y, pointer, button);
	}
	 
	@Override
	public void fling(int x, int y) {
		if(_currentScreen != null) _currentScreen.flingScreen(x, y);
	}
	
	@Override
	public void update(float delta) {
		if(_currentScreen != null) _currentScreen.updateScreen(delta);
	}
	
	@Override
	public void render(VBCamera camera) {
		if(_currentScreen != null) _currentScreen.renderScreen(camera);
		if(_switchScreens) {
			switchScreens();
		}
	}
 
}
