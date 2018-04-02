package com.libjam.neonrain.vabrant.application;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.libjam.neonrain.vabrant.action.actions.move.VBDefaultMovable;
import com.libjam.neonrain.vabrant.util.VBButton;

public class VBBaseScreen<A extends VBApplication, B extends VBBackground> {

	private boolean _pollButtonsTouchDown;
	private boolean _restrictInput;
	private boolean _isPaused;
	private int _touchX;
	private int _touchY;
	private A _application;
	private B _background;
	private VBDefaultMovable _moveComponent;
	private Array<VBButton> _buttons;
	
	public VBBaseScreen(A application) {
		_application = application;
		_moveComponent = new VBDefaultMovable();
		_buttons = new Array<>();
	}
	
	public void addButton(VBButton button) {
		_buttons.add(button);
	}

	public void restrictInput(boolean restrictInput) {
		_restrictInput = restrictInput;
	}
	
	public boolean isInputRestricted() {
		return _restrictInput;
	}
	
	public boolean isPaused() {
		return _isPaused;
	}
	
	public VBDefaultMovable getMoveComponent() {
		return _moveComponent;
	}
	
	public void setBackground(B background) {
		if(background == null) throw new NullPointerException("Background can't be null.");
		
		if(_background != null) {
			_background.hide();
			_background.dispose();
			_background = null;
		}
		_background = background;
	}
	
	public B getBackground() {
		return _background;
	}
	
	public A getApplication() {
		return _application;
	}
	
	protected void resumeScreen() {
		_isPaused = false;
		if(_background != null) _background.resume();
		resume();
	}
	
	protected void pauseScreen() {
		_isPaused = true;
		if(_background != null) _background.pause();
		pause();
	}
	
	protected void disposeScreen() {
		if(_background != null) _background.dispose();
		
		//pool the buttons
		for(int i = _buttons.size - 1; i >= 0; i--) {
			_buttons.removeIndex(i).dispose();
		}
		dispose();
	}
	
	protected void updateScreen(float delta) {
		if(_background != null) _background.update(delta);
		update(delta);
		if(!_restrictInput) pollButtons();
	}
	
	protected void updateAfterRenderScreen() {
		if(_background != null) _background.updateAfterRender();
		updateAfterRender();
	}
	
	protected void renderScreen(VBCamera camera) {
		if(_background != null) _background.render(this, camera);
		render(camera);
	}
	
	protected void resizeScreen(VBCamera camera) {
		if(_background != null) _background.resize(camera);
		resize(camera);
	}
	
	protected void showScreen() {
		if(_background != null) _background.show();
		show();
	}
	
	protected void hideScreen() {
		if(_background != null) _background.hide();
		hide();
	}
	
	protected void keyDownScreen(int keycode) {
		if(_restrictInput) return;
		keyDown(keycode);
	}
	
	protected void keyUpScreen(int keycode) {
		if(_restrictInput) return;
		keyUp(keycode);
	}
	
	protected void touchDownScreen(int x, int y, int pointer, int button) {
		if(_restrictInput) return;
		eventTouchDownButtons(x,y);
		touchDown(x,y,pointer,button);
	}
	
	protected void touchUpScreen(int x, int y, int pointer, int button) {
		if(_restrictInput) return;
		eventTouchUpButtons(x,y);
		touchUp(x,y,pointer,button);
	}
	
	protected void flingScreen(int x, int y) {
		if(_restrictInput) return;
		fling(x,y);
	}
	
	private void eventTouchDownButtons(int x, int y) {
		for(int i = _buttons.size - 1; i >= 0; i--) {
			VBButton button = _buttons.get(i);
			if(button.poll()) continue;
			button.touchDown(x, y);
		}
	}
	
	private void eventTouchUpButtons(int x, int y) {
		for(int i = _buttons.size - 1; i >=0; i--) {
			VBButton button = _buttons.get(i);
			if(button.poll()) continue;
			button.touchUp(x,y);
		}
	}

	private void pollButtons() {
		if(_restrictInput) return;
		
		if(Gdx.input.isTouched()) {
			_pollButtonsTouchDown = true;
			int rawX = Gdx.input.getX();
			int rawY = Gdx.input.getY();
			Vector3 touch = getApplication().getCamera().unproject(rawX, rawY);
			_touchX = (int)touch.x;
			_touchY = (int)touch.y;
			
			for(int i = 0; i < _buttons.size; i++) {
				VBButton button = _buttons.get(i);
				if(!button.poll()) continue;
				button.touchDown(_touchX, _touchY);
			}
		}
		
		if(!Gdx.input.isTouched() && _pollButtonsTouchDown) {
			_pollButtonsTouchDown = false;
			for(int i = 0; i < _buttons.size; i++) {
				VBButton button = _buttons.get(i);
				if(!button.poll()) continue;
				button.touchUp(_touchX, _touchY);
			}
		}
	}

	public void render(VBCamera camera) {}
	public void update(float delta) {}
	public void updateAfterRender() {}
	public void dispose() {}
	public void pause() {}
	public void resume() {}
	public void show() {}
	public void hide() {}
	public void resize(VBCamera camera) {}
	public void keyDown(int keycode){}
	public void keyUp(int keycode){}
	public void touchDown(int x, int y, int pointer, int buttom){}
	public void touchUp(int x, int y, int pointer, int button){}
	public void fling(int x ,int y) {}
}
