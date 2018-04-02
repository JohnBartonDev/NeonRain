package com.libjam.neonrain.vabrant.test;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.libjam.neonrain.vabrant.application.VBBackground;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBScreen;

public class VBTestScreenOne extends VBScreen<VBTestApplication, VBBackground>{

	float _inputTimer;
	TextureRegion _region;
	
	public VBTestScreenOne(VBTestApplication app) {
		super(app);
//		VBUtils.loadAtlas(getApplication().getAssetManager(), Constants.BACKGROUND_ATLAS);
		getApplication().getAssetManager().finishLoading();
		
		setPauseScreen(new VBTestPauseScreen(app));
		
//		_region = getApplication().getAssetManager().get(Constants.BACKGROUND_ATLAS, TextureAtlas.class).findRegion("square");
	}

	@Override
	public void render(VBCamera camera) {
		float x = 480/2 - 25;
		float y = 320/2 - 25;
		
		Color old = camera.getBatch().getColor();
		camera.getBatch().setColor(Color.GREEN);
		camera.drawRegion(null, _region, x, y, 50, 50, true, ShakeType.NONE);
		camera.getBatch().setColor(old);
	}
	
	@Override
	public void update(float delta) {
		if(isInputRestricted()) {
			_inputTimer += delta;
			
			if(_inputTimer >= 3) {
				restrictInput(false);
			}
		}
	}
	
	@Override
	public void keyDown(int keycode) {
		if(keycode == Keys.SPACE) {
		}
		
		if(keycode == Keys.N) {
		}
	}
}
