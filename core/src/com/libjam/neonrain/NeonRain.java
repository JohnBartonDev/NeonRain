package com.libjam.neonrain;

import com.badlogic.gdx.assets.AssetManager;
import com.libjam.neonrain.vabrant.application.VBApplication;
import com.libjam.neonrain.vabrant.application.VBBackground;

public class NeonRain extends VBApplication<VBBackground, AssetManager> {
	
	@Override
	public void initApplication() {
		setScreen(new SplashScreen(this));
	}

}
