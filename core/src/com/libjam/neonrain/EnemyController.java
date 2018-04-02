package com.libjam.neonrain;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBScreen;

public class EnemyController {


	public boolean _canDeployBlue = true;
	public boolean _canDeployRed = true;
	public boolean _canDeployGreen = true;
	public final int _blueChance = 1;
	public final int _redChance = 4;
	public final int _greenChance = 3;
	public final int _yellowChance = 5;
	public float _blueTimer;
	public float _redTimer;
	public float _greenTimer;
	public final float _blueGap = 7;
	public final float _redGap = 3;
	public final float _greenGap = 2;
	
	private float _spawnGap = 1;
	private float _spawnTimer = _spawnGap;
	private Array<EnemyDrop> _inUse;
	
	public EnemyController() {
		_inUse = new Array<>(25);
	}
	
	public void rotate() {
		for(int i = 0; i < _inUse.size; i++) {
			_inUse.get(i).setRotation(90);
		}
	}

	public void update(float delta, PlayerDrop player, ScoreHud score) {
		updateRedTimer(delta);
		updateBlueTimer(delta);
		updateGreenTimer(delta);
		
		spawnLogic(delta, player);
		for(int i = _inUse.size - 1; i >= 0; i--) {
			_inUse.get(i).update(delta);
			
			if(!_inUse.get(i).isAlive()) {
				score.increment();
				Pools.free(_inUse.removeIndex(i));
			}
		}
		checkCollision(player);
	}
	
	public void draw(TextureRegion tex, VBScreen screen, VBCamera camera) {
		for(int i = 0; i < _inUse.size; i++) {
			_inUse.get(i).draw(tex, screen, camera);
		}
	}
	
	public void drawHitBoxes(ShapeRenderer renderer) {
		for(int i = 0; i < _inUse.size; i++) {
			_inUse.get(i).drawHitBox(renderer);
		}
	}
	
	private void updateRedTimer(float delta) {
		if(!_canDeployRed) {
			_redTimer += delta;
			
			if(_redTimer >= _redGap) {
				_canDeployRed = true;
			}
		}
	}
	
	private void updateGreenTimer(float delta) {
		if(!_canDeployGreen) {
			_greenTimer += delta;
			
			if(_greenTimer >= _greenGap) {
				_canDeployGreen = true;
			}
		}
	}
	
	private void updateBlueTimer(float delta) {
		if(!_canDeployBlue) {
			_blueTimer += delta;
			
			if(_blueTimer >= _blueGap) {
				_canDeployBlue = true;
			}
		}
	}
	
	public void spawnLogic(float delta, PlayerDrop player) {
		_spawnTimer += delta;
		
		if(_spawnTimer >= _spawnGap) {
			_spawnTimer = 0;
			_spawnGap = MathUtils.random(0.5f, 1);
			
			int sum = 0;
			
			sum += _yellowChance;
			
			if(_canDeployGreen) sum += _greenChance;
			if(_canDeployRed) sum += _redChance;
			if(_canDeployBlue) sum += _blueChance;
			
			int ran = MathUtils.random(0, sum);
			
			int currentValue = 0;
			int previousValue = 0;
			
			//check yellow
			currentValue = _yellowChance;
			if(ran <= currentValue) {
				spawnYellowDrop();
				return;
			}
			else {
				previousValue = currentValue;
			}
			
			//check green
			if(_canDeployGreen) {
				currentValue = _greenChance + previousValue;
				if(ran <= currentValue) {
					spawnGreenDrop();
					return;
				}
				else {
					previousValue = currentValue;
				}
			}
			
			//check green
			if(_canDeployRed) {
				currentValue = _redChance + previousValue;
				if(ran <= currentValue) {
					spawnRedDrop();
					return;
				}
				else {
					previousValue = currentValue;
				}
			}
			
			//last option so it must be blue
			if(_canDeployBlue) {
				currentValue = _blueChance + previousValue;
				spawnBlueDrop(player);
			}
		}
	}
	
	public void checkCollision(PlayerDrop player) {
		if(!player.isAlive()) return;
		
		for(int i = 0; i < _inUse.size; i++) {
			EnemyDrop enemy = _inUse.get(i);
			
			//did the heads collide 
			if(player.getHitBox().overlaps(enemy.getHitBox())) {
				player.kill();
				return;
			}

			//did the player head collide with the enemy body
			int length = enemy.getLength();
			Rectangle[] snaps = enemy.getBody();
			for(int j =  0; j < length; j++) {
				if(player.getHitBox().overlaps(snaps[j])) {
					player.kill();
					return;
				}
			}
			
			//did the enenmy collide with the player body
			length = player.getLength();
			snaps = player.getBody();
			for(int j = 0; j < length; j++) {
				if(enemy.getHitBox().overlaps(snaps[j])) {
					player.kill();
					return;
				}
			}
		}
	}
	
	public void spawnBlueDrop(PlayerDrop player) {
		_canDeployBlue = false;
		_blueTimer = 0;
		
		BlueDrop drop = Pools.obtain(BlueDrop.class);
		
		int length = MathUtils.random(20, 50);
		int x = MathUtils.random(0, 480 - 10);
		
		drop.setup(player, x, length);
		drop.start();
		
		_inUse.add(drop);
	}
	
	public void spawnYellowDrop() {
		YellowDrop drop = Pools.obtain(YellowDrop.class);
		
		int length = MathUtils.random(20, 50);
		int x = MathUtils.random(0, 480 - 10);
		
		drop.setup(x, length);
		drop.start();
		_inUse.add(drop);
	}
	
	public void spawnRedDrop() {
		_canDeployRed = false;
		_redTimer = 0;
		
		RedDrop drop = Pools.obtain(RedDrop.class);
		
		int length = MathUtils.random(20, 50);
		int x = MathUtils.random(0, 480 - 10);
	
		drop.setup(x, length);
		drop.start();
		_inUse.add(drop);
	}
	
	public void spawnGreenDrop() {
		_canDeployGreen = false;
		_greenTimer = 0;
		
		GreenDrop drop1 = Pools.obtain(GreenDrop.class);
		GreenDrop drop2 = Pools.obtain(GreenDrop.class);
		
		int length = MathUtils.random(20, 50);
		
		float x = MathUtils.random(0, (480 - 10)/2);
		drop1.setup(x, length);
		
		x = MathUtils.random((480 - 10)/2, 480 - 10);
		drop2.setup(x, length);
		
		drop1.start();
		drop2.start();
		
		_inUse.add(drop1);
		_inUse.add(drop2);
	}

}
