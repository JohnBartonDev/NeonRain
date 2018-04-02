package com.libjam.neonrain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.libjam.neonrain.vabrant.application.VBBackground;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBScreen;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.util.VBButton;
import com.libjam.neonrain.vabrant.util.VBButton.VBButtonListener;
import com.libjam.neonrain.vabrant.util.VBUtils;

public class GameOverScreen extends VBScreen<NeonRain, VBBackground>{

	private String _score;
	private String _highScore;
	private BitmapFont _font;
	private VBButton _restartButton;
	
	public GameOverScreen(NeonRain app, int score) {
		super(app);
		_score = "score " + Integer.toString(score);
		
		int highScore = Constants.HIGH_SCORE;
		
		if(score > highScore) {
			Constants.HIGH_SCORE = score;
		}
		
		_highScore = "high score " + Constants.HIGH_SCORE;
		
		_font = VBUtils.getFont(getApplication().getAssetManager(), Constants.BUBBLE_FONT);
		TextureRegion region = VBUtils.getRegion(getApplication().getAssetManager(), Constants.GAME_ATLAS, "square");
		_restartButton = new VBButton(region, 110, 40, 480/2 - 50 - 2, 100 + 8 - 50, 110, 40);
		_restartButton.setListener(new RestartListener());
		_restartButton.setColor(Color.RED);
		addButton(_restartButton);
	}
	
	@Override
	public void render(VBCamera camera) {
		_font.getData().setScale(0.5f);
		camera.drawBitmapFont(this, _font, 480/2 - 150, 300, "game over", true, ShakeType.NONE);
//		_restartButton.draw(this, camera, true, ShakeType.NONE);
		
		_font.getData().setScale(0.3f);
		camera.drawBitmapFont(this, _font, 480/2 - 70 - 35, 230, _highScore, true, ShakeType.NONE);
		camera.drawBitmapFont(this, _font, 480/2 - 70, 190, _score, true, ShakeType.NONE);
		
		_font.getData().setScale(0.2f);
		camera.drawBitmapFont(this, _font, 480/2 - 50, 150 - 50, "restart", true, ShakeType.NONE);
	}
	
	private class RestartListener implements VBButtonListener{
		@Override
		public void touchUpSelected(VBButton button) {
			getApplication().setScreen(new GameScreen(getApplication()));
		}
	}

}
