package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.libjam.neonrain.vabrant.application.VBBackground;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBScreen;
import com.libjam.neonrain.vabrant.util.VBUtils;

public class SplashScreen extends VBScreen<NeonRain, VBBackground>{

	private boolean _isLoading = true;
	private float _timer;
	private final float DURATION = 2;
	private BitmapFont _font;
	
	public SplashScreen(NeonRain app) {
		super(app);
		
		VBUtils.loadFont(getApplication().getAssetManager(), Constants.BUBBLE_FONT);
		getApplication().getAssetManager().finishLoading();
		
		_font = VBUtils.getFont(getApplication().getAssetManager(), Constants.BUBBLE_FONT);
		
		_font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	@Override
	public void show() {
		VBUtils.loadAtlas(getApplication().getAssetManager(), Constants.GAME_ATLAS);
	}
	
	@Override
	public void render(VBCamera camera) {
		_font.setColor(Color.BLACK);
		_font.getData().setScale(0.5f);
		camera.drawBitmapFont(this, _font, 480/2 - 110, 320/2 + 50, "loading", false, ShakeType.NONE);
	}
	
	@Override
	public void update(float delta) {
		if(_isLoading) {
			if(getApplication().getAssetManager().update()) {
				_isLoading = false;
			}
		}
		
		_timer += delta;
		
		if(_timer >= DURATION && !_isLoading) {
			getApplication().setScreen(new GameScreen(getApplication()));
		}
	}
}
