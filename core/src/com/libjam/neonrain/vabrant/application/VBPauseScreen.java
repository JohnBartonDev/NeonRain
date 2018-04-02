package com.libjam.neonrain.vabrant.application;

public class VBPauseScreen<A extends VBApplication, B extends VBBackground> extends VBBaseScreen<A, B>{
	
	private boolean _restrictMainScreenInputWhenPaused;
	private boolean _useCallbackForMainScreen;
	private PauseCallback _listener;
	
	public VBPauseScreen(A app) {
		super(app);
	}
	
	public void restrictMainScreenInputWhenPaused(boolean restrict) {
		_restrictMainScreenInputWhenPaused = restrict;
	}
	
	public void useCallbackForMainScreen(boolean callback) {
		_useCallbackForMainScreen = callback;
	}
	
	public boolean restrictMainScreenInputWhenPaused() {
		return _restrictMainScreenInputWhenPaused;
	}
	
	public boolean useCallbackforMainScreen() {
		return _useCallbackForMainScreen;
	}

	protected void setListener(PauseCallback listener) {
		_listener = listener;
	}
	
	public void endPause() {
		if(_listener != null) _listener.pauseOver();
	}
	
	public interface PauseCallback{
		public void pauseOver();
	}
}
