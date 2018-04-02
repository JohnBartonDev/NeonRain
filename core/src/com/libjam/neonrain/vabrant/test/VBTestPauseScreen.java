package com.libjam.neonrain.vabrant.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.libjam.neonrain.vabrant.application.VBBackground;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBPauseScreen;

public class VBTestPauseScreen extends VBPauseScreen<VBTestApplication, VBBackground>{
	
	boolean _running;
	float _timer;
	TextureRegion _bg;
	
	public VBTestPauseScreen(VBTestApplication app) {
		super(app);
		restrictMainScreenInputWhenPaused(true);
		useCallbackForMainScreen(true);
//		_bg = VBUtils.getRegion(getApplication().getAssetManager(), Constants.BACKGROUND_ATLAS, "square");
	}
	
	@Override
	public void show() {
		if(_running) _running = false;
	}
	
	@Override
	public void hide() {
		_running = true;
		_timer = 0;
	}
	
	@Override
	public void update(float delta) {
		System.out.println("updating");
		
		if(_running) {
			if((_timer += delta) >= 2) {
				endPause();
				_running = false;
			}
		}
	}
	
	@Override
	public void render(VBCamera camera) {
		Color old = camera.getBatch().getColor();
		camera.getBatch().setColor(0,0,0,0.5f);
		camera.drawRegion(null, _bg, 0, 0, 480, 320, true, ShakeType.NONE);
		camera.getBatch().setColor(old);
	}
}
