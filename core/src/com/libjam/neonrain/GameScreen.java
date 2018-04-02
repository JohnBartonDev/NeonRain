package com.libjam.neonrain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.libjam.neonrain.StartHud.StartHudListener;
import com.libjam.neonrain.vabrant.application.VBBackground;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBScreen;
import com.libjam.neonrain.vabrant.util.VBUtils;

public class GameScreen extends VBScreen<NeonRain, VBBackground> implements StartHudListener{
	
	private boolean _running;
	private boolean _gameOver;
	private float _endTimer;
	private ShapeRenderer _renderer;
	private PlayerDrop _player;
	private TextureRegion _square;
	private EnemyController _enemyController;
	private ScoreHud _scoreHud;
	private StartHud _startHud;
	
	public GameScreen(NeonRain app) {
		super(app);
		_renderer = new ShapeRenderer();
		_renderer.setProjectionMatrix(getApplication().getCamera().getCombined());
		_player = new PlayerDrop();
		_enemyController = new EnemyController();
		_scoreHud = new ScoreHud(getApplication().getAssetManager());
		_startHud = new StartHud(this);
		_square = VBUtils.getRegion(getApplication().getAssetManager(), Constants.GAME_ATLAS, "square");
		
		_startHud.start();
	}
	
	@Override
	public void keyDown(int keycode) {
		_player.keyDown(keycode);
	}
	
	@Override
	public void update(float delta) {
		_startHud.update(delta);
		
		if(!_running) return;
		
		if(!_player.isAlive() && !_gameOver) {
			getApplication().getCamera().normalShake(0.3f, 0.4f, 3, 0);
			_gameOver = true;
		}
		
		if(_gameOver) {
			_endTimer += delta;
			
			if(_endTimer >= 2) {
				getApplication().setScreen(new GameOverScreen(getApplication(), _scoreHud.getScore()));
			}
			return;
		}
		_player.update(delta);
		_enemyController.update(delta, _player, _scoreHud);
	}
	
	@Override
	public void updateAfterRender() {
//		_renderer.begin(ShapeType.Line);
//		_player.drawHitBox(_renderer);
//		_enemyController.drawHitBoxes(_renderer);
//		_renderer.end();
	}
	
	@Override
	public void render(VBCamera camera) {
		_player.draw(_square, this, camera);
		_enemyController.draw(_square, this, camera);
		_scoreHud.draw(this, camera);
		_startHud.draw(this, camera);
	}
	
	@Override
	public void startHudOver() {
		_player.start();
		_running = true;
	}

}
