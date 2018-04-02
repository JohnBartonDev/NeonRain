package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.utils.Array;

public class VBCountDownTimer {
	
	private boolean _isPaused;
	private boolean _isRunning;
	private float _delay;
	private int _length;
	private float _timer;
	private Array<VBCountDownTimerListener> _listeners;
	
	public VBCountDownTimer() {
		_listeners = new Array<>();
	}

	public void addListener(VBCountDownTimerListener listener) {
		_listeners.add(listener);
	}
	
	public void start(int length) {
		start(0, length);
	}
	
	public void start(int delay, int length) {
		_delay = delay;
		_length = length + 1;
		_isRunning = true;
		_timer = 1;
	}
	
	public void start() {
		_isRunning = true;
		_timer = 1;
	}
	
	public void end() {
		_isRunning = false;
	}
	
	public void set(int length) {
		_length = length + 1;
	}
	
	public void pause() {
		_isPaused = true;
	}
	
	public void resume() {
		_isPaused = false;
	}
	
	public boolean isRunning() {
		return _isRunning;
	}
	
	public void update(float delta) {
		if(!_isRunning || _isPaused) return;
		
		_timer += delta;
		
		if(_delay > 0) {
			if(_timer >= 1) {
				--_delay;
				_timer = 0;
			}
			return;
		}
		
		if(_timer >= 1) {
			--_length;
			_timer = 0;
			
			if(_length == 0) {
				for(int i = 0; i < _listeners.size; i++){
					_listeners.get(i).VBTimerOver();
				}
				_isRunning = false;
			}
			else {
				for(int i = 0; i < _listeners.size; i++) {
					_listeners.get(i).VBCurrentCount(_length);
				}
			}
		}
	}
	
	public interface VBCountDownTimerListener{
		public default void VBCurrentCount(int count) {};
		public default void VBTimerOver() {};
	}
}
