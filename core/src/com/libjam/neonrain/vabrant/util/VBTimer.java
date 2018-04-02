package com.libjam.neonrain.vabrant.util;

public class VBTimer {

	private boolean _isPaused;
	private boolean _isRunning;
	private float _delay;
	private float _duration;
	private float _timer;
	
	public VBTimer() {
		this(0,0);
	}
	
	public VBTimer(float duration) {
		this(0,duration);
	}
	
	public VBTimer(float delay, float duration) {
		set(delay, duration);
	}
	
	public void set(float delay, float duration) {
		if(_isRunning) return;
		_delay = delay;
		_duration = duration;
	}
	
	public void start() {
		if(_isRunning) return;
		_isRunning = true;
		_timer = 0;
	}
	
	public void restart() {
		if(!_isRunning) _isRunning = true;
		_timer = 0;
	}
	
	public void end() {
		_isRunning = false;
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
		
		if(_delay > 0) {
			_delay -= delta;
			return;
		}
		
		_timer += delta;
		if(_timer >= _duration) {
			end();
		}
	}
	
	
}
