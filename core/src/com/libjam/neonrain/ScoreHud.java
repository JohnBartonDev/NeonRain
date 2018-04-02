package com.libjam.neonrain;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBScreen;
import com.libjam.neonrain.vabrant.util.VBUtils;

public class ScoreHud {
	
	private int _score = 0;
	private BitmapFont _font;

	public ScoreHud(AssetManager manager) {
		_font = VBUtils.getFont(manager, Constants.BUBBLE_FONT);
	}
	
	public void increment() {
		_score++;
	}
	
	public int getScore() {
		return _score;
	}
	
	public void draw(VBScreen screen, VBCamera camera) {
		String score = Integer.toString(_score);
		
		if(score.length() == 4) {
			_font.getData().setScale(0.3f);
		}
		else if(score.length() == 5) {
			_font.getData().setScale(0.23f);
		}
		else if(score.length() == 6) {
			_font.getData().setScale(0.19f);
		}
		else {
			_font.getData().setScale(0.4f);
		}
		
		camera.drawBitmapFont(screen, _font, 400, 310, Integer.toString(_score), true, ShakeType.ALL);
	}
}
